package com.javajober.spaceWall.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.javajober.blocks.styleSetting.backgroundSetting.dto.request.BackgroundStringUpdateRequest;
import com.javajober.blocks.styleSetting.blockSetting.dto.request.BlockSettingUpdateRequest;
import com.javajober.blocks.fileBlock.dto.request.FileBlockStringUpdateRequest;
import com.javajober.blocks.freeBlock.dto.request.FreeBlockUpdateRequest;
import com.javajober.blocks.listBlock.dto.request.ListBlockUpdateRequest;
import com.javajober.blocks.snsBlock.domain.SNSType;
import com.javajober.blocks.snsBlock.dto.request.SNSBlockUpdateRequest;
import com.javajober.space.repository.AddSpaceRepository;

import com.javajober.space.domain.AddSpace;
import com.javajober.blocks.fileBlock.domain.FileBlock;
import com.javajober.blocks.fileBlock.repository.FileBlockRepository;
import com.javajober.blocks.freeBlock.domain.FreeBlock;
import com.javajober.blocks.freeBlock.repository.FreeBlockRepository;
import com.javajober.blocks.listBlock.domain.ListBlock;
import com.javajober.blocks.listBlock.repository.ListBlockRepository;
import com.javajober.member.domain.Member;
import com.javajober.member.repository.MemberRepository;
import com.javajober.blocks.styleSetting.backgroundSetting.domain.BackgroundSetting;
import com.javajober.blocks.styleSetting.blockSetting.domain.BlockSetting;
import com.javajober.spaceWall.dto.request.*;
import com.javajober.spaceWall.dto.response.SpaceWallSaveResponse;
import com.javajober.spaceWall.filedto.SpaceWallSaveRequest;
import com.javajober.blocks.styleSetting.domain.StyleSetting;
import com.javajober.blocks.styleSetting.dto.request.StyleSettingStringUpdateRequest;
import com.javajober.blocks.templateBlock.dto.request.TemplateBlockUpdateRequest;
import com.javajober.blocks.styleSetting.themeSetting.domain.ThemeSetting;
import com.javajober.blocks.styleSetting.backgroundSetting.repository.BackgroundSettingRepository;
import com.javajober.blocks.styleSetting.blockSetting.repository.BlockSettingRepository;
import com.javajober.blocks.styleSetting.repository.StyleSettingRepository;
import com.javajober.blocks.styleSetting.themeSetting.dto.request.ThemeSettingUpdateRequest;
import com.javajober.blocks.styleSetting.themeSetting.repository.ThemeSettingRepository;
import com.javajober.blocks.snsBlock.domain.SNSBlock;
import com.javajober.blocks.snsBlock.repository.SNSBlockRepository;
import com.javajober.spaceWall.domain.BlockType;
import com.javajober.spaceWall.domain.FlagType;
import com.javajober.spaceWall.domain.SpaceWall;
import com.javajober.spaceWall.spaceWallCategory.domain.SpaceWallCategoryType;
import com.javajober.spaceWall.repository.SpaceWallRepository;
import com.javajober.blocks.templateBlock.domain.TemplateBlock;
import com.javajober.blocks.templateBlock.repository.TemplateBlockRepository;
import com.javajober.blocks.wallInfoBlock.domain.WallInfoBlock;
import com.javajober.blocks.wallInfoBlock.dto.request.WallInfoBlockStringUpdateRequest;
import com.javajober.blocks.wallInfoBlock.repository.WallInfoBlockRepository;
import com.javajober.spaceWall.strategy.BlockJsonProcessor;
import com.javajober.spaceWall.strategy.BlockStrategyFactory;
import com.javajober.spaceWall.strategy.FixBlockStrategy;
import com.javajober.spaceWall.strategy.MoveBlockStrategy;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class SpaceWallService {

	private final SpaceWallRepository spaceWallRepository;
	private final SNSBlockRepository snsBlockRepository;
	private final FreeBlockRepository freeBlockRepository;
	private final TemplateBlockRepository templateBlockRepository;
	private final WallInfoBlockRepository wallInfoBlockRepository;
	private final FileBlockRepository fileBlockRepository;
	private final ListBlockRepository listBlockRepository;
	private final StyleSettingRepository styleSettingRepository;
	private final BackgroundSettingRepository backgroundSettingRepository;
	private final BlockSettingRepository blockSettingRepository;
	private final ThemeSettingRepository themeSettingRepository;
	private final MemberRepository memberRepository;
	private final AddSpaceRepository addSpaceRepository;
	private final BlockStrategyFactory blockStrategyFactory;
	private final BlockJsonProcessor blockJsonProcessor;

	public SpaceWallService(final SpaceWallRepository spaceWallRepository, final SNSBlockRepository snsBlockRepository,
							final FreeBlockRepository freeBlockRepository, final TemplateBlockRepository templateBlockRepository,
							final WallInfoBlockRepository wallInfoBlockRepository, final FileBlockRepository fileBlockRepository,
							final ListBlockRepository listBlockRepository, final StyleSettingRepository styleSettingRepository,
							final BackgroundSettingRepository backgroundSettingRepository, final BlockSettingRepository blockSettingRepository,
							final ThemeSettingRepository themeSettingRepository, final MemberRepository memberRepository,
							final AddSpaceRepository addSpaceRepository, final BlockStrategyFactory blockStrategyFactory,
							final BlockJsonProcessor blockJsonProcessor) {

		this.spaceWallRepository = spaceWallRepository;
		this.snsBlockRepository = snsBlockRepository;
		this.freeBlockRepository = freeBlockRepository;
		this.templateBlockRepository = templateBlockRepository;
		this.wallInfoBlockRepository = wallInfoBlockRepository;
		this.fileBlockRepository = fileBlockRepository;
		this.listBlockRepository = listBlockRepository;
		this.styleSettingRepository = styleSettingRepository;
		this.backgroundSettingRepository = backgroundSettingRepository;
		this.blockSettingRepository = blockSettingRepository;
		this.themeSettingRepository = themeSettingRepository;
		this.memberRepository = memberRepository;
		this.addSpaceRepository = addSpaceRepository;
		this.blockStrategyFactory = blockStrategyFactory;
		this.blockJsonProcessor = blockJsonProcessor;
	}

	@Transactional
	public SpaceWallSaveResponse save(final SpaceWallStringRequest spaceWallRequest, final FlagType flagType) {

		SpaceWallCategoryType spaceWallCategoryType = SpaceWallCategoryType.findSpaceWallCategoryTypeByString(spaceWallRequest.getData().getCategory());
		AddSpace addSpace = addSpaceRepository.findAddSpace(spaceWallRequest.getData().getSpaceId());
		Member member = memberRepository.findMember(spaceWallRequest.getData().getMemberId());

		DataStringSaveRequest data = spaceWallRequest.getData();
		ArrayNode blockInfoArray = blockJsonProcessor.createArrayNode();
		processWallInfoBlock(data, blockInfoArray);

		Long blocksPosition = 2L;
		AtomicLong blocksPositionCounter = new AtomicLong(blocksPosition);

		spaceWallRequest.getData().getBlocks().forEach(block -> {

			BlockType blockType = BlockType.findBlockTypeByString(block.getBlockType());
			Long position = blocksPositionCounter.getAndIncrement();

			String strategyName = blockType.getStrategyName();
			MoveBlockStrategy blockProcessingStrategy = blockStrategyFactory.findMoveBlockStrategy(strategyName);

			List<Long> blockIds = blockProcessingStrategy.saveBlocks(block.getSubData());
			blockIds.forEach(blockId -> blockJsonProcessor.addBlockInfoToArray(blockInfoArray, position, blockId, block));
		});

		processStyleSettingBlock(data, blockInfoArray, blocksPositionCounter);

		String shareURL = spaceWallRequest.getData().getShareURL();
		Long spaceWallId = saveSpaceWall(spaceWallCategoryType, member, addSpace, shareURL, flagType, blockInfoArray);

		return new SpaceWallSaveResponse(spaceWallId);
	}

	private void processWallInfoBlock(DataStringSaveRequest data, ArrayNode blockInfoArray) {
		String wallInfoBlockStrategyName = BlockType.WALL_INFO_BLOCK.getStrategyName();
		FixBlockStrategy wallInfoBlockStrategy = blockStrategyFactory.findFixBlockStrategy(wallInfoBlockStrategyName);
		Long wallInfoBlockId = wallInfoBlockStrategy.saveBlocks(data);
		String wallInfoBlockType  = BlockType.WALL_INFO_BLOCK.getEngTitle();
		Long blockStartPosition = 1L;
		blockJsonProcessor.addBlockToJsonArray(blockInfoArray, blockStartPosition, wallInfoBlockType, wallInfoBlockId);
	}

	private void processStyleSettingBlock(DataStringSaveRequest data, ArrayNode blockInfoArray, AtomicLong blocksPositionCounter) {
		String styleSettingBlockStrategyName = BlockType.STYLE_SETTING.getStrategyName();
		FixBlockStrategy styleSettingBlockStrategy = blockStrategyFactory.findFixBlockStrategy(styleSettingBlockStrategyName);
		Long styleSettingBlockId = styleSettingBlockStrategy.saveBlocks(data);
		String styleSettingString = BlockType.STYLE_SETTING.getEngTitle();
		Long stylePosition = blocksPositionCounter.getAndIncrement();
		blockJsonProcessor.addBlockToJsonArray(blockInfoArray, stylePosition, styleSettingString, styleSettingBlockId);
	}

	private Long saveSpaceWall(SpaceWallCategoryType spaceWallCategoryType, Member member, AddSpace addSpace, String shareURL, FlagType flagType, ArrayNode blockInfoArray) {

		String blockInfoArrayAsString = blockInfoArray.toString();
		SpaceWall spaceWall = SpaceWallSaveRequest.toEntity(spaceWallCategoryType, member, addSpace, shareURL, flagType, blockInfoArrayAsString);

		return spaceWallRepository.save(spaceWall).getId();
	}

	@Transactional
	public SpaceWallSaveResponse update(final SpaceWallStringUpdateRequest spaceWallUpdateRequest, final FlagType flagType) {

		DataStringUpdateRequest dataUpdateRequest = spaceWallUpdateRequest.getData();

		Long spaceWallId = dataUpdateRequest.getSpaceWallId();
		Long addSpaceId = dataUpdateRequest.getSpaceId();
		Long memberId = dataUpdateRequest.getMemberId();

		memberRepository.findMember(memberId);
		addSpaceRepository.findAddSpace(addSpaceId);

		SpaceWall spaceWall = spaceWallRepository.findSpaceWall(spaceWallId, addSpaceId, memberId, flagType);

		Long blocksPosition = 2L;
		AtomicLong blocksPositionCounter = new AtomicLong(blocksPosition);
		ObjectMapper jsonMapper = new ObjectMapper();
		ArrayNode blockInfoArray = jsonMapper.createArrayNode();

		WallInfoBlockStringUpdateRequest wallInfoBlockRequest = spaceWallUpdateRequest.getData().getWallInfoBlock();
		Long wallInfoBlock = updateWallInfoBlock(wallInfoBlockRequest);
		String wallInfoBlockType  = BlockType.WALL_INFO_BLOCK.getEngTitle();
		Long blockStartPosition = 1L;
		addBlockToJsonArray(blockInfoArray, jsonMapper, blockStartPosition, wallInfoBlockType, wallInfoBlock);

		spaceWallUpdateRequest.getData().getBlocks().forEach(block -> {
			BlockType blockType = BlockType.findBlockTypeByString(block.getBlockType());
			Long position = blocksPositionCounter.getAndIncrement();
			switch (blockType) {
				case FREE_BLOCK:
					List<FreeBlockUpdateRequest> freeBlockRequests = jsonMapper.convertValue(block.getSubData(), new TypeReference<List<FreeBlockUpdateRequest>>() {});
					List<Long> updateFreeBlockIds = updateFreeBlocks(freeBlockRequests);
					updateFreeBlockIds.forEach(freeBlockId -> addBlockInfoToArray(blockInfoArray, jsonMapper, blockType, position, freeBlockId, block));
					break;
				case SNS_BLOCK:
					List<SNSBlockUpdateRequest> snsBlockRequests = jsonMapper.convertValue(block.getSubData(), new TypeReference<List<SNSBlockUpdateRequest>>() {});
					List<Long> updateSnsBlockIds = updateSnsBlocks(snsBlockRequests);
					updateSnsBlockIds.forEach(snsBlockId -> addBlockInfoToArray(blockInfoArray, jsonMapper, blockType, position, snsBlockId, block));
					break;
				case TEMPLATE_BLOCK:
					List<TemplateBlockUpdateRequest> templateBlockRequests = jsonMapper.convertValue(block.getSubData(), new TypeReference<List<TemplateBlockUpdateRequest>>() {});
					List<Long> updateTemplateBlockIds = updateTemplateBlocks(templateBlockRequests);
					updateTemplateBlockIds.forEach(templateBlockId -> addBlockInfoToArray(blockInfoArray, jsonMapper, blockType, position, templateBlockId, block));
					break;
				case FILE_BLOCK:
					List<FileBlockStringUpdateRequest> fileBlockRequests = jsonMapper.convertValue(block.getSubData(), new TypeReference<List<FileBlockStringUpdateRequest>>() {});
					List<Long> updateFileBlockIds = updateFileBlocks(fileBlockRequests);
					updateFileBlockIds.forEach(fileBlockId -> addBlockInfoToArray(blockInfoArray, jsonMapper, blockType, position, fileBlockId, block));
					break;
				case LIST_BLOCK:
					List<ListBlockUpdateRequest> listBlockRequests = jsonMapper.convertValue(block.getSubData(), new TypeReference<List<ListBlockUpdateRequest>>() {});
					List<Long> updateListBlockIds = updateListBlock(listBlockRequests);
					updateListBlockIds.forEach(listBlockId -> addBlockInfoToArray(blockInfoArray, jsonMapper, blockType, position, listBlockId, block));
			}
		});

		StyleSettingStringUpdateRequest styleSettingUpdateRequest = dataUpdateRequest.getStyleSetting();
		Long styleSetting = updateStyleSetting(styleSettingUpdateRequest);
		String styleSettingString = "styleSetting";
		Long stylePosition = blocksPositionCounter.getAndIncrement();

		addBlockToJsonArray(blockInfoArray, jsonMapper, stylePosition, styleSettingString, styleSetting);
		String blocks = blockInfoArray.toString();

		spaceWall.update(dataUpdateRequest, flagType, blocks);
		spaceWallId = spaceWallRepository.save(spaceWall).getId();

		return new SpaceWallSaveResponse(spaceWallId);
	}


	private Long updateWallInfoBlock(final WallInfoBlockStringUpdateRequest wallInfoBlockRequest) {

		WallInfoBlock wallInfoBlock = wallInfoBlockRepository.findWallInfoBlock(wallInfoBlockRequest.getWallInfoBlockId());
		wallInfoBlock.update(wallInfoBlockRequest);

		return wallInfoBlockRepository.save(wallInfoBlock).getId();
	}

	private List<Long> updateFreeBlocks(final List<FreeBlockUpdateRequest> subData) {

		List<Long> updatedFreeBlockIds = new ArrayList<>();
		for (FreeBlockUpdateRequest updateRequest : subData) {
			if(updateRequest.getFreeBlockId() == null ){
				FreeBlock freeBlock = new FreeBlock(updateRequest.getFreeTitle(),updateRequest.getFreeContent());
				updatedFreeBlockIds.add(freeBlockRepository.save(freeBlock).getId());
			}else {
				FreeBlock freeBlock = freeBlockRepository.findFreeBlock(updateRequest.getFreeBlockId());
				freeBlock.update(updateRequest);
				updatedFreeBlockIds.add(freeBlockRepository.save(freeBlock).getId());
			}
		}
		return updatedFreeBlockIds;
	}

	private List<Long> updateSnsBlocks(final List<SNSBlockUpdateRequest> subData){

		List<Long> updateSnsBlockIds = new ArrayList<>();
		subData.forEach(snsBlockRequest -> {
			if(snsBlockRequest.getSnsBlockId() ==null){
				SNSType snsType = SNSType.findSNSTypeByString(snsBlockRequest.getSnsType());
				SNSBlock snsBlock = new SNSBlock(snsBlockRequest.getSnsUUID(),snsType,snsBlockRequest.getSnsURL());
				updateSnsBlockIds.add(snsBlockRepository.save(snsBlock).getId());
			}else {
				SNSBlock snsBlock = snsBlockRepository.findSNSBlock(snsBlockRequest.getSnsBlockId());
				snsBlock.update(snsBlockRequest);
				updateSnsBlockIds.add(snsBlockRepository.save(snsBlock).getId());
			}
		});

		return updateSnsBlockIds;
	}

	private List<Long> updateTemplateBlocks(final List<TemplateBlockUpdateRequest> subData) {

		List<Long> updateTemplateBlockIds = new ArrayList<>();
		for(TemplateBlockUpdateRequest updateRequest : subData) {
			if (updateRequest.getTemplateBlockId() == null) {
				TemplateBlock templateBlock = new TemplateBlock(updateRequest.getTemplateUUID(),updateRequest.getTemplateTitle(),updateRequest.getTemplateDescription());
				updateTemplateBlockIds.add(templateBlockRepository.save(templateBlock).getId());
			} else {
				TemplateBlock templateBlock = templateBlockRepository.findTemplateBlock(updateRequest.getTemplateBlockId());
				templateBlock.update(updateRequest.getTemplateUUID(), updateRequest.getTemplateTitle(), updateRequest.getTemplateDescription());
				updateTemplateBlockIds.add(templateBlockRepository.save(templateBlock).getId());
			}
		}
		return updateTemplateBlockIds;
	}

	private List<Long> updateFileBlocks(final List<FileBlockStringUpdateRequest> subData) {

		List<Long> updateFileBlockIds = new ArrayList<>();
		for (FileBlockStringUpdateRequest updateRequest : subData) {
			if(updateRequest.getFileBlockId() == null){
				FileBlock fileBlock = new FileBlock(updateRequest.getFileTitle(), updateRequest.getFileDescription(),updateRequest.getFileName(),updateRequest.getFile());
				updateFileBlockIds.add(fileBlockRepository.save(fileBlock).getId());
			}else{
				FileBlock fileBlock = fileBlockRepository.findFileBlock(updateRequest.getFileBlockId());
				fileBlock.update(updateRequest);
				updateFileBlockIds.add(fileBlockRepository.save(fileBlock).getId());
			}
		}
		return updateFileBlockIds;
	}

	private List<Long> updateListBlock(final List<ListBlockUpdateRequest> subData){

		List<Long> updateListBlockIds = new ArrayList<>();
		for(ListBlockUpdateRequest updateRequest : subData){
			if(updateRequest.getListBlockId() == null){
				ListBlock listBlock = new ListBlock(updateRequest.getListUUID(),updateRequest.getListLabel(),updateRequest.getListTitle(),updateRequest.getListDescription(),updateRequest.getIsLink());
				updateListBlockIds.add(listBlockRepository.save(listBlock).getId());
			}else{
				ListBlock listBlock = listBlockRepository.findListBlock(updateRequest.getListBlockId());
				listBlock.update(updateRequest);
				updateListBlockIds.add(listBlockRepository.save(listBlock).getId());
			}
		}
		return updateListBlockIds;
	}

	private Long updateStyleSetting(final StyleSettingStringUpdateRequest updateRequest){

		StyleSetting styleSetting = styleSettingRepository.findStyleBlock(updateRequest.getStyleSettingBlockId());
		updateBackgroundSetting(updateRequest.getBackgroundSetting());
		updateBlockSetting(updateRequest.getBlockSetting());
		updateThemeSetting(updateRequest.getThemeSetting());
		styleSetting.update(styleSetting);

		return styleSettingRepository.save(styleSetting).getId();
	}

	private Long updateBackgroundSetting(final BackgroundStringUpdateRequest updateRequest){

		BackgroundSetting backgroundSetting = backgroundSettingRepository.getById(updateRequest.getBackgroundSettingBlockId());
		backgroundSetting.update(updateRequest.getSolidColor(), updateRequest.getGradation(), updateRequest.getStyleImgURL());

		return backgroundSettingRepository.save(backgroundSetting).getId();
	}

	private Long updateBlockSetting(final BlockSettingUpdateRequest updateRequest){

		BlockSetting blockSetting = blockSettingRepository.getById(updateRequest.getBlockSettingBlockId());
		blockSetting.update(updateRequest);

		return blockSettingRepository.save(blockSetting).getId();
	}

	private Long updateThemeSetting(final ThemeSettingUpdateRequest updateRequest){

		ThemeSetting themeSetting = themeSettingRepository.getById(updateRequest.getThemeSettingBlockId());
		themeSetting.update(updateRequest.getTheme());

		return themeSettingRepository.save(themeSetting).getId();
	}

	private void addBlockInfoToArray(final ArrayNode blockInfoArray, final ObjectMapper jsonMapper, final BlockType blockType, final Long position, final Long blockId, final BlockSaveRequest block) {

		String currentBlockTypeTitle = blockType.getEngTitle();
		String blockUUID = block.getBlockUUID();

		ObjectNode blockInfoObject = jsonMapper.createObjectNode();

		blockInfoObject.put("position", position);
		blockInfoObject.put("blockType", currentBlockTypeTitle);
		blockInfoObject.put("blockId", blockId);
		blockInfoObject.put("blockUUID", blockUUID);

		blockInfoArray.add(blockInfoObject);
	}

	private void addBlockToJsonArray(final ArrayNode jsonArray, final ObjectMapper mapper, final Long position, final String blockType, final Long blockId) {

		ObjectNode blockInfoObject = mapper.createObjectNode();

		blockInfoObject.put("position", position);
		blockInfoObject.put("blockType", blockType);
		blockInfoObject.put("blockId", blockId);
		blockInfoObject.put("blockUUID", (String)null);

		jsonArray.add(blockInfoObject);
	}
}
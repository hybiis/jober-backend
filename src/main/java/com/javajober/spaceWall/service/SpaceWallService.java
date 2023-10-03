package com.javajober.spaceWall.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.javajober.fileBlock.dto.request.FileBlockUpdateRequest;
import com.javajober.freeBlock.dto.request.FreeBlockUpdateRequest;
import com.javajober.listBlock.dto.request.ListBlockUpdateRequest;
import com.javajober.memberGroup.domain.MemberGroup;
import com.javajober.snsBlock.domain.SNSType;
import com.javajober.snsBlock.dto.request.SNSBlockUpdateRequest;
import com.javajober.space.repository.AddSpaceRepository;
import com.javajober.core.config.FileDirectoryConfig;
import com.javajober.core.error.exception.Exception404;
import com.javajober.core.error.exception.Exception500;
import com.javajober.core.message.ErrorMessage;

import com.javajober.space.domain.AddSpace;
import com.javajober.fileBlock.domain.FileBlock;
import com.javajober.fileBlock.dto.request.FileBlockSaveRequest;
import com.javajober.fileBlock.repository.FileBlockRepository;
import com.javajober.freeBlock.domain.FreeBlock;
import com.javajober.freeBlock.dto.request.FreeBlockSaveRequest;
import com.javajober.freeBlock.repository.FreeBlockRepository;
import com.javajober.listBlock.domain.ListBlock;
import com.javajober.listBlock.dto.request.ListBlockSaveRequest;
import com.javajober.listBlock.repository.ListBlockRepository;
import com.javajober.member.domain.Member;
import com.javajober.member.repository.MemberRepository;
import com.javajober.backgroundSetting.domain.BackgroundSetting;
import com.javajober.blockSetting.domain.BlockSetting;
import com.javajober.spaceWall.dto.request.SpaceWallUpdateRequest;
import com.javajober.styleSetting.domain.StyleSetting;
import com.javajober.template.domain.TemplateAuth;
import com.javajober.templateBlock.dto.request.TemplateBlockUpdateRequest;
import com.javajober.themeSetting.domain.ThemeSetting;
import com.javajober.backgroundSetting.dto.request.BackgroundSettingSaveRequest;
import com.javajober.blockSetting.dto.request.BlockSettingSaveRequest;
import com.javajober.styleSetting.dto.request.StyleSettingSaveRequest;
import com.javajober.themeSetting.dto.request.ThemeSettingSaveRequest;
import com.javajober.backgroundSetting.repository.BackgroundSettingRepository;
import com.javajober.blockSetting.repository.BlockSettingRepository;
import com.javajober.styleSetting.repository.StyleSettingRepository;
import com.javajober.themeSetting.repository.ThemeSettingRepository;
import com.javajober.snsBlock.domain.SNSBlock;
import com.javajober.snsBlock.dto.request.SNSBlockRequest;
import com.javajober.snsBlock.repository.SNSBlockRepository;
import com.javajober.spaceWall.domain.BlockType;
import com.javajober.spaceWall.domain.FlagType;
import com.javajober.spaceWall.domain.SpaceWall;
import com.javajober.spaceWallCategory.domain.SpaceWallCategoryType;
import com.javajober.spaceWall.dto.request.BlockRequest;
import com.javajober.spaceWall.dto.request.SpaceWallRequest;
import com.javajober.spaceWall.dto.response.SpaceWallTemporaryResponse;
import com.javajober.spaceWall.repository.SpaceWallRepository;
import com.javajober.templateBlock.domain.TemplateBlock;
import com.javajober.templateBlock.dto.request.TemplateBlockRequest;
import com.javajober.templateBlock.repository.TemplateBlockRepository;
import com.javajober.wallInfoBlock.domain.WallInfoBlock;
import com.javajober.wallInfoBlock.dto.request.WallInfoBlockRequest;
import com.javajober.wallInfoBlock.dto.request.WallInfoBlockUpdateRequest;
import com.javajober.wallInfoBlock.repository.WallInfoBlockRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class SpaceWallService {

	private final SpaceWallRepository spaceWallRepository;
	private final SNSBlockRepository snsBlockRepository;
	private final FreeBlockRepository freeBlockRepository;
	private final TemplateBlockRepository templateBlockRepository;
	private final WallInfoBlockRepository wallInfoBlockRepository;
	private final FileBlockRepository fileBlockRepository;
	private final FileDirectoryConfig fileDirectoryConfig;
	private final ListBlockRepository listBlockRepository;
	private final StyleSettingRepository styleSettingRepository;
	private final BackgroundSettingRepository backgroundSettingRepository;
	private final BlockSettingRepository blockSettingRepository;
	private final ThemeSettingRepository themeSettingRepository;
	private final MemberRepository memberRepository;
	private final AddSpaceRepository addSpaceRepository;

	public SpaceWallService(SpaceWallRepository spaceWallRepository, SNSBlockRepository snsBlockRepository,
							FreeBlockRepository freeBlockRepository, TemplateBlockRepository templateBlockRepository,
							WallInfoBlockRepository wallInfoBlockRepository, FileBlockRepository fileBlockRepository,
							FileDirectoryConfig fileDirectoryConfig, ListBlockRepository listBlockRepository,
							StyleSettingRepository styleSettingRepository, BackgroundSettingRepository backgroundSettingRepository,
							BlockSettingRepository blockSettingRepository, ThemeSettingRepository themeSettingRepository,
							MemberRepository memberRepository, AddSpaceRepository addSpaceRepository) {

		this.spaceWallRepository = spaceWallRepository;
		this.snsBlockRepository = snsBlockRepository;
		this.freeBlockRepository = freeBlockRepository;
		this.templateBlockRepository = templateBlockRepository;
		this.wallInfoBlockRepository = wallInfoBlockRepository;
		this.fileBlockRepository = fileBlockRepository;
		this.fileDirectoryConfig = fileDirectoryConfig;
		this.listBlockRepository = listBlockRepository;
		this.styleSettingRepository = styleSettingRepository;
		this.backgroundSettingRepository = backgroundSettingRepository;
		this.blockSettingRepository = blockSettingRepository;
		this.themeSettingRepository = themeSettingRepository;
		this.memberRepository = memberRepository;
		this.addSpaceRepository = addSpaceRepository;
	}

	public SpaceWallTemporaryResponse checkSpaceWallTemporary(Long memberId, Long addSpaceId) {

		List<SpaceWall> spaceWalls = spaceWallRepository.findSpaceWalls(memberId, addSpaceId);

		if (spaceWalls == null || spaceWalls.isEmpty()) {
			return new SpaceWallTemporaryResponse(null, false);
		}

		for (SpaceWall spaceWall : spaceWalls) {
			if (spaceWall.getFlag().equals(FlagType.PENDING) && spaceWall.getDeletedAt() == null) {
				return new SpaceWallTemporaryResponse(spaceWall.getId(), true);
			}
			if (spaceWall.getFlag().equals(FlagType.SAVED) && spaceWall.getDeletedAt() == null) {
				throw new Exception404(ErrorMessage.SAVED_SPACE_WALL_ALREADY_EXISTS);
			}
		}

		return new SpaceWallTemporaryResponse(null, false);
	}

	@Transactional
	public void save(final SpaceWallRequest spaceWallRequest, FlagType flagType) {

		SpaceWallCategoryType spaceWallCategoryType = SpaceWallCategoryType.findSpaceWallCategoryTypeByString(spaceWallRequest.getData().getCategory());
		AddSpace addSpace = addSpaceRepository.findAddSpace(spaceWallRequest.getData().getAddSpaceId());
		Member member = memberRepository.findMember(spaceWallRequest.getData().getMemberId());

		Long blocksPosition = 2L;
		AtomicLong blocksPositionCounter = new AtomicLong(blocksPosition);
		ObjectMapper jsonMapper = new ObjectMapper();
		ArrayNode blockInfoArray = jsonMapper.createArrayNode();

		WallInfoBlockRequest wallInfoBlockRequest = spaceWallRequest.getData().getWallInfoBlock();
		Long wallInfoBlock = saveWallInfoBlock(wallInfoBlockRequest);
		String wallInfoBlockType  = BlockType.WALL_INFO_BLOCK.getEngTitle();
		Long blockStartPosition = 1L;
		addBlockToJsonArray(blockInfoArray, jsonMapper, blockStartPosition, wallInfoBlockType, wallInfoBlock);

		spaceWallRequest.getData().getBlocks().forEach(block -> {
			BlockType blockType = BlockType.findBlockTypeByString(block.getBlockType());
			Long position = blocksPositionCounter.getAndIncrement();
			switch (blockType) {
				case FREE_BLOCK:
					List<FreeBlockSaveRequest> freeBlockRequests = jsonMapper.convertValue(block.getSubData(),
							new TypeReference<List<FreeBlockSaveRequest>>() {
							});
					List<Long> freeBlockIds = saveFreeBlocks(freeBlockRequests);
					freeBlockIds.forEach(freeBlockId -> addBlockInfoToArray(blockInfoArray, jsonMapper, blockType, position, freeBlockId, block));
					break;
				case SNS_BLOCK:
					List<SNSBlockRequest> snsBlockRequests = jsonMapper.convertValue(block.getSubData(),
							new TypeReference<List<SNSBlockRequest>>() {
							});
					List<Long> snsBlockIds = saveSnsBlocks(snsBlockRequests);
					snsBlockIds.forEach(snsBlockId -> addBlockInfoToArray(blockInfoArray, jsonMapper, blockType, position, snsBlockId, block));
					break;
				case TEMPLATE_BLOCK:
					List<TemplateBlockRequest> templateBlockRequests = jsonMapper.convertValue(block.getSubData(),
							new TypeReference<List<TemplateBlockRequest>>() {
							});
					List<Long> templateBlockIds = saveTemplateBlocks(templateBlockRequests);
					templateBlockIds.forEach(templateBlockId -> addBlockInfoToArray(blockInfoArray, jsonMapper, blockType, position, templateBlockId, block));
					break;
				case FILE_BLOCK:
					List<FileBlockSaveRequest> fileBlockSaveRequests = jsonMapper.convertValue(block.getSubData(),
							new TypeReference<List<FileBlockSaveRequest>>() {
							});
					List<Long> fileBlockIds = saveFileBlocks(fileBlockSaveRequests);
					fileBlockIds.forEach(templateBlockId -> addBlockInfoToArray(blockInfoArray, jsonMapper, blockType, position, templateBlockId, block));
					break;
				case LIST_BLOCK:
					List<ListBlockSaveRequest> listBlockRequests = jsonMapper.convertValue(block.getSubData(),
							new TypeReference<List<ListBlockSaveRequest>>() {
							});
					List<Long> listBlockIds = saveListBlocks(listBlockRequests);
					listBlockIds.forEach(listBlockId -> addBlockInfoToArray(blockInfoArray, jsonMapper, blockType, position, listBlockId, block));
			}
		});

		StyleSettingSaveRequest styleSettingSaveRequest = spaceWallRequest.getData().getStyleSetting();
		Long styleSetting = saveStyleSetting(styleSettingSaveRequest);
		String styleSettingString = "styleSetting";
		Long stylePosition = blocksPositionCounter.getAndIncrement();
		addBlockToJsonArray(blockInfoArray, jsonMapper, stylePosition, styleSettingString, styleSetting);

		String blockInfoArrayAsString = blockInfoArray.toString();
		String shareURL = spaceWallRequest.getData().getShareURL();
		SpaceWall spaceWall = SpaceWallRequest.toEntity(spaceWallCategoryType, member, addSpace, shareURL, flagType, blockInfoArrayAsString);
		spaceWallRepository.save(spaceWall);
	}

	@Transactional
	public void update(final SpaceWallUpdateRequest spaceWallUpdateRequest, FlagType flagType){
		SpaceWall spaceWall = spaceWallRepository.findById(spaceWallUpdateRequest.getData().getSpaceWallId())
			.orElseThrow(() -> new Exception404(ErrorMessage.ADD_SPACE_NOT_FOUND));

		Long blocksPosition = 2L;
		AtomicLong blocksPositionCounter = new AtomicLong(blocksPosition);
		ObjectMapper jsonMapper = new ObjectMapper();
		ArrayNode blockInfoArray = jsonMapper.createArrayNode();

		spaceWallUpdateRequest.getData().getBlocks().forEach(block -> {
			BlockType blockType = BlockType.findBlockTypeByString(block.getBlockType());
			Long position = blocksPositionCounter.getAndIncrement();
			switch (blockType) {
				case FREE_BLOCK:
					List<FreeBlockUpdateRequest> freeBlockRequests = jsonMapper.convertValue(block.getSubData(),
						new TypeReference<List<FreeBlockUpdateRequest>>() {
						});
					List<Long> updateFreeBlockIds = updateFreeBlocks(freeBlockRequests);
					updateFreeBlockIds.forEach(freeBlockId -> addBlockInfoToArray(blockInfoArray, jsonMapper, blockType, position, freeBlockId, block));
					break;
				case SNS_BLOCK:
					List<SNSBlockUpdateRequest> snsBlockRequests = jsonMapper.convertValue(block.getSubData(),
						new TypeReference<List<SNSBlockUpdateRequest>>() {
						});
					List<Long> updateSnsBlockIds = updateSnsBlocks(snsBlockRequests);
					updateSnsBlockIds.forEach(snsBlockId -> addBlockInfoToArray(blockInfoArray, jsonMapper, blockType, position, snsBlockId, block));
					break;
				case TEMPLATE_BLOCK:
					List<TemplateBlockUpdateRequest> templateBlockRequests = jsonMapper.convertValue(block.getSubData(),
						new TypeReference<List<TemplateBlockUpdateRequest>>() {
						});
					List<Long> updateTemplateBlockIds = updateTemplateBlocks(templateBlockRequests);
					updateTemplateBlockIds.forEach(templateBlockId -> addBlockInfoToArray(blockInfoArray, jsonMapper, blockType, position, templateBlockId, block));
					break;
				case FILE_BLOCK:
					List<FileBlockUpdateRequest> fileBlockRequests = jsonMapper.convertValue(block.getSubData(),
						new TypeReference<List<FileBlockUpdateRequest>>() {
						});
					List<Long> updateFileBlockIds = updateFileBlocks(fileBlockRequests);
					updateFileBlockIds.forEach(fileBlockId -> addBlockInfoToArray(blockInfoArray, jsonMapper, blockType, position, fileBlockId, block));
					break;
				case LIST_BLOCK:
					List<ListBlockUpdateRequest> listBlockRequests = jsonMapper.convertValue(block.getSubData(),
						new TypeReference<List<ListBlockUpdateRequest>>() {
						});
					List<Long> updateListBlockIds = updateListBlock(listBlockRequests);
					updateListBlockIds.forEach(listBlockId -> addBlockInfoToArray(blockInfoArray, jsonMapper, blockType, position, listBlockId, block));
			}
		});

		String blockInfoArrayAsString = blockInfoArray.toString();
		String shareURL = spaceWallUpdateRequest.getData().getShareURL();
		SpaceWall updateSpaceWall = SpaceWallUpdateRequest.toEntity(spaceWall, shareURL, flagType, blockInfoArrayAsString);
		spaceWall.update(updateSpaceWall);
		spaceWallRepository.save(spaceWall);
	}


	private Long saveWallInfoBlock(WallInfoBlockRequest wallInfoBlockRequest) {
		WallInfoBlock wallInfoBlock = WallInfoBlockRequest.toEntity(wallInfoBlockRequest);

		return wallInfoBlockRepository.save(wallInfoBlock).getId();
	}

	private List<Long> saveFreeBlocks(List<FreeBlockSaveRequest> subData) {
		List<Long> freeBlockIds = new ArrayList<>();
		subData.forEach(block -> {
			FreeBlock freeBlock = FreeBlockSaveRequest.toEntity(block);
			freeBlockIds.add(freeBlockRepository.save(freeBlock).getId());
		});
		return freeBlockIds;
	}

	private List<Long> saveSnsBlocks(List<SNSBlockRequest> subData) {
		List<Long> snsBlockIds = new ArrayList<>();

		subData.forEach(block -> {
			SNSBlock snsBlock = SNSBlockRequest.toEntity(block);
			snsBlockIds.add(snsBlockRepository.save(snsBlock).getId());
		});
		return snsBlockIds;
	}

	private List<Long> saveTemplateBlocks(List<TemplateBlockRequest> subData){
		List<Long> templateBlockIds = new ArrayList<>();

		subData.forEach(block -> {
			TemplateBlock templateBlock = TemplateBlockRequest.toEntity(block);
			templateBlockIds.add(templateBlockRepository.save(templateBlock).getId());
		});
		return templateBlockIds;
	}

	private List<Long> saveFileBlocks(List<FileBlockSaveRequest> subData) {
		List<Long> fileBlockIds = new ArrayList<>();
		subData.forEach(block -> {
			FileBlock fileBlock = FileBlockSaveRequest.toEntity(block);
			fileBlockIds.add(fileBlockRepository.save(fileBlock).getId());
		});
		return fileBlockIds;
	}

	private List<Long> saveListBlocks(List<ListBlockSaveRequest> subData) {
		List<Long> listBlockIds = new ArrayList<>();
		subData.forEach(block -> {
			ListBlock listBlock = ListBlockSaveRequest.toEntity(block);
			listBlockIds.add(listBlockRepository.save(listBlock).getId());
		});
		return listBlockIds;
	}

	private Long saveStyleSetting(StyleSettingSaveRequest saveRequest){
		StyleSetting styleSetting =saveRequest.toEntity();

		backgroundSettingRepository.save(styleSetting.getBackgroundSetting());
		blockSettingRepository.save(styleSetting.getBlockSetting());
		themeSettingRepository.save(styleSetting.getThemeSetting());

		return styleSettingRepository.save(styleSetting).getId();
	}

	private BackgroundSetting saveBackgroundSetting(BackgroundSettingSaveRequest saveRequest){
		//String styleImg = uploadFile(file);
		BackgroundSetting backgroundSetting = saveRequest.toEntity();
		return backgroundSettingRepository.save(backgroundSetting);
	}

	private BlockSetting saveBlockSetting(BlockSettingSaveRequest saveRequest ){
		BlockSetting blockSetting = saveRequest.toEntity();
		return blockSettingRepository.save(blockSetting);
	}

	private ThemeSetting saveThemeSetting(ThemeSettingSaveRequest saveRequest){
		ThemeSetting themeSetting = saveRequest.toEntity();
		return themeSettingRepository.save(themeSetting);
	}

	private Long updateWallInfoBlock(WallInfoBlockUpdateRequest wallInfoBlockRequest) {
		WallInfoBlock wallInfoBlockPS = wallInfoBlockRepository.findWallInfoBlock(wallInfoBlockRequest.getWallInfoId());
		WallInfoBlock wallInfoBlock = WallInfoBlockUpdateRequest.toEntity(wallInfoBlockRequest);
		wallInfoBlockPS.update(wallInfoBlock);
		return wallInfoBlockRepository.save(wallInfoBlock).getId();
	}

	private List<Long> updateFreeBlocks(List<FreeBlockUpdateRequest> subData) {
		List<Long> updatedFreeBlockIds = new ArrayList<>();
		for (FreeBlockUpdateRequest updateRequest : subData) {
			FreeBlock freeBlockPS = freeBlockRepository.getById(updateRequest.getFreeId());
			FreeBlock freeBlock = FreeBlockUpdateRequest.toEntity(updateRequest);
			freeBlockPS.update(freeBlock);
			updatedFreeBlockIds.add(freeBlockRepository.save(freeBlockPS).getId());
		}
		return updatedFreeBlockIds;
	}

	private List<Long> updateSnsBlocks(List<SNSBlockUpdateRequest> subData){
		List<Long> updateSnsBlockIds = new ArrayList<>();
		subData.forEach(snsBlockRequest -> {

			SNSBlock snsBlock = snsBlockRepository.findSNSBlock(snsBlockRequest.getSnsId());

			SNSType snsType = SNSType.findSNSTypeByString(snsBlockRequest.getSnsType());

			snsBlock.update(snsBlockRequest.getSnsUUID(), snsType, snsBlockRequest.getSnsURL());

			updateSnsBlockIds.add(snsBlockRepository.save(snsBlock).getId());
		});

		return updateSnsBlockIds;
	}

	private List<Long> updateTemplateBlocks(List<TemplateBlockUpdateRequest> subData) {
		List<Long> updateTemplateBlockIds = new ArrayList<>();
		for(TemplateBlockUpdateRequest templateBlockRequest : subData){

			TemplateBlock templateBlock = templateBlockRepository.getById(templateBlockRequest.getId());
			templateBlock.update(templateBlockRequest.getTemplateUUID(), templateBlockRequest.getTemplateTitle(), templateBlockRequest.getTemplateDescription());

			updateTemplateBlockIds.add(templateBlockRepository.save(templateBlock).getId());
		}
		return updateTemplateBlockIds;
	}

	private List<Long> updateFileBlocks(List<FileBlockUpdateRequest> subData) {
		List<Long> updateFileBlockIds = new ArrayList<>();
		for (FileBlockUpdateRequest fileBlockUpdateRequest : subData) {
			FileBlock fileBlockPS = fileBlockRepository.getById(fileBlockUpdateRequest.getFileId());
			//String fileNamePS = fileBlockPS.getFileName();
			//deleteFile(fileNamePS);
			//String fileName = uploadFile(file);
			FileBlock fileBlock = FileBlockUpdateRequest.toEntity(fileBlockUpdateRequest);
			fileBlockPS.update(fileBlock);

			fileBlockRepository.save(fileBlockPS);
		}
		return updateFileBlockIds;
	}

	private List<Long> updateListBlock(List<ListBlockUpdateRequest> subData){
		List<Long> updateListBlockIds = new ArrayList<>();
		for(ListBlockUpdateRequest UpdateRequest : subData){
			ListBlock listBlockPS = listBlockRepository.findListBlock(UpdateRequest.getListId());
			ListBlock listBlock = ListBlockUpdateRequest.toEntity(UpdateRequest);
			listBlockPS.update(listBlock);
			updateListBlockIds.add(listBlockRepository.save(listBlockPS).getId());
		}
		return updateListBlockIds;
	}
	private void addBlockInfoToArray(ArrayNode blockInfoArray, ObjectMapper jsonMapper, BlockType blockType, Long position, Long blockId, BlockRequest block) {
		String currentBlockTypeTitle = blockType.getEngTitle();
		String blockUUID = block.getBlockUUID();

		ObjectNode blockInfoObject = jsonMapper.createObjectNode();

		blockInfoObject.put("position", position);
		blockInfoObject.put("blockType", currentBlockTypeTitle);
		blockInfoObject.put("blockId", blockId);
		blockInfoObject.put("blockUUID", blockUUID);

		blockInfoArray.add(blockInfoObject);
	}

	private void addBlockToJsonArray(ArrayNode jsonArray, ObjectMapper mapper, Long position, String blockType, Long blockId) {
		ObjectNode blockInfoObject = mapper.createObjectNode();

		blockInfoObject.put("position", position);
		blockInfoObject.put("blockType", blockType);
		blockInfoObject.put("blockId", blockId);
		blockInfoObject.put("blockUUID", (String)null);

		jsonArray.add(blockInfoObject);
	}

	private String uploadFile(MultipartFile file) {

		if (file.isEmpty()) {   // 파일 첨부를 안했을 경우
			return null;
		}

		if (file.getOriginalFilename() == null) {   // 이름이 없는 파일일 경우
			throw new Exception404(ErrorMessage.INVALID_FILE_NAME);
		}
		String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename(); // 테스트용
		String fileUploadPth = getDirectoryPath() + fileName;

		try {
			file.transferTo(new File(fileUploadPth));
		} catch (IOException e) {
			throw new Exception500(ErrorMessage.FILE_UPLOAD_FAILED);
		}

		return fileName;
	}

	private String getDirectoryPath() {
		return fileDirectoryConfig.getDirectoryPath();
	}

	private void validationPdfMultipartFile(List<MultipartFile> files) {

		for (MultipartFile file : files) {
			if (file == null || file.isEmpty()) {
				throw new Exception404(ErrorMessage.FILE_IS_EMPTY);
			}

			String originalFilename = file.getOriginalFilename();
			if (originalFilename == null) {
				throw new Exception404(ErrorMessage.INVALID_FILE_NAME);
			}

			int dotIndex = originalFilename.lastIndexOf('.');
			if (dotIndex < 0 || !(originalFilename.substring(dotIndex + 1).equalsIgnoreCase("pdf"))) {
				throw new Exception404(ErrorMessage.INVALID_FILE_TYPE);
			}
		}
	}
}
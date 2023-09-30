package com.javajober.spaceWall.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.javajober.core.config.FileDirectoryConfig;
import com.javajober.core.error.exception.Exception404;
import com.javajober.core.error.exception.Exception500;
import com.javajober.core.message.ErrorMessage;

import com.javajober.fileBlock.domain.FileBlock;
import com.javajober.fileBlock.dto.request.FileBlockSaveRequest;
import com.javajober.fileBlock.repository.FileBlockRepository;
import com.javajober.freeBlock.domain.FreeBlock;
import com.javajober.freeBlock.dto.request.FreeBlockSaveRequest;
import com.javajober.freeBlock.repository.FreeBlockRepository;
import com.javajober.listBlock.domain.ListBlock;
import com.javajober.listBlock.dto.ListBlockSaveRequest;
import com.javajober.listBlock.listBlockRepository.ListBlockRepository;
import com.javajober.member.domain.MemberGroup;
import com.javajober.setting.domain.BackgroundSetting;
import com.javajober.setting.domain.BlockSetting;
import com.javajober.setting.domain.StyleSetting;
import com.javajober.setting.domain.ThemeSetting;
import com.javajober.setting.dto.BackgroundSettingSaveRequest;
import com.javajober.setting.dto.BlockSettingSaveRequest;
import com.javajober.setting.dto.StyleSettingSaveRequest;
import com.javajober.setting.dto.ThemeSettingSaveRequest;
import com.javajober.setting.repository.BackgroundSettingRepository;
import com.javajober.setting.repository.BlockSettingRepository;
import com.javajober.setting.repository.StyleSettingRepository;
import com.javajober.setting.repository.ThemeSettingRepository;
import com.javajober.snsBlock.domain.SNSBlock;
import com.javajober.snsBlock.dto.SNSBlockRequest;
import com.javajober.snsBlock.repository.SNSBlockRepository;
import com.javajober.spaceWall.domain.BlockType;
import com.javajober.spaceWall.domain.FlagType;
import com.javajober.spaceWall.domain.SpaceWall;
import com.javajober.spaceWall.domain.SpaceWallCategoryType;
import com.javajober.spaceWall.dto.request.SpaceWallRequest;
import com.javajober.spaceWall.dto.response.SpaceWallResponse;
import com.javajober.spaceWall.repository.SpaceWallRepository;
import com.javajober.template.domain.TemplateAuth;
import com.javajober.template.domain.TemplateBlock;
import com.javajober.template.dto.TemplateBlockRequest;
import com.javajober.template.repository.MemberGroupRepository;
import com.javajober.template.repository.TemplateAuthRepository;
import com.javajober.template.repository.TemplateBlockRepository;
import com.javajober.wallInfoBlock.domain.WallInfoBlock;
import com.javajober.wallInfoBlock.dto.request.WallInfoBlockRequest;
import com.javajober.wallInfoBlock.repository.WallInfoBlockRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class SpaceWallService {

	private final SpaceWallRepository spaceWallRepository;
	private final SNSBlockRepository snsBlockRepository;
	private final FreeBlockRepository freeBlockRepository;
	private final TemplateBlockRepository templateBlockRepository;
	private final MemberGroupRepository memberGroupRepository;
	private final TemplateAuthRepository templateAuthRepository;
	private final WallInfoBlockRepository wallInfoBlockRepository;
	private final FileBlockRepository fileBlockRepository;
	private final FileDirectoryConfig fileDirectoryConfig;
	private final ListBlockRepository listBlockRepository;
	private final StyleSettingRepository styleSettingRepository;
	private final BackgroundSettingRepository backgroundSettingRepository;
	private final BlockSettingRepository blockSettingRepository;
	private final ThemeSettingRepository themeSettingRepository;

	public SpaceWallService(SpaceWallRepository spaceWallRepository, SNSBlockRepository snsBlockRepository,
							FreeBlockRepository freeBlockRepository, TemplateBlockRepository templateBlockRepository,
							MemberGroupRepository memberGroupRepository, TemplateAuthRepository templateAuthRepository,
							WallInfoBlockRepository wallInfoBlockRepository, FileBlockRepository fileBlockRepository,
							FileDirectoryConfig fileDirectoryConfig, ListBlockRepository listBlockRepository,
		StyleSettingRepository styleSettingRepository, BackgroundSettingRepository backgroundSettingRepository,
		BlockSettingRepository blockSettingRepository, ThemeSettingRepository themeSettingRepository) {

		this.spaceWallRepository = spaceWallRepository;
		this.snsBlockRepository = snsBlockRepository;
		this.freeBlockRepository = freeBlockRepository;
		this.templateBlockRepository = templateBlockRepository;
		this.memberGroupRepository = memberGroupRepository;
		this.templateAuthRepository = templateAuthRepository;
		this.wallInfoBlockRepository = wallInfoBlockRepository;
		this.fileBlockRepository = fileBlockRepository;
		this.fileDirectoryConfig = fileDirectoryConfig;
		this.listBlockRepository = listBlockRepository;
		this.styleSettingRepository = styleSettingRepository;
		this.backgroundSettingRepository = backgroundSettingRepository;
		this.blockSettingRepository = blockSettingRepository;
		this.themeSettingRepository = themeSettingRepository;
	}

	public SpaceWallResponse checkSpaceWallTemporary(Long memberId, Long addSpaceId) {

		List<SpaceWall> spaceWalls = spaceWallRepository.findSpaceWalls(memberId, addSpaceId);

		if (spaceWalls == null || spaceWalls.isEmpty()) {
			return new SpaceWallResponse(null, false);
		}

		for (SpaceWall spaceWall : spaceWalls) {
			if (spaceWall.getFlag().equals(FlagType.PENDING) && spaceWall.getDeletedAt() == null) {
				return new SpaceWallResponse(spaceWall.getId(), true);
			}
			if (spaceWall.getFlag().equals(FlagType.SAVED) && spaceWall.getDeletedAt() == null) {
				throw new Exception404(ErrorMessage.SAVED_SPACE_WALL_ALREADY_EXISTS);
			}
		}

		return new SpaceWallResponse(null, false);
	}

	@Transactional
	public void save(final SpaceWallRequest spaceWallRequest) {

		WallInfoBlockRequest wallInfoBlockRequest = spaceWallRequest.getData().getWallInfoBlock();
		saveWallInfoBlock(wallInfoBlockRequest);

		StyleSettingSaveRequest styleSettingSaveRequest = spaceWallRequest.getData().getStyleSetting();
		saveStyleSetting(styleSettingSaveRequest);

		ObjectMapper mapper = new ObjectMapper();
		AtomicInteger i = new AtomicInteger();

		SpaceWallCategoryType spaceWallCategoryType = SpaceWallCategoryType.findSpaceWallCategoryTypeByString(spaceWallRequest.getData().getCategory());

		spaceWallRequest.getData().getBlocks().forEach(block -> {
			BlockType blockType = BlockType.findBlockTypeByString(block.getBlockType());
      
			switch (blockType) {
				case FREE_BLOCK:
					List<FreeBlockSaveRequest> freeBlockRequests = mapper.convertValue(block.getSubData(),
							new TypeReference<List<FreeBlockSaveRequest>>() {
							});
					saveFreeBlocks(freeBlockRequests);
					break;
				case SNS_BLOCK:
					List<SNSBlockRequest> snsBlockRequests = mapper.convertValue(block.getSubData(),
							new TypeReference<List<SNSBlockRequest>>() {
							});
					saveSnsBlocks(snsBlockRequests);
					break;
				case TEMPLATE_BLOCK:
					List<TemplateBlockRequest> templateBlockRequests = mapper.convertValue(block.getSubData(),
							new TypeReference<List<TemplateBlockRequest>>() {
							});
					saveTemplateBlock(templateBlockRequests);
					break;
				case FILE_BLOCK:
					List<FileBlockSaveRequest> fileBlockSaveRequests = mapper.convertValue(block.getSubData(),
							new TypeReference<List<FileBlockSaveRequest>>() {
							});
					saveFileBlocks(fileBlockSaveRequests);
				case LIST_BLOCK:
					List<ListBlockSaveRequest> listBlockRequests = mapper.convertValue(block.getSubData(),
						new TypeReference<List<ListBlockSaveRequest>>() {
						});
					saveListBlocks(listBlockRequests);
			}
		});
	}

	private void saveWallInfoBlock(WallInfoBlockRequest wallInfoBlockRequest) {

		WallInfoBlock wallInfoBlock = WallInfoBlockRequest.toEntity(wallInfoBlockRequest);
		wallInfoBlockRepository.save(wallInfoBlock);
	}

	private void saveFreeBlocks(List<FreeBlockSaveRequest> subData) {
		subData.forEach(block -> {
			FreeBlock freeBlock = FreeBlockSaveRequest.toEntity(block);
			freeBlockRepository.save(freeBlock);
		});
	}

	private void saveStyleSetting(StyleSettingSaveRequest saveRequest){
		StyleSetting styleSetting =saveRequest.toEntity();
		backgroundSettingRepository.save(styleSetting.getBackgroundSetting());
		blockSettingRepository.save(styleSetting.getBlockSetting());
		themeSettingRepository.save(styleSetting.getThemeSetting());
		styleSettingRepository.save(styleSetting);
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

	private void saveSnsBlocks(List<SNSBlockRequest> subData) {
		subData.forEach(block -> {
			SNSBlock snsBlock = SNSBlockRequest.toEntity(block);
			snsBlockRepository.save(snsBlock);
		});
	}

	private void saveTemplateBlock(List<TemplateBlockRequest> subData){
		subData.forEach(block -> {
			TemplateBlock templateBlock = TemplateBlockRequest.toEntity(block);
			templateBlockRepository.save(templateBlock);

			block.getAllAuthIds().forEach(authId -> {
				MemberGroup memberGroup = memberGroupRepository.getById(authId);
				Boolean hasAccess = block.getHasAccessTemplateAuth().contains(authId);
				TemplateAuth templateAuth = new TemplateAuth(memberGroup, hasAccess, templateBlock);
				templateAuthRepository.save(templateAuth);
			});
		});
	}

	private void saveFileBlocks(List<FileBlockSaveRequest> subData) {
		subData.forEach(block -> {
			FileBlock fileBlock = FileBlockSaveRequest.toEntity(block);
			fileBlockRepository.save(fileBlock);
		});
	}

	private void saveListBlocks(List<ListBlockSaveRequest> subData) {
		subData.forEach(block -> {
			ListBlock listBlock = ListBlockSaveRequest.toEntity(block);
			listBlockRepository.save(listBlock);
		});
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

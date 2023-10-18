package com.javajober.spaceWall.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.javajober.backgroundSetting.domain.BackgroundSetting;
import com.javajober.backgroundSetting.filedto.BackgroundSettingUpdateRequest;
import com.javajober.backgroundSetting.repository.BackgroundSettingRepository;
import com.javajober.blockSetting.domain.BlockSetting;
import com.javajober.blockSetting.dto.request.BlockSettingUpdateRequest;
import com.javajober.blockSetting.repository.BlockSettingRepository;
import com.javajober.core.component.FileImageService;
import com.javajober.fileBlock.domain.FileBlock;
import com.javajober.fileBlock.filedto.FileBlockSaveRequest;
import com.javajober.fileBlock.filedto.FileBlockUpdateRequest;
import com.javajober.fileBlock.repository.FileBlockRepository;
import com.javajober.freeBlock.domain.FreeBlock;
import com.javajober.freeBlock.dto.request.FreeBlockSaveRequest;
import com.javajober.freeBlock.dto.request.FreeBlockUpdateRequest;
import com.javajober.freeBlock.repository.FreeBlockRepository;
import com.javajober.listBlock.domain.ListBlock;
import com.javajober.listBlock.dto.request.ListBlockSaveRequest;
import com.javajober.listBlock.dto.request.ListBlockUpdateRequest;
import com.javajober.listBlock.repository.ListBlockRepository;
import com.javajober.member.domain.Member;
import com.javajober.member.repository.MemberRepository;
import com.javajober.snsBlock.domain.SNSBlock;
import com.javajober.snsBlock.domain.SNSType;
import com.javajober.snsBlock.dto.request.SNSBlockSaveRequest;
import com.javajober.snsBlock.dto.request.SNSBlockUpdateRequest;
import com.javajober.snsBlock.repository.SNSBlockRepository;
import com.javajober.space.domain.AddSpace;
import com.javajober.space.repository.AddSpaceRepository;
import com.javajober.spaceWall.domain.BlockType;
import com.javajober.spaceWall.domain.FlagType;
import com.javajober.spaceWall.domain.SpaceWall;
import com.javajober.spaceWall.dto.request.BlockSaveRequest;
import com.javajober.spaceWall.filedto.DataUpdateRequest;
import com.javajober.spaceWall.filedto.SpaceWallSaveRequest;
import com.javajober.spaceWall.dto.response.SpaceWallSaveResponse;
import com.javajober.spaceWall.filedto.SpaceWallUpdateRequest;
import com.javajober.spaceWall.repository.SpaceWallRepository;
import com.javajober.spaceWallCategory.domain.SpaceWallCategoryType;
import com.javajober.styleSetting.domain.StyleSetting;
import com.javajober.styleSetting.filedto.StyleSettingSaveRequest;
import com.javajober.styleSetting.filedto.StyleSettingUpdateRequest;
import com.javajober.styleSetting.repository.StyleSettingRepository;
import com.javajober.templateBlock.domain.TemplateBlock;
import com.javajober.templateBlock.dto.request.TemplateBlockSaveRequest;
import com.javajober.templateBlock.dto.request.TemplateBlockUpdateRequest;
import com.javajober.templateBlock.repository.TemplateBlockRepository;
import com.javajober.themeSetting.domain.ThemeSetting;
import com.javajober.themeSetting.dto.request.ThemeSettingUpdateRequest;
import com.javajober.themeSetting.repository.ThemeSettingRepository;
import com.javajober.wallInfoBlock.domain.WallInfoBlock;
import com.javajober.wallInfoBlock.filedto.WallInfoBlockSaveRequest;
import com.javajober.wallInfoBlock.filedto.WallInfoBlockUpdateRequest;
import com.javajober.wallInfoBlock.repository.WallInfoBlockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class FileUploadService {

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
    private final FileImageService fileImageService;

    public FileUploadService(final SpaceWallRepository spaceWallRepository, final SNSBlockRepository snsBlockRepository,
                             final FreeBlockRepository freeBlockRepository, final TemplateBlockRepository templateBlockRepository,
                             final WallInfoBlockRepository wallInfoBlockRepository, final FileBlockRepository fileBlockRepository,
                             final ListBlockRepository listBlockRepository, final FileImageService fileImageService,
                             final StyleSettingRepository styleSettingRepository, final BackgroundSettingRepository backgroundSettingRepository,
                             final BlockSettingRepository blockSettingRepository, final ThemeSettingRepository themeSettingRepository,
                             final MemberRepository memberRepository, final AddSpaceRepository addSpaceRepository) {

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
        this.fileImageService = fileImageService;
    }

    @Transactional
    public SpaceWallSaveResponse save(final SpaceWallSaveRequest spaceWallSaveRequest, FlagType flagType,
                                      final List<MultipartFile> files, final MultipartFile backgroundImgURL,
                                      final MultipartFile wallInfoImgURL, final MultipartFile styleImgURL) {

        SpaceWallCategoryType spaceWallCategoryType = SpaceWallCategoryType.findSpaceWallCategoryTypeByString(spaceWallSaveRequest.getData().getCategory());
        AddSpace addSpace = addSpaceRepository.findAddSpace(spaceWallSaveRequest.getData().getSpaceId());
        Member member = memberRepository.findMember(spaceWallSaveRequest.getData().getMemberId());

        Long blocksPosition = 2L;
        AtomicLong blocksPositionCounter = new AtomicLong(blocksPosition);
        ObjectMapper jsonMapper = new ObjectMapper();
        ArrayNode blockInfoArray = jsonMapper.createArrayNode();
        AtomicInteger i = new AtomicInteger();

        WallInfoBlockSaveRequest wallInfoBlockSaveRequest = spaceWallSaveRequest.getData().getWallInfoBlock();
        Long wallInfoBlock = saveWallInfoBlock(wallInfoBlockSaveRequest, backgroundImgURL, wallInfoImgURL);
        String wallInfoBlockType = BlockType.WALL_INFO_BLOCK.getEngTitle();
        Long blockStartPosition = 1L;
        addBlockToJsonArray(blockInfoArray, jsonMapper, blockStartPosition, wallInfoBlockType, wallInfoBlock);

        spaceWallSaveRequest.getData().getBlocks().forEach(block -> {
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
                    List<SNSBlockSaveRequest> snsBlockSaveRequests = jsonMapper.convertValue(block.getSubData(),
                            new TypeReference<List<SNSBlockSaveRequest>>() {
                            });
                    List<Long> snsBlockIds = saveSnsBlocks(snsBlockSaveRequests);
                    snsBlockIds.forEach(snsBlockId -> addBlockInfoToArray(blockInfoArray, jsonMapper, blockType, position, snsBlockId, block));
                    break;
                case TEMPLATE_BLOCK:
                    List<TemplateBlockSaveRequest> templateBlockSaveRequests = jsonMapper.convertValue(block.getSubData(),
                            new TypeReference<List<TemplateBlockSaveRequest>>() {
                            });
                    List<Long> templateBlockIds = saveTemplateBlocks(templateBlockSaveRequests);
                    templateBlockIds.forEach(templateBlockId -> addBlockInfoToArray(blockInfoArray, jsonMapper, blockType, position, templateBlockId, block));
                    break;
                case FILE_BLOCK:
                    List<FileBlockSaveRequest> fileBlockSaveRequests = jsonMapper.convertValue(block.getSubData(),
                            new TypeReference<List<FileBlockSaveRequest>>() {
                            });
                    List<Long> fileBlockIds = saveFileBlocks(fileBlockSaveRequests, files.get(i.getAndIncrement()));
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

        StyleSettingSaveRequest styleSettingSaveRequest = spaceWallSaveRequest.getData().getStyleSetting();
        Long styleSetting = saveStyleSetting(styleSettingSaveRequest, styleImgURL);
        String styleSettingString = "styleSetting";
        Long stylePosition = blocksPositionCounter.getAndIncrement();
        addBlockToJsonArray(blockInfoArray, jsonMapper, stylePosition, styleSettingString, styleSetting);

        String blockInfoArrayAsString = blockInfoArray.toString();
        String shareURL = spaceWallSaveRequest.getData().getShareURL();
        SpaceWall spaceWall = SpaceWallSaveRequest.toEntity(spaceWallCategoryType, member, addSpace, shareURL, flagType, blockInfoArrayAsString);

        Long spaceWallId = spaceWallRepository.save(spaceWall).getId();

        return new SpaceWallSaveResponse(spaceWallId);
    }

    @Transactional
    public SpaceWallSaveResponse update(final SpaceWallUpdateRequest spaceWallRequest, FlagType flagType,
        final List<MultipartFile> files, final MultipartFile backgroundImgURL,
        final MultipartFile wallInfoImgURL, final MultipartFile styleImgURL){

        DataUpdateRequest dataUpdateRequest = spaceWallRequest.getData();

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
        AtomicInteger i = new AtomicInteger();

        WallInfoBlockUpdateRequest wallInfoBlockRequest = spaceWallRequest.getData().getWallInfoBlock();
        Long wallInfoBlock = updateWallInfoBlock(wallInfoBlockRequest, backgroundImgURL, wallInfoImgURL);
        String wallInfoBlockType  = BlockType.WALL_INFO_BLOCK.getEngTitle();
        Long blockStartPosition = 1L;
        addBlockToJsonArray(blockInfoArray, jsonMapper, blockStartPosition, wallInfoBlockType, wallInfoBlock);

        spaceWallRequest.getData().getBlocks().forEach(block -> {
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
                    List<FileBlockUpdateRequest> fileBlockRequests = jsonMapper.convertValue(block.getSubData(), new TypeReference<List<FileBlockUpdateRequest>>() {});
                    List<Long> updateFileBlockIds = updateFileBlocks(fileBlockRequests, files.get(i.getAndIncrement()));
                    updateFileBlockIds.forEach(fileBlockId -> addBlockInfoToArray(blockInfoArray, jsonMapper, blockType, position, fileBlockId, block));
                    break;
                case LIST_BLOCK:
                    List<ListBlockUpdateRequest> listBlockRequests = jsonMapper.convertValue(block.getSubData(), new TypeReference<List<ListBlockUpdateRequest>>() {});
                    List<Long> updateListBlockIds = updateListBlock(listBlockRequests);
                    updateListBlockIds.forEach(listBlockId -> addBlockInfoToArray(blockInfoArray, jsonMapper, blockType, position, listBlockId, block));
            }
        });

        StyleSettingUpdateRequest styleSettingUpdateRequest = dataUpdateRequest.getStyleSetting();
        Long styleSetting = updateStyleSetting(styleSettingUpdateRequest, styleImgURL);
        String styleSettingString = "styleSetting";
        Long stylePosition = blocksPositionCounter.getAndIncrement();

        addBlockToJsonArray(blockInfoArray, jsonMapper, stylePosition, styleSettingString, styleSetting);
        String blocks = blockInfoArray.toString();

        spaceWall.fileUpdate(dataUpdateRequest, flagType, blocks);
        spaceWallId = spaceWallRepository.save(spaceWall).getId();

        return new SpaceWallSaveResponse(spaceWallId);
    }

    private Long saveWallInfoBlock(final WallInfoBlockSaveRequest wallInfoBlockSaveRequest, MultipartFile backgroundImgURL, MultipartFile wallInfoImgURL) {

        String backgroundImgName = fileImageService.uploadFile(backgroundImgURL);
        String wallInfoImgName = fileImageService.uploadFile(wallInfoImgURL);
        WallInfoBlock wallInfoBlock = WallInfoBlockSaveRequest.toEntity(wallInfoBlockSaveRequest, backgroundImgName, wallInfoImgName);

        return wallInfoBlockRepository.save(wallInfoBlock).getId();
    }

    private List<Long> saveFreeBlocks(final List<FreeBlockSaveRequest> subData) {

        List<Long> freeBlockIds = new ArrayList<>();
        subData.forEach(block -> {
            FreeBlock freeBlock = FreeBlockSaveRequest.toEntity(block);
            freeBlockIds.add(freeBlockRepository.save(freeBlock).getId());
        });
        return freeBlockIds;
    }

    private List<Long> saveSnsBlocks(final List<SNSBlockSaveRequest> subData) {

        List<Long> snsBlockIds = new ArrayList<>();

        subData.forEach(block -> {
            SNSBlock snsBlock = SNSBlockSaveRequest.toEntity(block);
            snsBlockIds.add(snsBlockRepository.save(snsBlock).getId());
        });
        return snsBlockIds;
    }

    private List<Long> saveTemplateBlocks(final List<TemplateBlockSaveRequest> subData) {

        List<Long> templateBlockIds = new ArrayList<>();

        subData.forEach(block -> {
            TemplateBlock templateBlock = TemplateBlockSaveRequest.toEntity(block);
            templateBlockIds.add(templateBlockRepository.save(templateBlock).getId());
        });
        return templateBlockIds;
    }

    private List<Long> saveFileBlocks(final List<FileBlockSaveRequest> subData, MultipartFile file) {

        String fileName = fileImageService.uploadFile(file);
        List<Long> fileBlockIds = new ArrayList<>();
        subData.forEach(block -> {
            FileBlock fileBlock = FileBlockSaveRequest.toEntity(block, fileName);
            fileBlockIds.add(fileBlockRepository.save(fileBlock).getId());
        });
        return fileBlockIds;
    }

    private List<Long> saveListBlocks(final List<ListBlockSaveRequest> subData) {

        List<Long> listBlockIds = new ArrayList<>();
        subData.forEach(block -> {
            ListBlock listBlock = ListBlockSaveRequest.toEntity(block);
            listBlockIds.add(listBlockRepository.save(listBlock).getId());
        });
        return listBlockIds;
    }

    private Long saveStyleSetting(final StyleSettingSaveRequest saveRequest, MultipartFile styleImgURL){

        String styleImgName = fileImageService.uploadFile(styleImgURL);

        BackgroundSetting savedBackgroundSetting = backgroundSettingRepository.save(saveRequest.getBackgroundSetting().toEntity(styleImgName));
        BlockSetting savedBlockSetting = blockSettingRepository.save(saveRequest.getBlockSetting().toEntity());
        ThemeSetting savedThemeSetting = themeSettingRepository.save(saveRequest.getThemeSetting().toEntity());

        StyleSetting styleSetting = StyleSetting.builder()
                .backgroundSetting(savedBackgroundSetting)
                .blockSetting(savedBlockSetting)
                .themeSetting(savedThemeSetting)
                .build();

        return styleSettingRepository.save(styleSetting).getId();
    }

    private Long updateWallInfoBlock(final WallInfoBlockUpdateRequest wallInfoBlockRequest, MultipartFile backgroundImgURL, MultipartFile wallInfoImgURL) {

        WallInfoBlock wallInfoBlock = wallInfoBlockRepository.findWallInfoBlock(wallInfoBlockRequest.getWallInfoBlockId());
        String backgroundImgName = fileImageService.uploadFile(backgroundImgURL);
        String wallInfoImgName = fileImageService.uploadFile(wallInfoImgURL);
        wallInfoBlock.update(backgroundImgName, wallInfoImgName, wallInfoBlockRequest.getWallInfoTitle(), wallInfoBlockRequest.getWallInfoDescription());

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
                freeBlock.update(updateRequest.getFreeTitle(), updateRequest.getFreeContent());
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
                SNSType snsType = SNSType.findSNSTypeByString(snsBlockRequest.getSnsType());
                snsBlock.update(snsBlockRequest.getSnsUUID(), snsType, snsBlockRequest.getSnsURL());
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

    private List<Long> updateFileBlocks(final List<FileBlockUpdateRequest> subData, MultipartFile file) {

        String fileName = fileImageService.uploadFile(file);
        List<Long> updateFileBlockIds = new ArrayList<>();
        for (FileBlockUpdateRequest updateRequest : subData) {
            if(updateRequest.getFileBlockId() == null){
                FileBlock fileBlock = new FileBlock(updateRequest.getFileTitle(), updateRequest.getFileDescription(),fileName);
                updateFileBlockIds.add(fileBlockRepository.save(fileBlock).getId());
            }else{
                FileBlock fileBlock = fileBlockRepository.findFileBlock(updateRequest.getFileBlockId());
                fileBlock.fileUpdate(updateRequest.getFileTitle(), updateRequest.getFileDescription(), fileName);
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
                listBlock.update(updateRequest.getListUUID(), updateRequest.getListLabel(), updateRequest.getListTitle(), updateRequest.getListDescription(), updateRequest.getIsLink());
                updateListBlockIds.add(listBlockRepository.save(listBlock).getId());
            }
        }
        return updateListBlockIds;
    }

    private Long updateStyleSetting(final StyleSettingUpdateRequest updateRequest, MultipartFile styleImgURL){

        StyleSetting styleSetting = styleSettingRepository.findStyleBlock(updateRequest.getStyleSettingBlockId());
        updateBackgroundSetting(updateRequest.getBackgroundSetting(), styleImgURL);
        updateBlockSetting(updateRequest.getBlockSetting());
        updateThemeSetting(updateRequest.getThemeSetting());
        styleSetting.update(styleSetting);
        return styleSettingRepository.save(styleSetting).getId();
    }

    private Long updateBackgroundSetting(final BackgroundSettingUpdateRequest updateRequest, MultipartFile styleImgURL){

        String styleImgName = fileImageService.uploadFile(styleImgURL);
        BackgroundSetting backgroundSetting = backgroundSettingRepository.getById(updateRequest.getBackgroundSettingBlockId());
        backgroundSetting.update(updateRequest.getSolidColor(), updateRequest.getGradation(), styleImgName);
        return backgroundSettingRepository.save(backgroundSetting).getId();
    }

    private Long updateBlockSetting(final BlockSettingUpdateRequest updateRequest){

        BlockSetting blockSetting = blockSettingRepository.getById(updateRequest.getBlockSettingBlockId());
        blockSetting.update(updateRequest.getShape(), updateRequest.getStyle(),updateRequest.getStyleColor(),updateRequest.getGradation());
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
        blockInfoObject.put("blockUUID", (String) null);

        jsonArray.add(blockInfoObject);
    }
}
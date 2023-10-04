package com.javajober.spaceWall.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.javajober.backgroundSetting.domain.BackgroundSetting;
import com.javajober.backgroundSetting.repository.BackgroundSettingRepository;
import com.javajober.blockSetting.domain.BlockSetting;
import com.javajober.blockSetting.repository.BlockSettingRepository;
import com.javajober.core.component.FileImageService;
import com.javajober.fileBlock.domain.FileBlock;
import com.javajober.fileBlock.filedto.FileBlockSaveRequest;
import com.javajober.fileBlock.repository.FileBlockRepository;
import com.javajober.freeBlock.domain.FreeBlock;
import com.javajober.freeBlock.dto.request.FreeBlockSaveRequest;
import com.javajober.freeBlock.repository.FreeBlockRepository;
import com.javajober.listBlock.domain.ListBlock;
import com.javajober.listBlock.dto.request.ListBlockSaveRequest;
import com.javajober.listBlock.repository.ListBlockRepository;
import com.javajober.member.domain.Member;
import com.javajober.member.repository.MemberRepository;
import com.javajober.snsBlock.domain.SNSBlock;
import com.javajober.snsBlock.dto.request.SNSBlockRequest;
import com.javajober.snsBlock.repository.SNSBlockRepository;
import com.javajober.space.domain.AddSpace;
import com.javajober.space.repository.AddSpaceRepository;
import com.javajober.spaceWall.domain.BlockType;
import com.javajober.spaceWall.domain.FlagType;
import com.javajober.spaceWall.domain.SpaceWall;
import com.javajober.spaceWall.dto.request.BlockRequest;
import com.javajober.spaceWall.dto.request.SpaceWallRequest;
import com.javajober.spaceWall.dto.response.SpaceWallSaveResponse;
import com.javajober.spaceWall.repository.SpaceWallRepository;
import com.javajober.spaceWallCategory.domain.SpaceWallCategoryType;
import com.javajober.styleSetting.domain.StyleSetting;
import com.javajober.styleSetting.dto.request.StyleSettingSaveRequest;
import com.javajober.styleSetting.repository.StyleSettingRepository;
import com.javajober.templateBlock.domain.TemplateBlock;
import com.javajober.templateBlock.dto.request.TemplateBlockRequest;
import com.javajober.templateBlock.repository.TemplateBlockRepository;
import com.javajober.themeSetting.domain.ThemeSetting;
import com.javajober.themeSetting.repository.ThemeSettingRepository;
import com.javajober.wallInfoBlock.domain.WallInfoBlock;
import com.javajober.wallInfoBlock.dto.request.WallInfoBlockRequest;
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

    public FileUploadService(SpaceWallRepository spaceWallRepository, SNSBlockRepository snsBlockRepository,
                            FreeBlockRepository freeBlockRepository, TemplateBlockRepository templateBlockRepository,
                            WallInfoBlockRepository wallInfoBlockRepository, FileBlockRepository fileBlockRepository,
                            ListBlockRepository listBlockRepository, FileImageService fileImageService,
                            StyleSettingRepository styleSettingRepository, BackgroundSettingRepository backgroundSettingRepository,
                            BlockSettingRepository blockSettingRepository, ThemeSettingRepository themeSettingRepository,
                            MemberRepository memberRepository, AddSpaceRepository addSpaceRepository) {

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
    public SpaceWallSaveResponse save(final SpaceWallRequest spaceWallRequest, FlagType flagType,
                                      final List<MultipartFile> files, final MultipartFile backgroundImgURL,
                                      final MultipartFile wallInfoImgURL, final MultipartFile styleImgURL) {

        SpaceWallCategoryType spaceWallCategoryType = SpaceWallCategoryType.findSpaceWallCategoryTypeByString(spaceWallRequest.getData().getCategory());
        AddSpace addSpace = addSpaceRepository.findAddSpace(spaceWallRequest.getData().getSpaceId());
        Member member = memberRepository.findMember(spaceWallRequest.getData().getMemberId());

        Long blocksPosition = 2L;
        AtomicLong blocksPositionCounter = new AtomicLong(blocksPosition);
        ObjectMapper jsonMapper = new ObjectMapper();
        ArrayNode blockInfoArray = jsonMapper.createArrayNode();
        AtomicInteger i = new AtomicInteger();

        WallInfoBlockRequest wallInfoBlockRequest = spaceWallRequest.getData().getWallInfoBlock();
        Long wallInfoBlock = saveWallInfoBlock(wallInfoBlockRequest, backgroundImgURL, wallInfoImgURL);
        String wallInfoBlockType = BlockType.WALL_INFO_BLOCK.getEngTitle();
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

        StyleSettingSaveRequest styleSettingSaveRequest = spaceWallRequest.getData().getStyleSetting();
        Long styleSetting = saveStyleSetting(styleSettingSaveRequest, styleImgURL);
        String styleSettingString = "styleSetting";
        Long stylePosition = blocksPositionCounter.getAndIncrement();
        addBlockToJsonArray(blockInfoArray, jsonMapper, stylePosition, styleSettingString, styleSetting);

        String blockInfoArrayAsString = blockInfoArray.toString();
        String shareURL = spaceWallRequest.getData().getShareURL();
        SpaceWall spaceWall = SpaceWallRequest.toEntity(spaceWallCategoryType, member, addSpace, shareURL, flagType, blockInfoArrayAsString);

        Long spaceWallId = spaceWallRepository.save(spaceWall).getId();

        return new SpaceWallSaveResponse(spaceWallId);
    }

    private Long saveWallInfoBlock(WallInfoBlockRequest wallInfoBlockRequest, MultipartFile backgroundImgURL, MultipartFile wallInfoImgURL) {
        String backgroundImgName = fileImageService.uploadFile(backgroundImgURL);
        String wallInfoImgName = fileImageService.uploadFile(wallInfoImgURL);
        WallInfoBlock wallInfoBlock = WallInfoBlockRequest.toEntity(wallInfoBlockRequest, backgroundImgName, wallInfoImgName);

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

    private List<Long> saveTemplateBlocks(List<TemplateBlockRequest> subData) {
        List<Long> templateBlockIds = new ArrayList<>();

        subData.forEach(block -> {
            TemplateBlock templateBlock = TemplateBlockRequest.toEntity(block);
            templateBlockIds.add(templateBlockRepository.save(templateBlock).getId());
        });
        return templateBlockIds;
    }

    private List<Long> saveFileBlocks(List<FileBlockSaveRequest> subData, MultipartFile file) {
        String fileName = fileImageService.uploadFile(file);
        List<Long> fileBlockIds = new ArrayList<>();
        subData.forEach(block -> {
            FileBlock fileBlock = FileBlockSaveRequest.toEntity(block, fileName);
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

    private Long saveStyleSetting(StyleSettingSaveRequest saveRequest, MultipartFile styleImgURL){
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
        blockInfoObject.put("blockUUID", (String) null);

        jsonArray.add(blockInfoObject);
    }
}

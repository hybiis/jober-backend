package com.javajober.spaceWall.domain;

import java.util.Arrays;

import com.javajober.core.exception.ApiStatus;
import com.javajober.core.exception.ApplicationException;

import lombok.Getter;

@Getter
public enum BlockType {

    FILE_BLOCK("fileBlock", "파일 블록","FileBlockStrategy"),
    FREE_BLOCK("freeBlock", "자유 블록", "FreeBlockStrategy"),
    LIST_BLOCK("listBlock", "리스트 블록", "ListBlockStrategy"),
    SNS_BLOCK("snsBlock", "소셜 블록", "SNSBlockStrategy"),
    STYLE_SETTING("styleSetting","스타일 블록","StyleSettingStrategy"),
    TEMPLATE_BLOCK("templateBlock", "템플릿 블록", "TemplateBlockStrategy"),
    WALL_INFO_BLOCK("wallInfoBlock", "공유페이지 소개 블록", "WallInfoBlockStrategy"),
    EMPTY("empty","없음","");

    private final String engTitle;
    private final String korTitle;
    private final String strategyName;

    BlockType(final String engTitle, final String korTitle, final String strategyName) {
        this.engTitle = engTitle;
        this.korTitle = korTitle;
        this.strategyName = strategyName;
    }

    public static BlockType findBlockTypeByString(final String type) {
        return Arrays.stream(values())
            .filter(blockType -> blockType.getEngTitle().equals(type))
            .findAny()
            .orElseThrow(() -> new ApplicationException(ApiStatus.INVALID_DATA, "유효하지 않은 블록 타입입니다."));
    }
}
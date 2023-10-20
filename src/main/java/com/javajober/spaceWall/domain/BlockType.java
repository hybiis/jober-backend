package com.javajober.spaceWall.domain;

import java.util.Arrays;

import com.javajober.core.exception.ApiStatus;
import com.javajober.core.exception.ApplicationException;

import lombok.Getter;

@Getter
public enum BlockType {

    WALL_INFO_BLOCK("wallInfoBlock", "공유페이지 소개 블록"),
    LIST_BLOCK("listBlock", "리스트 블록"),
    FREE_BLOCK("freeBlock", "자유 블록"),
    TEMPLATE_BLOCK("templateBlock", "템플릿 블록"),
    SNS_BLOCK("snsBlock", "소셜 블록"),
    FILE_BLOCK("fileBlock", "파일 블록"),
    STYLE_SETTING("styleSetting", "스타일 블록"),
    EMPTY("empty", "없음");

    private final String engTitle;
    private final String korTitle;

    BlockType(final String engTitle, final String korTitle) {
        this.engTitle = engTitle;
        this.korTitle = korTitle;
    }

    public static BlockType findBlockTypeByString(final String type) {
        return Arrays.stream(values())
                .filter(blockType -> blockType.getEngTitle().equals(type))
                .findAny()
                .orElseThrow(() -> new ApplicationException(ApiStatus.INVALID_DATA,"유효하지 않은 블록 타입입니다"));
    }
}
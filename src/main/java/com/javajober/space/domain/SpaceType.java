package com.javajober.space.domain;

import com.fasterxml.jackson.annotation.JsonValue;

import com.javajober.core.exception.ApiStatus;
import com.javajober.core.exception.ApplicationException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum SpaceType {

    PERSONAL("personal", "개인"),
    ORGANIZATION("organization","단체");

    private final String engTitle;
    private final String korTitle;

    SpaceType(final String engTitle, final String korTitle) {
        this.engTitle = engTitle;
        this.korTitle = korTitle;
    }

    @JsonValue
    public String getEngTitle() {
        return engTitle;
    }

    public static SpaceType findSpaceTypeByString(final String type) {
        return Arrays.stream(values())
                .filter(spaceType -> spaceType.getEngTitle().equals(type))
                .findAny()
                .orElseThrow(() -> new ApplicationException(ApiStatus.INVALID_DATA, "존재하지 않는 space type입니다."));
    }
}
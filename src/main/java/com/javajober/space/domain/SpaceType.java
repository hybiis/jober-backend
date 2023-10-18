package com.javajober.space.domain;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;

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
}
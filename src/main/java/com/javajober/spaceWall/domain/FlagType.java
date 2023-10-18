package com.javajober.spaceWall.domain;

import lombok.Getter;

@Getter
public enum FlagType {

    PENDING("임시 저장"),
    SAVED("저장");

    private final String description;

    private FlagType(final String description) {
        this.description = description;
    }
}
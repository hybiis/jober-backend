package com.javajober.member.memberGroup.domain;

import lombok.Getter;

@Getter
public enum MemberHashtagType {

    FRIEND("친구"),
    COLLEAGUE("직장동료"),
    EMPTY("없음");

    private final String description;

    private MemberHashtagType(final String description) {
        this.description = description;
    }
}
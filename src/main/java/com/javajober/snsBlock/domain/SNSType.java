package com.javajober.snsBlock.domain;

import java.util.Arrays;

import lombok.Getter;

@Getter
public enum SNSType {

	INSTAGRAM("instagram", "인스타그램"),
	FACEBOOK("facebook", "페이스북"),
	LINKEDIN("linkedin", "링크드인"),
	BEHANCE("behance", "비핸스"),
    EMPTY("empty", "없음");

	private final String engTitle;
	private final String korTitle;

	SNSType(final String engTitle, final String korTitle) {
		this.engTitle = engTitle;
		this.korTitle = korTitle;
	}

    public static SNSType findSNSTypeByString(final String type) {
        return Arrays.stream(values())
            .filter(snsType -> snsType.getEngTitle().equals(type))
            .findAny()
            .orElse(EMPTY);
    }
}


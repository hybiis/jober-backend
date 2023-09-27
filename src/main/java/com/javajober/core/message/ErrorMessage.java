package com.javajober.core.message;

import lombok.Getter;

@Getter
public enum ErrorMessage {

	MEMBER_NOT_FOUND("존재하지 않는 회원 정보입니다."),
	ADD_SPACE_NOT_FOUND("존재하지 않는 스페이스 입니다."),

	FCM_INITIALIZATION_FAILED("FCM 초기화에 실패하였습니다."),

	FAILED_TO_DELETE_THE_FILE("파일 삭제를 실패하였습니다."),
	SAVED_SPACE_WALL_ALREADY_EXISTS("저장된 공유페이지가 있습니다."),

	TEMPLATE_AUTH_NOT_FOUND("템플릿 권한 정보를 찾을 수 없습니다."),
	TEMPLATE_RECOMMEND_NOT_FOUND("추천 템플릿 그룹을 찾을 수 없습니다."),
	TEMPLATE_CATEGORY_NOT_FOUND("해당 카테고리를 찾을 수 없습니다."),
	TEMPLATE_SEARCH_NOT_FOUND("해당 검색어를 찾을 수 없습니다."),
	SPACE_MEMBER_GROUP_NOT_FOUND("해당 스페이스 연락처를 찾을 수 없습니다."),
	TEMPLATE_BLOCK_NOT_FOUND("템플릿 블럭을 찾을 수 없습니다."),
	NOT_FOUND("요청하신 데이터를 찾을 수 없습니다.");

	private final String message;

	ErrorMessage(String message) {
		this.message = message;
	}

}


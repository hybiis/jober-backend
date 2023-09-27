package com.javajober.core.message;

import lombok.Getter;

@Getter
public enum SuccessMessage {


	HOME_SUCCESS("홈 조회를 성공했습니다."),
	SPACE_WALL_TEMPORARY_QUERY_SUCCESS("임시 저장 조회를 성공했습니다."),

	TEMPLATE_AUTH_SUCCESS("권한설정을 위한 유저정보 조회를 성공했습니다."),
	TEMPLATE_RECOMMEND_SUCCESS("추천템플릿 조회를 성공했습니다."),
	TEMPLATE_SEARCH_SUCCESS("템플릿 검색을 성공했습니다."),
	TEMPLATE_CATEGORY_SUCCESS("카테고리별 템플릿 리스트 조회를 성공했습니다."),

	CREATE_SUCCESS("저장이 완료되었습니다."),
	READ_SUCCESS("성공적으로 조회되었습니다."),
	UPDATE_SUCCESS("성공적으로 수정되었습니다."),
	DELETE_SUCCESS("성공적으로 삭제되었습니다."),

	TEMPLATE_BLOCK_SAVE_SUCCESS("템플릿 블록 저장에 성공했습니다."),
	TEMPLATE_BLOCK_READ_SUCCESS("템플릿 블록 조회에 성공했습니다."),
	TEMPLATE_BLOCK_UPDATE_SUCCESS("템플릿 블록 수정에 성공했습니다."),
	TEMPLATE_BLOCK_DELETE_SUCCESS("템플릿 블록 삭제에 성공했습니다.");


	private final String message;

	SuccessMessage(String message) {
		this.message = message;
	}
}

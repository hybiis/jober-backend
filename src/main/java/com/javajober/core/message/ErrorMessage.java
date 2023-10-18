package com.javajober.core.message;

import lombok.Getter;

@Getter
public enum ErrorMessage {

	MEMBER_NOT_FOUND("존재하지 않는 회원 정보입니다."),
	ADD_SPACE_NOT_FOUND("존재하지 않는 스페이스 입니다."),

	FCM_INITIALIZATION_FAILED("FCM 초기화에 실패하였습니다."),

	INVALID_SPACE_TYPE("잘못된 spaceType 파라미터입니다."),

	FREE_BLOCK_NOT_FOUND("존재하지 않는 자유 블록입니다."),
	FILE_BLOCK_NOT_FOUND("존재하지 않는 파일 블록입니다."),
	FAILED_DELETE_FILE("파일 삭제를 실패하였습니다."),
	FILE_UPLOAD_FAILED("파일 업로드에 실패하였습니다."),
	FILE_IS_EMPTY("업로드할 파일이 없습니다."),
	INVALID_FILE_TYPE("pdf 파일만 첨부 가능합니다."),
	INVALID_FILE_NAME("파일 이름이 없습니다."),
	FAILED_TO_DELETE_THE_FILE("파일 삭제를 실패하였습니다."),
	SAVED_SPACE_WALL_ALREADY_EXISTS("저장된 공유페이지가 있습니다."),

	TEMPLATE_AUTH_NOT_FOUND("템플릿 권한 정보를 찾을 수 없습니다."),
	TEMPLATE_RECOMMEND_NOT_FOUND("추천 템플릿 그룹을 찾을 수 없습니다."),
	TEMPLATE_CATEGORY_NOT_FOUND("해당 카테고리를 찾을 수 없습니다."),
	TEMPLATE_SEARCH_NOT_FOUND("해당 검색어를 찾을 수 없습니다."),
	SPACE_MEMBER_GROUP_NOT_FOUND("해당 스페이스 연락처를 찾을 수 없습니다."),
	TEMPLATE_BLOCK_NOT_FOUND("템플릿 블럭을 찾을 수 없습니다."),

	INVALID_BLOCK_TYPE("유효하지 않은 블록 타입입니다."),
	INVALID_SPACE_WALL_CATEGORY_TYPE("유효하지 않은 카테고리 타입입니다."),

	SPACE_WALL_NOT_FOUND("존재하지 않는 공유페이지 입니다."),
	SPACE_WALL_TEMPORARY_NOT_FOUND("임시 저장된 공유페이지가 없습니다."),

	LIST_BLOCK_NOT_FOUND("존재하지 않는 리스트 블록입니다."),
	STYLE_SETTING_BLOCK_NOT_FOUND("존재하지 않는 스타일 블록입니다."),
	WALL_INFO_BLOCK_NOT_FOUND("존재하지 않는 소개 블록입니다."),
	SHARE_URL_NOT_FOUND("존재하지 않는 URL입니다."),
	JSON_PROCESSING_ERROR("Json을 변환 중 오류가 발생했습니다."),

	NOT_FOUND("요청하신 데이터를 찾을 수 없습니다.");

	private final String message;

	ErrorMessage(String message) {
		this.message = message;
	}

}
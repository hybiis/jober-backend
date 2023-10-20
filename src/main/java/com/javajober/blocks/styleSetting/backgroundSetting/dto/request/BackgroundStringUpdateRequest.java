package com.javajober.blocks.styleSetting.backgroundSetting.dto.request;

import lombok.Getter;

@Getter
public class BackgroundStringUpdateRequest {
	private Long backgroundSettingBlockId;
	private String solidColor;
	private Boolean gradation;
	private String styleImgURL;

	public BackgroundStringUpdateRequest(){

	}
}
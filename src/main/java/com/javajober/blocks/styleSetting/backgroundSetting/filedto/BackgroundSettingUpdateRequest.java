package com.javajober.blocks.styleSetting.backgroundSetting.filedto;

import lombok.Getter;

@Getter
public class BackgroundSettingUpdateRequest {

	private Long backgroundSettingBlockId;
	private String solidColor;
	private Boolean gradation;

	public BackgroundSettingUpdateRequest(){

	}
}
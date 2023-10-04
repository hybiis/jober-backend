package com.javajober.backgroundSetting.filedto;

import com.javajober.backgroundSetting.domain.BackgroundSetting;

import lombok.Getter;

@Getter
public class BackgroundSettingUpdateRequest {

	private Long backgroundSettingBlockId;
	private String solidColor;
	private Boolean gradation;

	public BackgroundSettingUpdateRequest(){

	}

}

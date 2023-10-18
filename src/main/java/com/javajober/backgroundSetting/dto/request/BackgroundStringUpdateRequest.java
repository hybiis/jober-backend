package com.javajober.backgroundSetting.dto.request;

import com.javajober.backgroundSetting.domain.BackgroundSetting;
import com.javajober.backgroundSetting.filedto.BackgroundSettingUpdateRequest;

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
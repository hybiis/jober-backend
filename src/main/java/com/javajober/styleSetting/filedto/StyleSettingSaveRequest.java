package com.javajober.styleSetting.filedto;
 ;
 import com.javajober.backgroundSetting.domain.BackgroundSetting;
 import com.javajober.backgroundSetting.filedto.BackgroundSettingSaveRequest;
 import com.javajober.blockSetting.domain.BlockSetting;
 import com.javajober.blockSetting.dto.request.BlockSettingSaveRequest;
 import com.javajober.styleSetting.domain.StyleSetting;
 import com.javajober.themeSetting.domain.ThemeSetting;
 import com.javajober.themeSetting.dto.request.ThemeSettingSaveRequest;

 import lombok.Getter;

@Getter
public class StyleSettingSaveRequest {

	private BackgroundSettingSaveRequest backgroundSetting;
	private BlockSettingSaveRequest blockSetting;
	private ThemeSettingSaveRequest themeSetting;

	public StyleSettingSaveRequest(){

	}

	public StyleSetting toEntity(BackgroundSetting backgroundSetting, BlockSetting blockSetting, ThemeSetting themeSetting) {
		return StyleSetting.builder()
				.backgroundSetting(backgroundSetting)
				.blockSetting(blockSetting)
				.themeSetting(themeSetting)
				.build();
	}
}

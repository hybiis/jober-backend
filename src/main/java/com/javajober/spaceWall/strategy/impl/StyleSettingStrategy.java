package com.javajober.spaceWall.strategy.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.javajober.blocks.styleSetting.backgroundSetting.dto.response.BackgroundSettingResponse;
import com.javajober.blocks.styleSetting.blockSetting.dto.response.BlockSettingResponse;
import com.javajober.blocks.styleSetting.dto.response.StyleSettingResponse;
import com.javajober.blocks.styleSetting.themeSetting.dto.response.ThemeSettingResponse;
import com.javajober.core.util.response.CommonResponse;
import org.springframework.stereotype.Component;

import com.javajober.blocks.styleSetting.backgroundSetting.domain.BackgroundSetting;
import com.javajober.blocks.styleSetting.backgroundSetting.dto.request.BackgroundSettingStringSaveRequest;
import com.javajober.blocks.styleSetting.backgroundSetting.repository.BackgroundSettingRepository;
import com.javajober.blocks.styleSetting.blockSetting.domain.BlockSetting;
import com.javajober.blocks.styleSetting.blockSetting.dto.request.BlockSettingSaveRequest;
import com.javajober.blocks.styleSetting.blockSetting.repository.BlockSettingRepository;
import com.javajober.blocks.styleSetting.domain.StyleSetting;
import com.javajober.blocks.styleSetting.dto.request.StyleSettingStringSaveRequest;
import com.javajober.blocks.styleSetting.repository.StyleSettingRepository;
import com.javajober.blocks.styleSetting.themeSetting.domain.ThemeSetting;
import com.javajober.blocks.styleSetting.themeSetting.dto.request.ThemeSettingSaveRequest;
import com.javajober.blocks.styleSetting.themeSetting.repository.ThemeSettingRepository;
import com.javajober.spaceWall.domain.BlockType;
import com.javajober.spaceWall.dto.request.DataStringSaveRequest;
import com.javajober.spaceWall.strategy.BlockJsonProcessor;
import com.javajober.spaceWall.strategy.BlockStrategyName;
import com.javajober.spaceWall.strategy.FixBlockStrategy;

import java.util.List;

@Component
public class StyleSettingStrategy implements FixBlockStrategy {

	private static final String STYLE_SETTING = BlockType.STYLE_SETTING.getEngTitle();
	private final BlockJsonProcessor blockJsonProcessor;
	private final StyleSettingRepository styleSettingRepository;
	private final BackgroundSettingRepository backgroundSettingRepository;
	private final BlockSettingRepository blockSettingRepository;
	private final ThemeSettingRepository themeSettingRepository;

	public StyleSettingStrategy(final BlockJsonProcessor blockJsonProcessor, final StyleSettingRepository styleSettingRepository,
								final BackgroundSettingRepository backgroundSettingRepository, final BlockSettingRepository blockSettingRepository,
								final ThemeSettingRepository themeSettingRepository) {
		this.blockJsonProcessor = blockJsonProcessor;
		this.styleSettingRepository = styleSettingRepository;
		this.backgroundSettingRepository = backgroundSettingRepository;
		this.blockSettingRepository = blockSettingRepository;
		this.themeSettingRepository = themeSettingRepository;
	}

	@Override
	public void saveBlocks(final DataStringSaveRequest data, ArrayNode blockInfoArray, Long position) {
		StyleSettingStringSaveRequest request = data.getStyleSetting();

		Long styleSettingId = saveStyleSetting(request);
		blockJsonProcessor.addBlockInfoToArray(blockInfoArray, position, STYLE_SETTING, styleSettingId, "");
	}

	private Long saveStyleSetting(final StyleSettingStringSaveRequest request){

		BackgroundSettingStringSaveRequest backgroundRequest = request.getBackgroundSetting();
		BackgroundSetting backgroundSetting = backgroundSettingRepository.save(BackgroundSettingStringSaveRequest.toEntity(backgroundRequest));

		BlockSettingSaveRequest blockSettingRequest = request.getBlockSetting();
		BlockSetting blockSetting = blockSettingRepository.save(BlockSettingSaveRequest.toEntity(blockSettingRequest));

		ThemeSettingSaveRequest themeSettingRequest = request.getThemeSetting();
		ThemeSetting themeSetting = themeSettingRepository.save(ThemeSettingSaveRequest.toEntity(themeSettingRequest));

		StyleSetting styleSetting = request.toEntity(backgroundSetting, blockSetting, themeSetting);

		return styleSettingRepository.save(styleSetting).getId();
	}

	@Override
	public CommonResponse createFixBlockDTO(List<JsonNode> fixBlocks) {
		Long blockId = fixBlocks.get(0).path("block_id").asLong();
		StyleSetting styleSetting = styleSettingRepository.findStyleBlock(blockId);

		BackgroundSettingResponse backgroundSettingResponse = BackgroundSettingResponse.from(styleSetting.getBackgroundSetting());
		BlockSettingResponse blockSettingResponse = BlockSettingResponse.from(styleSetting.getBlockSetting());
		ThemeSettingResponse themeSettingResponse = ThemeSettingResponse.from(styleSetting.getThemeSetting());

		return new StyleSettingResponse(blockId, backgroundSettingResponse, blockSettingResponse, themeSettingResponse);
	}

	@Override
	public String getStrategyName() {
		return BlockStrategyName.StyleSettingStrategy.name();
	}
}

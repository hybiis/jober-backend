package com.javajober.spaceWall.spaceWallCategory.repository;

import java.util.Optional;

import com.javajober.spaceWall.spaceWallCategory.domain.SpaceWallCategory;
import com.javajober.spaceWall.spaceWallCategory.domain.SpaceWallCategoryType;
import org.springframework.data.repository.Repository;

import com.javajober.core.exception.ApiStatus;
import com.javajober.core.exception.ApplicationException;

public interface SpaceWallCategoryRepository extends Repository<SpaceWallCategory, Long> {

	Optional<SpaceWallCategory> findBySpaceWallCategory(final SpaceWallCategoryType categoryType);

	default SpaceWallCategory getBySpaceWallCategory(final SpaceWallCategoryType categoryType){
		return findBySpaceWallCategory(categoryType)
			.orElseThrow(() -> new ApplicationException(ApiStatus.NOT_FOUND, "해당 카테고리 데이터를 찾을 수 없습니다."));
	}
}
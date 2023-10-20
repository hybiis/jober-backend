package com.javajober.spaceWallCategory.repository;

import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.javajober.exception.ApiStatus;
import com.javajober.exception.ApplicationException;
import com.javajober.spaceWallCategory.domain.SpaceWallCategory;
import com.javajober.spaceWallCategory.domain.SpaceWallCategoryType;

public interface SpaceWallCategoryRepository extends Repository<SpaceWallCategory, Long> {

	Optional<SpaceWallCategory> findBySpaceWallCategory(final SpaceWallCategoryType categoryType);

	default SpaceWallCategory getBySpaceWallCategory(final SpaceWallCategoryType categoryType){
		return findBySpaceWallCategory(categoryType)
			.orElseThrow(() -> new ApplicationException(ApiStatus.NOT_FOUND, "해당 카테고리 데이터를 찾을 수 없습니다."));
	}
}
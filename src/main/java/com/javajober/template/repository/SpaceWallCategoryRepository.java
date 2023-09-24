package com.javajober.template.repository;


import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.javajober.core.error.exception.Exception404;
import com.javajober.entity.SpaceWallCategory;
import com.javajober.entity.SpaceWallCategoryType;

public interface SpaceWallCategoryRepository extends Repository<SpaceWallCategory, Long> {
	Optional<SpaceWallCategory> findBySpaceWallCategory(SpaceWallCategoryType categoryType);

	default SpaceWallCategory getBySpaceWallCategory(final SpaceWallCategoryType categoryType){
		return findBySpaceWallCategory(categoryType)
			.orElseThrow(() -> new Exception404("해당 카테고리를 찾을 수 없습니다."));
	}
}

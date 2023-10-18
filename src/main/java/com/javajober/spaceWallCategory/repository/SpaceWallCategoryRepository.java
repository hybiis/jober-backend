package com.javajober.spaceWallCategory.repository;

import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.javajober.core.error.exception.Exception404;
import com.javajober.core.message.ErrorMessage;
import com.javajober.spaceWallCategory.domain.SpaceWallCategory;
import com.javajober.spaceWallCategory.domain.SpaceWallCategoryType;

public interface SpaceWallCategoryRepository extends Repository<SpaceWallCategory, Long> {

	Optional<SpaceWallCategory> findBySpaceWallCategory(final SpaceWallCategoryType categoryType);

	default SpaceWallCategory getBySpaceWallCategory(final SpaceWallCategoryType categoryType){
		return findBySpaceWallCategory(categoryType)
			.orElseThrow(() -> new Exception404(ErrorMessage.TEMPLATE_CATEGORY_NOT_FOUND));
	}
}
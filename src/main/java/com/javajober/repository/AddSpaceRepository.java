package com.javajober.repository;

import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.javajober.core.error.exception.Exception404;
import com.javajober.entity.AddSpace;
import com.javajober.entity.SpaceType;

public interface AddSpaceRepository extends Repository<AddSpace, Long> {
	Optional<AddSpace> findBySpaceTypeAndId(SpaceType spaceType, Long memberId);

	default AddSpace getBySpaceTypeAndId(final SpaceType spaceType, final Long memberId){
		return findBySpaceTypeAndId(spaceType,memberId)
			.orElseThrow(() -> new Exception404("해당 스페이스를 찾을 수 없습니다."));
	}
}

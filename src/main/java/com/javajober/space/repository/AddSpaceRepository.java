package com.javajober.space.repository;

import java.util.List;
import java.util.Optional;

import com.javajober.core.exception.ApiStatus;
import com.javajober.core.exception.ApplicationException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.javajober.space.domain.AddSpace;
import com.javajober.space.domain.SpaceType;
import org.springframework.data.repository.query.Param;

public interface AddSpaceRepository extends Repository<AddSpace, Long> {

	AddSpace save(final AddSpace addSpace);

	Optional<AddSpace> findById(final Long id);

	Optional<AddSpace> findBySpaceTypeAndId(final SpaceType spaceType, final Long memberId);

	@Query("SELECT a FROM AddSpace a JOIN FETCH a.member WHERE a.member.id = :memberId AND a.spaceType IN :spaceTypes")
	List<AddSpace> findSpacesByMemberIdAndSpaceTypes(final Long memberId, final List<SpaceType> spaceTypes);

	@Query("SELECT s.id FROM AddSpace s WHERE s.spaceType = :spaceType AND s.member.id = :memberId")
	List<Long> findAddSpaceIdBySpaceTypeAndMemberId(@Param("spaceType") final SpaceType spaceType, @Param("memberId") final Long memberId);

	default AddSpace findAddSpace (final Long id) {
		return findById(id)
				.orElseThrow(() -> new ApplicationException(ApiStatus.NOT_FOUND, "존재하지 않는 스페이스입니다."));
	}

	default AddSpace getBySpaceTypeAndId(final SpaceType spaceType, final Long memberId) {
		return findBySpaceTypeAndId(spaceType, memberId)
				.orElseThrow(() -> new ApplicationException(ApiStatus.NOT_FOUND, "존재하지 않는 스페이스입니다."));
	}

	default List<Long> findAddSpaceIds(final SpaceType spaceType, final Long memberId) {
		List<Long> addSpaceIds = findAddSpaceIdBySpaceTypeAndMemberId(spaceType, memberId);
		if (addSpaceIds.isEmpty()) {
			throw new ApplicationException(ApiStatus.NOT_FOUND, "존재하지 않는 스페이스입니다.");
		}
		return addSpaceIds;
	}
}
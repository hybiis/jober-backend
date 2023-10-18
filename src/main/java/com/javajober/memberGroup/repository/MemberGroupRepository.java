package com.javajober.memberGroup.repository;

import java.util.List;
import java.util.Optional;

import com.javajober.space.dto.response.MemberGroupResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.javajober.core.error.exception.Exception404;
import com.javajober.core.message.ErrorMessage;
import com.javajober.memberGroup.domain.MemberGroup;
import org.springframework.data.repository.query.Param;

public interface MemberGroupRepository extends Repository<MemberGroup, Long> {

	List<MemberGroup> findByAddSpaceId(final Long addSpaceId);

	Optional<MemberGroup> findById(final Long Id);

	@Query("SELECT new com.javajober.space.dto.response.MemberGroupResponse(m.id, m.memberName, mg.memberHashtagType, mg.accountType, m.phoneNumber) " +
			"FROM MemberGroup mg LEFT JOIN mg.member m WHERE mg.addSpace.id = :addSpaceId")
	List<MemberGroupResponse> findMemberGroup(@Param("addSpaceId") final Long addSpaceId);

	default List<MemberGroup> getByAddSpaceId(final Long addSpaceId) {
		List<MemberGroup> memberGroups = findByAddSpaceId(addSpaceId);

		if (memberGroups == null || memberGroups.isEmpty()) {
			throw new Exception404(ErrorMessage.ADD_SPACE_NOT_FOUND);
		}

		return memberGroups;
	}

	default MemberGroup getById(final Long Id){
		return findById(Id)
			.orElseThrow(() -> new Exception404(ErrorMessage.MEMBER_NOT_FOUND));
	}
}
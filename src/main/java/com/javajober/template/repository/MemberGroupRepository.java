package com.javajober.template.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.javajober.core.error.exception.Exception404;
import com.javajober.entity.MemberGroup;


public interface MemberGroupRepository extends Repository<MemberGroup, Long> {
	List<MemberGroup> findByAddSpaceId(Long addSpaceId);

	default List<MemberGroup> getByAddSpaceId(final Long addSpaceId) {
		List<MemberGroup> memberGroups = findByAddSpaceId(addSpaceId);

		if (memberGroups == null || memberGroups.isEmpty()) {
			throw new Exception404("해당 스페이스 연락처를 찾을 수 없습니다.");
		}

		return memberGroups;
	}
}
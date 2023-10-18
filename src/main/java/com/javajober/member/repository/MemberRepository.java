package com.javajober.member.repository;

import java.util.Optional;
import org.springframework.data.repository.Repository;

import com.javajober.exception.ApiStatus;
import com.javajober.exception.ApplicationException;
import com.javajober.member.domain.Member;

public interface MemberRepository extends Repository<Member, Long> {
	Optional<Member> findById(final Long id);

	Optional<Member> findByMemberEmail(final String email);

	Member save(final Member member);

	default Member findMember(final Long id) {
		String message = "존재하지 않는 회원정보입니다.";

		return findById(id)
			.orElseThrow(() -> new ApplicationException(ApiStatus.NOT_FOUND, message));
	}

	default Member findMember (final String email) {
		String message = "존재하지 않는 회원정보입니다.";

		return findByMemberEmail(email)
			.orElseThrow(() -> new ApplicationException(ApiStatus.NOT_FOUND, message));
	}
}
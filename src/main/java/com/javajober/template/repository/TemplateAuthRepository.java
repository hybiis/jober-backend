package com.javajober.template.repository;

import java.util.Optional;

import com.javajober.core.error.exception.Exception404;
import com.javajober.entity.TemplateAuth;
import org.springframework.data.repository.Repository;


public interface TemplateAuthRepository extends Repository<TemplateAuth, Long> {
	Optional<TemplateAuth> findByAuthMemberId(Long authMemberId);

	default TemplateAuth getByAuthMemberId(final Long authMemberId){
		return findByAuthMemberId(authMemberId)
			.orElseThrow(() -> new Exception404("템플릿 권한 정보를 찾을 수 없습니다."));
	}
}

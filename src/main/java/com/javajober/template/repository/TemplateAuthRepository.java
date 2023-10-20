package com.javajober.template.repository;

import java.util.List;
import java.util.Optional;

import com.javajober.core.exception.ApiStatus;
import com.javajober.core.exception.ApplicationException;
import com.javajober.template.domain.TemplateAuth;
import com.javajober.blocks.templateBlock.domain.TemplateBlock;

import org.springframework.data.repository.Repository;

public interface TemplateAuthRepository extends Repository<TemplateAuth, Long> {

	Optional<TemplateAuth> findByAuthMemberIdAndTemplateBlockId(final Long authMemberId, final Long templateBlockId);

	TemplateAuth save(final TemplateAuth templateAuth);

	List<TemplateAuth> findByTemplateBlockId(final Long templateBlockId);

	List<TemplateAuth> findByTemplateBlock(final TemplateBlock block);

	default TemplateAuth getByAuthMemberIdAndTemplateBlockId(final Long authMemberId, final Long templateBlockId){
		return findByAuthMemberIdAndTemplateBlockId(authMemberId, templateBlockId)
				.orElseThrow(() -> new ApplicationException(ApiStatus.NOT_FOUND, "템플릿 권한 데이터를 찾을 수 없습니다."));
	}
}
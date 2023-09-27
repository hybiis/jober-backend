package com.javajober.template.repository;

import java.util.List;
import java.util.Optional;

import com.javajober.core.error.exception.Exception404;
import com.javajober.core.message.ErrorMessage;
import com.javajober.template.domain.TemplateAuth;
import com.javajober.template.domain.TemplateBlock;

import org.springframework.data.repository.Repository;


public interface TemplateAuthRepository extends Repository<TemplateAuth, Long> {
	Optional<TemplateAuth> findByAuthMemberIdAndTemplateBlockId(Long authMemberId, Long templateBlockId);

	default TemplateAuth getByAuthMemberIdAndTemplateBlockId(final Long authMemberId, final Long templateBlockId){
		return findByAuthMemberIdAndTemplateBlockId(authMemberId, templateBlockId)
			.orElseThrow(() -> new Exception404(ErrorMessage.TEMPLATE_AUTH_NOT_FOUND));
	}

	TemplateAuth save(TemplateAuth templateAuth);


	List<TemplateAuth> findByTemplateBlockId(Long templateBlockId);

	List<TemplateAuth> findByTemplateBlock(TemplateBlock block);
}

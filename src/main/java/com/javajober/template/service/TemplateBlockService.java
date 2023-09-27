package com.javajober.template.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javajober.core.error.exception.Exception404;
import com.javajober.core.message.ErrorMessage;
import com.javajober.member.domain.MemberGroup;
import com.javajober.template.domain.TemplateAuth;
import com.javajober.template.domain.TemplateBlock;
import com.javajober.template.dto.TemplateBlockRequest;
import com.javajober.template.dto.TemplateBlockRequests;
import com.javajober.template.dto.TemplateBlockResponse;
import com.javajober.template.repository.MemberGroupRepository;
import com.javajober.template.repository.TemplateAuthRepository;
import com.javajober.template.repository.TemplateBlockRepository;

@Service
public class TemplateBlockService {

	private final MemberGroupRepository memberGroupRepository;
	private final TemplateAuthRepository templateAuthRepository;
	private final TemplateBlockRepository templateBlockRepository;

	public TemplateBlockService(MemberGroupRepository memberGroupRepository, TemplateAuthRepository templateAuthRepository, TemplateBlockRepository templateBlockRepository) {
		this.memberGroupRepository = memberGroupRepository;
		this.templateAuthRepository = templateAuthRepository;
		this.templateBlockRepository = templateBlockRepository;
	}

	@Transactional
	public void save(final TemplateBlockRequests<TemplateBlockRequest> templateBlockRequests) {

		List<TemplateBlockRequest> subDataList = templateBlockRequests.getSubData();

		for (TemplateBlockRequest templateBlockRequest : subDataList) {

			TemplateBlock templateBlock = TemplateBlockRequest.toEntity(templateBlockRequest);
			templateBlockRepository.save(templateBlock);

			List<Long> allAuthIds = templateBlockRequest.getAllAuthIds();

			for (Long authId : allAuthIds) {
				MemberGroup memberGroup = memberGroupRepository.getById(authId);
				Boolean hasAccess = templateBlockRequest.getHasAccessTemplateAuth().contains(authId);
				TemplateAuth templateAuth = new TemplateAuth(memberGroup, hasAccess, templateBlock);
				templateAuthRepository.save(templateAuth);
			}
		}
	}

	@Transactional
	public TemplateBlockResponse getTemplateBlock(Long templateBlockId){

		TemplateBlock templateBlock = templateBlockRepository.getById(templateBlockId);

		List<TemplateAuth> templateAuths = templateAuthRepository.findByTemplateBlockId(templateBlockId);

		if(templateAuths == null || templateAuths.isEmpty()){
			throw new Exception404(ErrorMessage.TEMPLATE_AUTH_NOT_FOUND);
		}

		List<Long> hasAccessTemplateAuth = new ArrayList<>();
		List<Long> hasDenyTemplateAuth = new ArrayList<>();

		for (TemplateAuth auth : templateAuths) {
			if (auth.getHasAccess()) {
				hasAccessTemplateAuth.add(auth.getAuthMember().getId());
			} else {
				hasDenyTemplateAuth.add(auth.getAuthMember().getId());
			}
		}
		return TemplateBlockResponse.from(templateBlock, hasAccessTemplateAuth, hasDenyTemplateAuth);
	}



	@Transactional
	public void deleteTemplateBlock(Long templateBlockId){

		TemplateBlock templateBlock = templateBlockRepository.getById(templateBlockId);

		templateBlock.setDeletedAt();

		List<TemplateAuth> authIds = templateAuthRepository.findByTemplateBlock(templateBlock);

		if(authIds == null || authIds.isEmpty()){
			throw new Exception404(ErrorMessage.TEMPLATE_AUTH_NOT_FOUND);
		}

		for (TemplateAuth auth : authIds) {
			templateAuthRepository.delete(auth);
			auth.setDeletedAt();
		}

		templateBlockRepository.delete(templateBlock);
	}

}

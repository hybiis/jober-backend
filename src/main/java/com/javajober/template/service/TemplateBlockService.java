package com.javajober.template.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javajober.member.domain.MemberGroup;
import com.javajober.template.domain.TemplateAuth;
import com.javajober.template.domain.TemplateBlock;
import com.javajober.template.dto.TemplateBlockDeleteRequest;
import com.javajober.template.dto.TemplateBlockRequest;
import com.javajober.template.dto.TemplateBlockRequests;
import com.javajober.template.dto.TemplateBlockResponse;
import com.javajober.template.dto.TemplateBlockResponses;
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
	public TemplateBlockResponses find(final List<Long> templateBlockIds){

		List<TemplateBlockResponse> templateBlockResponses = new ArrayList<>();

		for(Long templateBlockId : templateBlockIds){

			TemplateBlock templateblock = templateBlockRepository.getById(templateBlockId);

			List<TemplateAuth> templateAuths = templateAuthRepository.findByTemplateBlockId(templateblock.getId());

			List<Long> hasAccessTemplateAuth = new ArrayList<>();
			List<Long> hasDenyTemplateAuth = new ArrayList<>();

			for (TemplateAuth auth : templateAuths) {
				if (auth.getHasAccess()) {
					hasAccessTemplateAuth.add(auth.getAuthMember().getId());
				} else {
					hasDenyTemplateAuth.add(auth.getAuthMember().getId());
				}
			}
			TemplateBlockResponse response = TemplateBlockResponse.from(templateblock,hasAccessTemplateAuth,hasDenyTemplateAuth);

			templateBlockResponses.add(response);
		}

		return new TemplateBlockResponses(templateBlockResponses);
	}



	@Transactional
	public void delete(final TemplateBlockDeleteRequest templateBlockDeleteRequest){

		List<TemplateBlock> templateBlocks = templateBlockRepository.findAllById(templateBlockDeleteRequest.getTemplateBlockIds());

		for(TemplateBlock templateBlock : templateBlocks){

			templateBlock.setDeletedAt();

			List<TemplateAuth> authIds = templateAuthRepository.findByTemplateBlock(templateBlock);

			for (TemplateAuth auth : authIds) {
				auth.setDeletedAt();
				templateAuthRepository.save(auth);
			}
		}

		templateBlockRepository.saveAll(templateBlocks);
	}

}

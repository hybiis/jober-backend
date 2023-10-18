package com.javajober.templateBlock.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javajober.memberGroup.domain.MemberGroup;
import com.javajober.template.domain.TemplateAuth;
import com.javajober.templateBlock.domain.TemplateBlock;
import com.javajober.templateBlock.dto.request.TemplateBlockDeleteRequest;
import com.javajober.templateBlock.dto.request.TemplateBlockSaveRequest;
import com.javajober.templateBlock.dto.request.TemplateBlockSaveRequests;
import com.javajober.templateBlock.dto.response.TemplateBlockResponse;
import com.javajober.templateBlock.dto.response.TemplateBlockResponses;
import com.javajober.templateBlock.dto.request.TemplateBlockUpdateRequest;
import com.javajober.memberGroup.repository.MemberGroupRepository;
import com.javajober.template.repository.TemplateAuthRepository;
import com.javajober.templateBlock.repository.TemplateBlockRepository;

@Service
public class TemplateBlockService {

	private final MemberGroupRepository memberGroupRepository;
	private final TemplateAuthRepository templateAuthRepository;
	private final TemplateBlockRepository templateBlockRepository;

	public TemplateBlockService(final MemberGroupRepository memberGroupRepository, final TemplateAuthRepository templateAuthRepository, final TemplateBlockRepository templateBlockRepository) {
		this.memberGroupRepository = memberGroupRepository;
		this.templateAuthRepository = templateAuthRepository;
		this.templateBlockRepository = templateBlockRepository;
	}

	@Transactional
	public void save(final TemplateBlockSaveRequests<TemplateBlockSaveRequest> templateBlockSaveRequests) {

		List<TemplateBlockSaveRequest> subDataList = templateBlockSaveRequests.getSubData();
		for (TemplateBlockSaveRequest templateBlockSaveRequest : subDataList) {
			TemplateBlock templateBlock = TemplateBlockSaveRequest.toEntity(templateBlockSaveRequest);
			templateBlockRepository.save(templateBlock);
			List<Long> allAuthIds = templateBlockSaveRequest.getAllAuthIds();
			for (Long authId : allAuthIds) {
				MemberGroup memberGroup = memberGroupRepository.getById(authId);
				Boolean hasAccess = templateBlockSaveRequest.getHasAccessTemplateAuth().contains(authId);
				TemplateAuth templateAuth = new TemplateAuth(memberGroup, hasAccess, templateBlock);
				templateAuthRepository.save(templateAuth);
			}
		}
	}

	@Transactional
	public TemplateBlockResponses find(final List<Long> templateBlockIds){

		List<TemplateBlockResponse> templateBlockResponses = new ArrayList<>();
		for(Long templateBlockId : templateBlockIds){
			TemplateBlock templateblock = templateBlockRepository.findTemplateBlock(templateBlockId);
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
			TemplateBlockResponse response = TemplateBlockResponse.of(templateblock,hasAccessTemplateAuth,hasDenyTemplateAuth);
			templateBlockResponses.add(response);
		}
		return new TemplateBlockResponses(templateBlockResponses);
	}

	@Transactional
	public void update(final TemplateBlockSaveRequests<TemplateBlockUpdateRequest> templateBlockSaveRequests){

		for(TemplateBlockUpdateRequest templateBlockRequest : templateBlockSaveRequests.getSubData()){
			TemplateBlock templateBlock = templateBlockRepository.findTemplateBlock(templateBlockRequest.getTemplateBlockId());
			templateBlock.update(templateBlockRequest.getTemplateUUID(), templateBlockRequest.getTemplateTitle(), templateBlockRequest.getTemplateDescription());
			List<TemplateAuth> authIds = templateAuthRepository.findByTemplateBlockId(templateBlock.getId());
			for (TemplateAuth auth : authIds) {
				MemberGroup memberGroup = memberGroupRepository.getById(auth.getId());
				Boolean hasAccess = templateBlockRequest.getHasAccessTemplateAuth().contains(auth.getId());
				auth.update(memberGroup,hasAccess,templateBlock);
				templateAuthRepository.save(auth);
			}
			templateBlockRepository.save(templateBlock);
		}
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
package com.javajober.spaceWall.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javajober.core.error.exception.Exception404;
import com.javajober.core.message.ErrorMessage;
import com.javajober.freeBlock.domain.FreeBlock;
import com.javajober.freeBlock.dto.request.FreeBlockSaveRequest;
import com.javajober.freeBlock.repository.FreeBlockRepository;
import com.javajober.member.domain.MemberGroup;
import com.javajober.snsBlock.domain.SNSBlock;
import com.javajober.snsBlock.dto.SNSBlockRequest;
import com.javajober.snsBlock.repository.SNSBlockRepository;
import com.javajober.spaceWall.domain.FlagType;
import com.javajober.spaceWall.domain.SpaceWall;
import com.javajober.spaceWall.dto.request.SpaceWallRequest;
import com.javajober.spaceWall.dto.response.SpaceWallResponse;
import com.javajober.spaceWall.repository.SpaceWallRepository;
import com.javajober.template.domain.TemplateAuth;
import com.javajober.template.domain.TemplateBlock;
import com.javajober.template.dto.TemplateBlockRequest;
import com.javajober.template.repository.MemberGroupRepository;
import com.javajober.template.repository.TemplateAuthRepository;
import com.javajober.template.repository.TemplateBlockRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SpaceWallService {

	private final SpaceWallRepository spaceWallRepository;
	private final SNSBlockRepository snsBlockRepository;
	private final FreeBlockRepository freeBlockRepository;
	private final TemplateBlockRepository templateBlockRepository;
	private final MemberGroupRepository memberGroupRepository;
	private final TemplateAuthRepository templateAuthRepository;

	public SpaceWallService(SpaceWallRepository spaceWallRepository, SNSBlockRepository snsBlockRepository,
		FreeBlockRepository freeBlockRepository, TemplateBlockRepository templateBlockRepository,
		MemberGroupRepository memberGroupRepository, TemplateAuthRepository templateAuthRepository) {
		this.spaceWallRepository = spaceWallRepository;
		this.snsBlockRepository = snsBlockRepository;
		this.freeBlockRepository = freeBlockRepository;
		this.templateBlockRepository = templateBlockRepository;
		this.memberGroupRepository = memberGroupRepository;
		this.templateAuthRepository = templateAuthRepository;
	}

	public SpaceWallResponse checkSpaceWallTemporary(Long memberId, Long addSpaceId) {

		List<SpaceWall> spaceWalls = spaceWallRepository.findSpaceWalls(memberId, addSpaceId);

		if (spaceWalls == null || spaceWalls.isEmpty()) {
			return new SpaceWallResponse(null, false);
		}

		for (SpaceWall spaceWall : spaceWalls) {
			if (spaceWall.getFlag().equals(FlagType.PENDING) && spaceWall.getDeletedAt() == null) {
				return new SpaceWallResponse(spaceWall.getId(), true);
			}
			if (spaceWall.getFlag().equals(FlagType.SAVED) && spaceWall.getDeletedAt() == null) {
				throw new Exception404(ErrorMessage.SAVED_SPACE_WALL_ALREADY_EXISTS);
			}
		}

		return new SpaceWallResponse(null, false);
	}

	@Transactional
	public void save(final SpaceWallRequest spaceWallRequest) {
		ObjectMapper mapper = new ObjectMapper();

		spaceWallRequest.getData().getBlocks().forEach(block -> {
			switch (block.getBlockType()) {
				case FREE_BLOCK:
					List<FreeBlockSaveRequest> freeBlockRequests = mapper.convertValue(block.getSubData(),
						new TypeReference<List<FreeBlockSaveRequest>>() {
						});
					saveFreeBlocks(freeBlockRequests);
					break;
				case SNS_BLOCK:
					List<SNSBlockRequest> snsBlockRequests = mapper.convertValue(block.getSubData(),
						new TypeReference<List<SNSBlockRequest>>() {
						});
					saveSnsBlocks(snsBlockRequests);
					break;
				case TEMPLATE_BLOCK:
					List<TemplateBlockRequest> templateBlockRequests = mapper.convertValue(block.getSubData(),
						new TypeReference<List<TemplateBlockRequest>>() {
						});
					saveTemplateBlock(templateBlockRequests);
					break;

			}
		});
	}

	private void saveFreeBlocks(List<FreeBlockSaveRequest> subData) {
		subData.forEach(block -> {
			FreeBlock freeBlock = FreeBlockSaveRequest.toEntity(block);
			freeBlockRepository.save(freeBlock);
		});
	}

	private void saveSnsBlocks(List<SNSBlockRequest> subData) {
		subData.forEach(block -> {
			SNSBlock snsBlock = SNSBlockRequest.toEntity(block);
			snsBlockRepository.save(snsBlock);
		});
	}

	private void saveTemplateBlock(List<TemplateBlockRequest> subData){
		subData.forEach(block -> {
			TemplateBlock templateBlock = TemplateBlockRequest.toEntity(block);
			templateBlockRepository.save(templateBlock);

			block.getAllAuthIds().forEach(authId -> {
				MemberGroup memberGroup = memberGroupRepository.getById(authId);
				Boolean hasAccess = block.getHasAccessTemplateAuth().contains(authId);
				TemplateAuth templateAuth = new TemplateAuth(memberGroup, hasAccess, templateBlock);
				templateAuthRepository.save(templateAuth);
			});
		});
	}
}

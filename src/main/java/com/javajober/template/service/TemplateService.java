package com.javajober.template.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javajober.addSpace.repository.AddSpaceRepository;
import com.javajober.core.error.exception.Exception404;
import com.javajober.core.message.ErrorMessage;
import com.javajober.entity.SpaceWallCategory;
import com.javajober.entity.SpaceWallCategoryType;
import com.javajober.template.domain.Template;
import com.javajober.template.domain.TemplateBlock;
import com.javajober.template.dto.MemberAuthResponse;
import com.javajober.entity.AddSpace;
import com.javajober.entity.Member;
import com.javajober.template.domain.MemberGroup;
import com.javajober.entity.SpaceType;
import com.javajober.template.domain.TemplateAuth;
import com.javajober.template.dto.TemplateResponse;
import com.javajober.template.repository.MemberGroupRepository;
import com.javajober.template.repository.SpaceWallCategoryRepository;
import com.javajober.template.repository.TemplateAuthRepository;
import com.javajober.template.repository.TemplateBlockRepository;
import com.javajober.template.repository.TemplateRepository;

@Service
public class TemplateService {

	private final MemberGroupRepository memberGroupRepository;
	private final AddSpaceRepository addSpaceRepository;
	private final TemplateAuthRepository templateAuthRepository;
	private final SpaceWallCategoryRepository spaceWallCategoryRepository;
	private final TemplateRepository templateRepository;
	private final TemplateBlockRepository templateBlockRepository;

	public TemplateService(MemberGroupRepository memberGroupRepository, AddSpaceRepository addSpaceRepository,
		TemplateAuthRepository templateAuthRepository, SpaceWallCategoryRepository spaceWallCategoryRepository,
		TemplateRepository templateRepository, TemplateBlockRepository templateBlockRepository) {
		this.memberGroupRepository = memberGroupRepository;
		this.addSpaceRepository = addSpaceRepository;
		this.templateAuthRepository = templateAuthRepository;
		this.spaceWallCategoryRepository = spaceWallCategoryRepository;
		this.templateRepository = templateRepository;
		this.templateBlockRepository = templateBlockRepository;
	}


	@Transactional
	public MemberAuthResponse getTemplateAuthList(SpaceType spaceType, Long memberId, Long templateBlockID) {


		AddSpace addSpace = addSpaceRepository.getBySpaceTypeAndId(spaceType, memberId);

		List<MemberGroup> memberGroups = memberGroupRepository.getByAddSpaceId(addSpace.getId());

		TemplateBlock templateBlock = templateBlockRepository.getById(templateBlockID);

		List<MemberAuthResponse.MemberInfo> memberInfos = new ArrayList<>();

		for (MemberGroup memberGroup : memberGroups) {
			Member member = memberGroup.getMember();

			if (member == null) {
				throw new Exception404(ErrorMessage.MEMBER_NOT_FOUND);
			}

			TemplateAuth templateAuth = templateAuthRepository.getByAuthMemberIdAndTemplateBlockId(memberGroup.getId(),templateBlock.getId());

			MemberAuthResponse.MemberInfo memberInfo = MemberAuthResponse.MemberInfo.from(memberGroup, member,
				templateAuth);
			memberInfos.add(memberInfo);
		}

		return new MemberAuthResponse(memberInfos);
	}

	@Transactional
	public TemplateResponse getTemplateRecommend(SpaceWallCategoryType spaceWallCategoryType) {

		SpaceWallCategory spaceWallCategory = spaceWallCategoryRepository.getBySpaceWallCategory(spaceWallCategoryType);

		List<Template> templates = templateRepository.getBySpaceWallCategoryId(spaceWallCategory.getId());

		List<TemplateResponse.TemplateInfo> templateInfos = new ArrayList<>();

		for (Template template : templates) {
			TemplateResponse.TemplateInfo templateInfo = TemplateResponse.TemplateInfo.from(template);
			templateInfos.add(templateInfo);
		}

		return new TemplateResponse(templateInfos);

	}

	@Transactional
	public TemplateResponse getSearchTemplatesByTitle(String keyword) {

		List<Template> templates = templateRepository.getTemplateTitle(keyword);

		List<TemplateResponse.TemplateInfo> templateInfos = new ArrayList<>();

		for (Template template : templates) {
			TemplateResponse.TemplateInfo info = TemplateResponse.TemplateInfo.from(template);
			templateInfos.add(info);
		}

		return new TemplateResponse(templateInfos);
	}
}
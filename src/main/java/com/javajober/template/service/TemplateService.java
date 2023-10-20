package com.javajober.template.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javajober.exception.ApiStatus;
import com.javajober.exception.ApplicationException;
import com.javajober.space.repository.AddSpaceRepository;
import com.javajober.member.domain.Member;
import com.javajober.spaceWallCategory.domain.SpaceWallCategory;
import com.javajober.spaceWallCategory.domain.SpaceWallCategoryType;
import com.javajober.template.domain.Template;
import com.javajober.templateBlock.domain.TemplateBlock;
import com.javajober.template.dto.response.MemberAuthResponse;
import com.javajober.space.domain.AddSpace;
import com.javajober.memberGroup.domain.MemberGroup;
import com.javajober.space.domain.SpaceType;
import com.javajober.template.domain.TemplateAuth;
import com.javajober.template.dto.response.TemplateResponse;
import com.javajober.memberGroup.repository.MemberGroupRepository;
import com.javajober.spaceWallCategory.repository.SpaceWallCategoryRepository;
import com.javajober.template.repository.TemplateAuthRepository;
import com.javajober.templateBlock.repository.TemplateBlockRepository;
import com.javajober.template.repository.TemplateRepository;

@Service
public class TemplateService {

	private final MemberGroupRepository memberGroupRepository;
	private final AddSpaceRepository addSpaceRepository;
	private final TemplateAuthRepository templateAuthRepository;
	private final SpaceWallCategoryRepository spaceWallCategoryRepository;
	private final TemplateRepository templateRepository;
	private final TemplateBlockRepository templateBlockRepository;

	public TemplateService(final MemberGroupRepository memberGroupRepository, final AddSpaceRepository addSpaceRepository,
		final TemplateAuthRepository templateAuthRepository, final SpaceWallCategoryRepository spaceWallCategoryRepository,
		final TemplateRepository templateRepository, final TemplateBlockRepository templateBlockRepository) {
		this.memberGroupRepository = memberGroupRepository;
		this.addSpaceRepository = addSpaceRepository;
		this.templateAuthRepository = templateAuthRepository;
		this.spaceWallCategoryRepository = spaceWallCategoryRepository;
		this.templateRepository = templateRepository;
		this.templateBlockRepository = templateBlockRepository;
	}

	@Transactional
	public MemberAuthResponse findTemplateAuthList(final SpaceType spaceType, final Long memberId, final Long templateBlockID) {

		AddSpace addSpace = addSpaceRepository.getBySpaceTypeAndId(spaceType, memberId);
		List<MemberGroup> memberGroups = memberGroupRepository.getByAddSpaceId(addSpace.getId());
		TemplateBlock templateBlock = templateBlockRepository.findTemplateBlock(templateBlockID);
		List<MemberAuthResponse.MemberInfo> memberInfos = new ArrayList<>();
		for (MemberGroup memberGroup : memberGroups) {
			Member member = memberGroup.getMember();
			if (member == null) {
				throw new ApplicationException(ApiStatus.NOT_FOUND, "존재하지 않는 회원정보입니다.");
			}
			TemplateAuth templateAuth = templateAuthRepository.getByAuthMemberIdAndTemplateBlockId(memberGroup.getId(),templateBlock.getId());
			MemberAuthResponse.MemberInfo memberInfo = MemberAuthResponse.MemberInfo.of(memberGroup, member,
				templateAuth);
			memberInfos.add(memberInfo);
		}
		return new MemberAuthResponse(memberInfos);
	}

	@Transactional
	public TemplateResponse findTemplateRecommend(final SpaceWallCategoryType spaceWallCategoryType) {

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
	public TemplateResponse findSearchTemplatesByTitle(final String keyword) {

		List<Template> templates = templateRepository.getTemplateTitle(keyword);
		List<TemplateResponse.TemplateInfo> templateInfos = new ArrayList<>();
		for (Template template : templates) {
			TemplateResponse.TemplateInfo info = TemplateResponse.TemplateInfo.from(template);
			templateInfos.add(info);
		}
		return new TemplateResponse(templateInfos);
	}
}
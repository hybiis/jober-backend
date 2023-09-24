package com.javajober.template.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.javajober.core.error.exception.Exception404;
import com.javajober.template.dto.MemberAuthResponse;
import com.javajober.entity.AddSpace;
import com.javajober.entity.Member;
import com.javajober.entity.MemberGroup;
import com.javajober.entity.SpaceType;
import com.javajober.entity.TemplateAuth;
import com.javajober.template.repository.AddSpaceRepository;
import com.javajober.template.repository.MemberGroupRepository;
import com.javajober.template.repository.TemplateAuthRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TemplateBlockService {

	private final MemberGroupRepository memberGroupRepository;
	private final AddSpaceRepository addSpaceRepository;
	private final TemplateAuthRepository templateAuthRepository;


	@Transactional
	public MemberAuthResponse getTemplateAuthList(SpaceType spaceType, Long memberId){

		AddSpace addSpace = addSpaceRepository.getBySpaceTypeAndId(spaceType, memberId);

		List<MemberGroup> memberGroups = memberGroupRepository.getByAddSpaceId(addSpace.getId());

		List<MemberAuthResponse.MemberInfo> memberInfos = new ArrayList<>();

		for (MemberGroup memberGroup : memberGroups) {
			Member member = memberGroup.getMember();

			if (member == null) {
				throw new Exception404("멤버 정보를 찾을 수 없습니다.");
			}

			TemplateAuth templateAuth = templateAuthRepository.getByAuthMemberId(memberGroup.getId());

			MemberAuthResponse.MemberInfo memberInfo = MemberAuthResponse.MemberInfo.from(memberGroup, member, templateAuth);
			memberInfos.add(memberInfo);
		}

		return new MemberAuthResponse(memberInfos);
	}
}

package com.javajober.home.service;

import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javajober.member.domain.Member;
import com.javajober.space.domain.AddSpace;
import com.javajober.space.domain.SpaceType;

import com.javajober.home.dto.response.AddSpaceResponse;
import com.javajober.home.dto.response.HomeResponse;
import com.javajober.home.dto.response.MemberResponse;
import com.javajober.space.repository.AddSpaceRepository;
import com.javajober.member.repository.MemberRepository;

@Service
public class HomeService {

	private final AddSpaceRepository addSpaceRepository;
	private final MemberRepository memberRepository;

	public HomeService(final AddSpaceRepository addSpaceRepository, final MemberRepository memberRepository) {
		this.addSpaceRepository = addSpaceRepository;
		this.memberRepository = memberRepository;
	}

	@Transactional
	public HomeResponse find(final Long memberId) {

		Member member = memberRepository.findMember(memberId);

		MemberResponse memberInfo = MemberResponse.from(member);

		List<AddSpace> findByMemberIdAndSpaceTypes = addSpaceRepository.findSpacesByMemberIdAndSpaceTypes(memberId,List.of(SpaceType.PERSONAL, SpaceType.ORGANIZATION));

		List<AddSpaceResponse> personalSpaces = filterSpacesByType(findByMemberIdAndSpaceTypes, SpaceType.PERSONAL);

		List<AddSpaceResponse> organizationSpaces = filterSpacesByType(findByMemberIdAndSpaceTypes, SpaceType.ORGANIZATION);

		EnumMap<SpaceType, List<AddSpaceResponse>> spaces = generateSpaceTypeMap(personalSpaces, organizationSpaces);

		return new HomeResponse(memberInfo, spaces);
	}

	private List<AddSpaceResponse> filterSpacesByType(final List<AddSpace> spaces, final SpaceType spaceType) {
		return spaces
				.stream()
				.filter(space -> space.getSpaceType() == spaceType)
				.map(AddSpaceResponse::from)
				.collect(Collectors.toList());
	}

	private EnumMap<SpaceType, List<AddSpaceResponse>> generateSpaceTypeMap(final List<AddSpaceResponse> personalSpaces, final List<AddSpaceResponse> organizationSpaces) {
		EnumMap<SpaceType, List<AddSpaceResponse>> spaces = new EnumMap<>(SpaceType.class);
		spaces.put(SpaceType.PERSONAL, personalSpaces);
		spaces.put(SpaceType.ORGANIZATION, organizationSpaces);
		return spaces;
	}
}
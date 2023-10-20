package com.javajober.space.service;

import com.javajober.core.exception.ApiStatus;
import com.javajober.core.exception.ApplicationException;
import com.javajober.member.domain.Member;
import com.javajober.member.repository.MemberRepository;
import com.javajober.member.memberGroup.repository.MemberGroupRepository;
import com.javajober.space.domain.AddSpace;
import com.javajober.space.domain.SpaceType;
import com.javajober.space.dto.request.SpaceSaveRequest;
import com.javajober.space.dto.response.SpaceSaveResponse;
import com.javajober.space.repository.AddSpaceRepository;
import com.javajober.space.dto.response.SpaceResponse;
import com.javajober.space.dto.response.MemberGroupResponse;
import com.javajober.spaceWall.domain.FlagType;
import com.javajober.spaceWall.repository.SpaceWallRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SpaceService {

    private final MemberRepository memberRepository;
    private final MemberGroupRepository memberGroupRepository;
    private final AddSpaceRepository addSpaceRepository;
    private final SpaceWallRepository spaceWallRepository;

    public SpaceService(final MemberRepository memberRepository, final MemberGroupRepository memberGroupRepository,
                        final AddSpaceRepository addSpaceRepository, final SpaceWallRepository spaceWallRepository) {

        this.memberRepository = memberRepository;
        this.memberGroupRepository = memberGroupRepository;
        this.addSpaceRepository = addSpaceRepository;
        this.spaceWallRepository = spaceWallRepository;
    }

    public SpaceSaveResponse save(SpaceSaveRequest request, Long memberId) {
        Member member = memberRepository.findMember(memberId);
        AddSpace space = SpaceSaveRequest.toEntity(request, member);
        Long spaceId = addSpaceRepository.save(space).getId();

        return new SpaceSaveResponse(spaceId);
    }

    public SpaceResponse find(Long addSpaceId, String spaceTypeString, Long memberId) {
        SpaceType spaceType = SpaceType.valueOf(spaceTypeString.toUpperCase());
        List<Long> addSpaceIds = addSpaceRepository.findAddSpaceIds(spaceType, memberId);
        existsAddSpace(addSpaceId, addSpaceIds);

        List<MemberGroupResponse> memberGroupResponses = memberGroupRepository.findMemberGroup(addSpaceId);

        Optional<Long> spaceWallId = spaceWallRepository.findSpaceWallId(memberId, addSpaceId, FlagType.SAVED);

        return spaceWallId.map(id -> new SpaceResponse(true, id, memberGroupResponses))
                .orElseGet(() -> new SpaceResponse(false, null, memberGroupResponses));
    }

    private static void existsAddSpace(Long addSpaceId, List<Long> addSpaceIds) {
        boolean containsId = addSpaceIds.stream().anyMatch(id -> Objects.equals(id, addSpaceId));
        if(!containsId) {
            throw new ApplicationException(ApiStatus.NOT_FOUND, "존재하지 않는 스페이스입니다.");
        }
    }

}
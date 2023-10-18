package com.javajober.space.service;

import com.javajober.exception.ApiStatus;
import com.javajober.exception.ApplicationException;
import com.javajober.memberGroup.repository.MemberGroupRepository;
import com.javajober.space.domain.SpaceType;
import com.javajober.space.repository.AddSpaceRepository;
import com.javajober.space.dto.response.SpaceResponse;
import com.javajober.space.dto.response.MemberGroupResponse;
import com.javajober.spaceWall.domain.FlagType;
import com.javajober.spaceWall.repository.SpaceWallRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SpaceService {

    private final MemberGroupRepository memberGroupRepository;
    private final AddSpaceRepository addSpaceRepository;
    private final SpaceWallRepository spaceWallRepository;

    public SpaceService(final MemberGroupRepository memberGroupRepository, final AddSpaceRepository addSpaceRepository, final SpaceWallRepository spaceWallRepository) {
        this.memberGroupRepository = memberGroupRepository;
        this.addSpaceRepository = addSpaceRepository;
        this.spaceWallRepository = spaceWallRepository;
    }

    @Transactional
    public SpaceResponse find(final Long memberId, final Long addSpaceId, final String spaceTypeString) {

        SpaceType spaceType = SpaceType.valueOf(spaceTypeString.toUpperCase());
        List<Long> addSpaceIds = addSpaceRepository.findAddSpaceIds(spaceType, memberId); // 단체스페이스면 여러개 나옴
        existsAddSpace(addSpaceId, addSpaceIds);

        List<MemberGroupResponse> memberGroupResponses = memberGroupRepository.findMemberGroup(addSpaceId);

        Optional<Long> spaceWallId = spaceWallRepository.findSpaceWallId(memberId, addSpaceId, FlagType.SAVED);

        return spaceWallId.map(id -> new SpaceResponse(true, id, memberGroupResponses))
                .orElseGet(() -> new SpaceResponse(false, null, memberGroupResponses));
    }

    private static void existsAddSpace(Long addSpaceId, List<Long> addSpaceIds) {

        boolean containsId = addSpaceIds.stream().anyMatch(id -> Objects.equals(id, addSpaceId));
        if(!containsId) {
            throw new ApplicationException(ApiStatus.NOT_FOUND, "존재하지 않는 스페이스 입니다.");
        }
    }

}
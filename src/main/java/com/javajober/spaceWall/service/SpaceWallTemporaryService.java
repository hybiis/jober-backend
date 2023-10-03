package com.javajober.spaceWall.service;

import com.javajober.spaceWall.domain.FlagType;
import com.javajober.spaceWall.domain.SpaceWall;
import com.javajober.spaceWall.repository.SpaceWallRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SpaceWallTemporaryService {

    private final SpaceWallRepository spaceWallRepository;

    public SpaceWallTemporaryService(final SpaceWallRepository spaceWallRepository){
        this.spaceWallRepository = spaceWallRepository;
    }

    @Transactional
    public void deleteTemporary(final Long memberId, final Long addSpaceId) {
        List<SpaceWall> spaceWalls = spaceWallRepository.findSpaceWallsOrThrow(memberId, addSpaceId);

        spaceWalls.forEach(spaceWall -> {
            if (spaceWall.getFlag().equals(FlagType.PENDING)) {
                spaceWall.markAsDeleted();
            }
        });

        spaceWallRepository.saveAll(spaceWalls);
    }
}

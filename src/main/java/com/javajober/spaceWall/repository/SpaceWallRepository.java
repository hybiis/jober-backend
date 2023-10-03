package com.javajober.spaceWall.repository;

import com.javajober.spaceWall.domain.SpaceWall;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import com.javajober.core.error.exception.Exception404;
import com.javajober.core.message.ErrorMessage;

import java.util.List;
import java.util.Optional;

public interface SpaceWallRepository extends Repository<SpaceWall, Long> {

    @Query("SELECT s FROM SpaceWall s LEFT JOIN s.addSpace a LEFT JOIN s.member m " +
            "WHERE a.id = :addSpaceId AND m.id = :memberId")
    List<SpaceWall> findSpaceWalls(@Param("memberId") Long memberId, @Param("addSpaceId") Long addSpaceId);

    boolean existsByAddSpaceId(Long addSpaceId);
  
    SpaceWall save(SpaceWall spaceWall);

    default SpaceWall getById(final Long memberId, final Long spaceWallId) {
        return findSpaceWalls(memberId, spaceWallId).stream()
                .findFirst()
                .orElseThrow(() -> new Exception404(ErrorMessage.ADD_SPACE_NOT_FOUND));
    }

    Optional<SpaceWall> findById(Long spaceWallId);

    SpaceWall deleteById(Long spaceWallId);
}

package com.javajober.spaceWall.repository;

import com.javajober.core.exception.ApiStatus;
import com.javajober.core.exception.ApplicationException;
import com.javajober.spaceWall.domain.FlagType;
import com.javajober.spaceWall.domain.SpaceWall;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SpaceWallRepository extends Repository<SpaceWall, Long> {

    boolean existsByShareURL(final String shareURL);

    @Query("SELECT s FROM SpaceWall s LEFT JOIN s.addSpace a LEFT JOIN s.member m " +
            "WHERE a.id = :addSpaceId AND m.id = :memberId")
    List<SpaceWall> findSpaceWalls(@Param("memberId") final Long memberId, @Param("addSpaceId") final Long addSpaceId);

    SpaceWall save(final SpaceWall spaceWall);

    @Query("SELECT s.id FROM SpaceWall s WHERE s.member.id = :memberId AND s.addSpace.id = :addSpaceId AND s.flag = :flag")
    Optional<Long> findSpaceWallId(@Param("memberId") final Long memberId, @Param("addSpaceId") final Long addSpaceId, @Param("flag") final FlagType flag);

    Optional<SpaceWall> findByIdAndAddSpaceIdAndMemberIdAndFlag(final Long id, final Long addSpaceId, final Long memberId, final FlagType flag);

    Optional<SpaceWall> findByShareURL(final String shareURL);

    default List<SpaceWall> findSpaceWallsOrThrow(final Long memberId, final Long addSpaceId) {
        List<SpaceWall> spaceWalls = findSpaceWalls(memberId, addSpaceId);
        if (spaceWalls.isEmpty()) {
            throw new ApplicationException(ApiStatus.NOT_FOUND, "임시 저장된 공유페이지가 없습니다.");
        }
        return spaceWalls;
    }

    default SpaceWall getById(final Long memberId, final Long spaceWallId) {
        return findSpaceWalls(memberId, spaceWallId).stream()
                .findFirst()
                .orElseThrow(() -> new ApplicationException(ApiStatus.NOT_FOUND, "존재하지 않는 스페이스입니다."));
    }

    default SpaceWall findSpaceWall(Long id, Long addSpaceId, Long memberId, FlagType flag) {
        return findByIdAndAddSpaceIdAndMemberIdAndFlag(id, addSpaceId, memberId, flag)
                .orElseThrow(() -> new ApplicationException(ApiStatus.NOT_FOUND, "공유페이지를 찾을 수 없습니다."));
    }

    default SpaceWall getByShareURL(final String shareURL) {
        return findByShareURL(shareURL)
                .orElseThrow(() -> new ApplicationException(ApiStatus.NOT_FOUND, "존재하지 않는 share url입니다."));
    }
}
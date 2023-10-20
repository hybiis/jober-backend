package com.javajober.core.refreshToken.repository;

import java.util.Optional;

import com.javajober.core.refreshToken.domain.RefreshToken;
import org.springframework.data.repository.Repository;

public interface RefreshTokenRepository extends Repository<RefreshToken, Long> {
	Optional<RefreshToken> findByTokenValue(final String tokenValue);

	RefreshToken save(final RefreshToken refreshToken);

	RefreshToken delete(final RefreshToken refreshToken);

}

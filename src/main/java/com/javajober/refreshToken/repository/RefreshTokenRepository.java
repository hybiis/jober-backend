package com.javajober.refreshToken.repository;

import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.javajober.refreshToken.domain.RefreshToken;

public interface RefreshTokenRepository extends Repository<RefreshToken, Long> {
	Optional<RefreshToken> findByTokenValue(final String tokenValue);

	RefreshToken save(final RefreshToken refreshToken);

	RefreshToken delete(final RefreshToken refreshToken);

}

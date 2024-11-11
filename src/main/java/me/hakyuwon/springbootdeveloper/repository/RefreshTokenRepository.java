package me.hakyuwon.springbootdeveloper.repository;

import me.hakyuwon.springbootdeveloper.domain.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository {
    Optional<RefreshToken> findByUserId(Long userId);
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}

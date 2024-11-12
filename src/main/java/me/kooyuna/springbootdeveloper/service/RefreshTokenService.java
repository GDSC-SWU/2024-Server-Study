package me.kooyuna.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.kooyuna.springbootdeveloper.domain.RefreshToken;
import me.kooyuna.springbootdeveloper.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken).
                orElseThrow(()->new IllegalArgumentException("Unexpected token"));
    }
}

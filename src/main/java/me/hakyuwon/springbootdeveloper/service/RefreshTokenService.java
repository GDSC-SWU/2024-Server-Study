package me.hakyuwon.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.hakyuwon.springbootdeveloper.domain.RefreshToken;
import me.hakyuwon.springbootdeveloper.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository; // 오류...

    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(()->new IllegalArgumentException("Unexpected token"));
    }
}

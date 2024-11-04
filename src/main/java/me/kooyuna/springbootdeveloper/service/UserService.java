package me.kooyuna.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.kooyuna.springbootdeveloper.domain.User;
import me.kooyuna.springbootdeveloper.dto.AddUserRequest;
import me.kooyuna.springbootdeveloper.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequest dto){
        return userRepository.save(User.builder()
                .email(dto.getEmail())
                // 패스워드 저장할 때 시큐리티 설정, 빈 사용해서 암호화한 뒤 저장
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build()).getId();
    }
}

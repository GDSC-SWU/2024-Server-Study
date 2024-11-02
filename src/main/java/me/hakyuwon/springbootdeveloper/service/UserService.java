package me.hakyuwon.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.hakyuwon.springbootdeveloper.domain.User;
import me.hakyuwon.springbootdeveloper.dto.AddUserRequest;
import me.hakyuwon.springbootdeveloper.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public long save(AddUserRequest dto){
        return userRepository.save(User.builder()
                .email(dto.getEmail())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build()).getId();
    }
}

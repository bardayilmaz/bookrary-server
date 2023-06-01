package com.bookrary.server.service;

import com.bookrary.server.entity.Library;
import com.bookrary.server.entity.User;
import com.bookrary.server.entity.UserRole;
import com.bookrary.server.exception.BusinessException;
import com.bookrary.server.exception.ErrorCode;
import com.bookrary.server.model.request.LoginRequest;
import com.bookrary.server.model.request.RegisterRequest;
import com.bookrary.server.model.response.LoginResponse;
import com.bookrary.server.repository.LibraryRepository;
import com.bookrary.server.repository.UserRepository;
import com.bookrary.server.security.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final LibraryRepository libraryRepository;


    public void register(RegisterRequest registerRequest) {
        if(userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new BusinessException("Email already exists", ErrorCode.validation);
        }

        if(userRepository.existsByPhoneNumber(registerRequest.getPhoneNumber())) {
            throw new BusinessException("Phone already exists", ErrorCode.validation);
        }

        Library library = libraryRepository.findById(registerRequest.getLibraryId())
                .orElseThrow(() -> new BusinessException("Library not found", ErrorCode.resource_missing));

        User user = fromRequest(new User(), registerRequest, library);
        userRepository.save(user);
    }

    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new BusinessException("Kullanıcı bulunamadı", ErrorCode.resource_missing));

        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new BusinessException("Yanlış kullanıcı adı veya şifre", ErrorCode.unauthorized);
        }

        return LoginResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .token(jwtService.createToken(user.getId()))
                .build();
    }

    private User fromRequest(User newUser, RegisterRequest registerRequest, Library library) {
        newUser.setFirstName(registerRequest.getFirstName());
        newUser.setLastName(registerRequest.getLastName());
        newUser.setRole(UserRole.USER);
        newUser.setEmail(registerRequest.getEmail());
        newUser.setPhoneNumber(registerRequest.getPhoneNumber());
        newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        newUser.setLibrary(library);
        return newUser;
    }
}

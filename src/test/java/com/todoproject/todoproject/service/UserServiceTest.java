package com.todoproject.todoproject.service;

import com.todoproject.todoproject.dto.SignupRequestDto;
import com.todoproject.todoproject.entity.User;
import com.todoproject.todoproject.repository.UserRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

//@SpringBootTest
//@RunWith(UserService.class
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class UserServiceTest {
    @Mock
    UserRepository userRepository;
    // ADMIN_TOKEN
    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";
    @NotBlank
    @Pattern(regexp = "^[a-z0-9]{4,10}$", message = "사용자이름은 영어 소문자와 숫자만 가능하며 4~10 자리여야 합니다.")
    String username;
    @NotBlank
    @Pattern (regexp = "^[a-zA-Z0-9]{8,15}$", message = "비밀번호는 영어 대소문자와 숫자만 가능하며 8~15 자리여야 합니다.")
    String password;

    // void라서 리턴값이 없기 때문에 테스트가 불가하다고 판단
    // 따라서 RequestDto에 값을 넣고 세이브 하는 로직만 남겨놓음
    // 만들고 보니 entity 테스트와 다를 게 없다고 판단
    @Test
    void 유저회원가입테스트(){
        //given
        SignupRequestDto signupRequestDto = new SignupRequestDto();
        username = "abcd1234";
        password = "Abcd12345678";
        signupRequestDto.setUsername(username);
        signupRequestDto.setPassword(password);

        // 회원 중복 확인
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        // 사용자 등록
        User user = new User(signupRequestDto.getUsername(),signupRequestDto.getPassword());
        userRepository.save(user);

        //then
        assertEquals("abcd1234", user.getUsername());
    }

}
package com.example.membermanagement.Security;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEnCoder {
    // 비밀번호를 암호화
    public static String encodePassword(String rawPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(rawPassword);
    }

    // 입력된 비밀번호와 암호화된 비밀번호 확인
    public static boolean checkPassword(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}

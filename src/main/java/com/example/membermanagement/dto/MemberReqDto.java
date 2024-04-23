package com.example.membermanagement.dto;

import com.example.membermanagement.entity.Member;
import lombok.*;

import java.util.regex.Pattern;

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberReqDto {
    //아이디
    private String memberId;
    //비밀번호
    private String password;
    //이름
    private String name;
    //닉네임
    private String nickName;
    //전화번호
    private String phoneNum;
    //이메일
    private String email;

    // MemberReqDto -> Member엔티티
    public Member toEntity() {
        return Member.builder()
                .memberId(this.memberId)
                .password(this.password)
                .name(this.name)
                .nickName(this.nickName)
                .phoneNum(this.phoneNum)
                .email(this.email)
                .build();
    }

    public boolean validate() {
        if (phoneNum != null && !phoneNum.isEmpty()) {
            // 전화번호 유효성 검사
            Pattern phonePattern = Pattern.compile("\\d{3}-\\d{3,4}-\\d{4}");
            if (!phonePattern.matcher(phoneNum).matches()) {
                return false;
            }
        }
        if (email != null && !email.isEmpty()) {
            // 이메일 유효성 검사
            Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
            if (!emailPattern.matcher(email).matches()) {
                return false;
            }
        }
        return true;
    }
}

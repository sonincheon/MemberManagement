package com.example.membermanagement.dto;

import com.example.membermanagement.entity.Member;
import lombok.*;

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
}

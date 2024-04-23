package com.example.membermanagement.dto;

import com.example.membermanagement.entity.Member;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberResDto {
    //아이디
    private String memberId;
    //이름
    private String name;
    //닉네임
    private String nickName;
    //전화번호
    private String phoneNum;
    //이메일
    private String email;
    //가입일자
    private LocalDateTime regDate;

    // Member엔티티 -> MemberResDto
    public static MemberResDto of(Member member) {
        return MemberResDto.builder()
                .memberId(member.getMemberId())
                .name(member.getName())
                .nickName(member.getNickName())
                .phoneNum(member.getPhoneNum())
                .email(member.getEmail())
                .regDate(member.getRegDate())
                .build();
    }
}

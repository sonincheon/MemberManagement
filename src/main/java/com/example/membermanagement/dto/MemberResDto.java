package com.example.membermanagement.dto;

import lombok.*;

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
    private Integer phoneNum;
    //이메일
    private String email;
}

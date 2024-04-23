package com.example.membermanagement.entity;

import com.example.membermanagement.dto.MemberReqDto;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter @Setter
@ToString
@Table(name = "member_tb")
public class Member {
    @Id
    @Column(name = "member_num")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //회원 아이디
    @Column(nullable = false)
    private String memberId;
    //비밀번호
    @Column(nullable = false)
    private String password;
    //이름
    @Column(nullable = false)
    private String name;
    //닉네임
    @Column(nullable = false)
    private String nickName;
    //전화번호
    private String phoneNum;
    //이메일
    private String email;

    // 가입한 날짜 및 시간 (가입일순을 위한 Column)
    private LocalDateTime regDate;

    //회원 생성 시간, 지금시간으로 자동입력
    @PrePersist
    protected void prePersist() {
        regDate = LocalDateTime.now();
    }

    // 회원 정보 업데이트 메서드
    public void updateMember(MemberReqDto requestDto) {
        this.setMemberId(requestDto.getMemberId());
        this.setPassword(requestDto.getPassword());
        this.setName(requestDto.getName());
        this.setNickName(requestDto.getNickName());
        this.setPhoneNum(requestDto.getPhoneNum());
        this.setEmail(requestDto.getEmail());
    }


}

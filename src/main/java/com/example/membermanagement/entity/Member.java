package com.example.membermanagement.entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.regex.Pattern;


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

    //유효성 검사
    public String validate() {
        StringBuilder errorMessage = new StringBuilder();
        if (memberId == null || memberId.isEmpty()) {
            errorMessage.append("회원 아이디를 입력하세요.\n");
        }
        if (password == null || password.isEmpty()) {
            errorMessage.append("비밀번호를 입력하세요.\n");
        }
        if (name == null || name.isEmpty()) {
            errorMessage.append("이름을 입력하세요.\n");
        }
        if (nickName == null || nickName.isEmpty()) {
            errorMessage.append("닉네임을 입력하세요.\n");
        }
        if (phoneNum != null && !phoneNum.isEmpty()) {
            Pattern phonePattern = Pattern.compile("\\d{3}-\\d{3,4}-\\d{4}");
            if (!phonePattern.matcher(phoneNum).matches()) {
                errorMessage.append("올바른 전화번호 형식이 아닙니다. (XXX-XXXX-XXXX 또는 XXX-XXX-XXXX)\n");
            }
        }
        if (email != null && !email.isEmpty()) {
            Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
            if (!emailPattern.matcher(email).matches()) {
                errorMessage.append("올바른 이메일 형식이 아닙니다.\n");
            }
        }
        return errorMessage.toString();
    }
}

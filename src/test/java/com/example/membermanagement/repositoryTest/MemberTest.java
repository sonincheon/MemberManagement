package com.example.membermanagement.repositoryTest;

import com.example.membermanagement.entity.Member;
import com.example.membermanagement.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class MemberTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원가입 테스트")
    public void signUp(){
        for(int i =1; i<=10; i++) {
            //회원 객체 생성
            Member member = Member.builder()
                    .memberId("incheon94" + i)
                    .password("dlscjs1234"+ i)
                    .email("s44851@naver.com"+ i)
                    .name("손인천"+ i)
                    .nickName("인천쓰"+ i)
                    .email("s44851@naver.com"+ i)
                    .phoneNum("01091184893"+ i)
                    .build();
            // 회원정보 저장
            Member savedMember = memberRepository.save(member);
            System.out.println(savedMember);
        }
    }

    @Test
    @DisplayName("회원 리스트 출력 테스트")
    public void listMembers(){
        // 페이지당 회원 수와 페이지 번호 설정
        int pageSize = 5;
        int pageNumber = 1;

        // 페이지 요청 객체 생성
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        // 회원 리스트 조회
        Page<Member> memberPage = memberRepository.findAll(pageable);

        // 조회된 회원 리스트 출력
        memberPage.getContent().forEach(System.out::println);

        int totalPages = memberPage.getTotalPages();
        System.out.println("총 페이지: " + totalPages);

        int currentPageNumber = memberPage.getNumber() ;
        System.out.println("현재 페이지 번호: " + currentPageNumber);

        int totalElements = memberPage.getNumberOfElements();
        System.out.println("조회된 회원 수 확인: " + totalElements);

        int pageSizeResult = memberPage.getSize();
        System.out.println("페이지 크기 확인: " + pageSizeResult);

        long totalMembers = memberPage.getTotalElements();
        System.out.println("총 회원 수 확인: " + totalMembers);
    }
}

package com.example.membermanagement.service;


import com.example.membermanagement.Security.PasswordEnCoder;
import com.example.membermanagement.dto.MemberReqDto;
import com.example.membermanagement.dto.MemberResDto;
import com.example.membermanagement.entity.Member;
import com.example.membermanagement.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    // 회원가입
    public void signUp(MemberReqDto requestDto) {
        // 요청 DTO를 엔티티로 변환
        Member member = requestDto.toEntity();
        // 유효성 메세지
        String errorMessage = member.validate();
        // 유효성 검사
        if (!errorMessage.isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
        // 회원 아이디 중복 체크
        if (memberRepository.existsByMemberId(member.getMemberId())) {
            throw new RuntimeException("이미 사용 중인 회원 아이디 입니다.");
        }

        // 비밀번호 암호화
        String encodedPassword = PasswordEnCoder.encodePassword(member.getPassword());
        member.setPassword(encodedPassword);

        // 회원 저장
        memberRepository.save(member);
        log.info("회원가입 완료 : {}", member.getMemberId());
    }

    // 회원 리스트 페이지 수
    public int getTotalPages(int pageSize) {
        long totalMembers = memberRepository.count();
        return (int) Math.ceil((double) totalMembers / pageSize);
    }

    // 회원 페이지 조회  (현재 페이지 번호, 페이지크기, 정렬방식)
    public List<MemberResDto> getMembers(int page, int pageSize, String sortBy) {
        // JPA 정렬 방식
        Sort sort = Sort.by(sortBy);
        // 페이지 요청 객체 생성
        PageRequest pageable = PageRequest.of(page - 1, pageSize, sort);
        // 회원 리스트 조회
        Page<Member> memberPage = memberRepository.findAll(pageable);
        // 회원 엔티티를 회원 DTO로 변환
        List<MemberResDto> memberResDtoList = new ArrayList<>();
        for (Member member : memberPage) {
            memberResDtoList.add(MemberResDto.of(member));
        }
        return memberResDtoList;
    }


    // 회원 정보 수정
    public void updateMember(Long memberId, MemberReqDto requestDto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("해당 회원을 찾을 수 없습니다."));
        // 전화번호, 이메일 유효성검사
        if (!requestDto.validate()) {
            throw new IllegalArgumentException("요청된 회원 정보가 올바르지 않습니다.");
        }
        // 비밀번호 변경
        if (requestDto.getPassword() != null && !requestDto.getPassword().isEmpty()) {
            // 비밀번호 암호화
            String encodedPassword = PasswordEnCoder.encodePassword(requestDto.getPassword());
            member.setPassword(encodedPassword);
        }
        //이름 변경
        if (requestDto.getName() != null && !requestDto.getName().isEmpty()) {
            member.setName(requestDto.getName());
        }
        //닉네임 변경
        if (requestDto.getNickName() != null && !requestDto.getNickName().isEmpty()) {
            member.setNickName(requestDto.getNickName());
        }
        //휴대폰 번호 변경
        if (requestDto.getPhoneNum() != null && !requestDto.getPhoneNum().isEmpty()) {
            member.setPhoneNum(requestDto.getPhoneNum());
        }
        //이메일 번호 변경
        if (requestDto.getEmail() != null && !requestDto.getEmail().isEmpty()) {
            member.setEmail(requestDto.getEmail());
        }
        memberRepository.save(member);
        log.info("회원 정보 수정 완료: {}", member.getMemberId());
    }

    // 비밀번호 확인
    public boolean verifyPassword(Long Id, String password) {
        // 사용자 아이디로 사용자 정보를 조회
        Member member = memberRepository.findById(Id)
                .orElseThrow(() -> new RuntimeException("해당 사용자를 찾을 수 없습니다."));
        // 조회된 사용자 정보의 암호화된 비밀번호와 입력된 비밀번호를 비교하여 일치 여부를 반환
        return PasswordEnCoder.checkPassword(password, member.getPassword());
    }

}

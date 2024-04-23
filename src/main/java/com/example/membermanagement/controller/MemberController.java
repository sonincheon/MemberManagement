package com.example.membermanagement.controller;


import com.example.membermanagement.dto.MemberReqDto;
import com.example.membermanagement.dto.MemberResDto;
import com.example.membermanagement.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.membermanagement.utils.Common.CORS_ORIGIN;

@Slf4j
@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = CORS_ORIGIN)
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    //회원가입 Controller
    @PostMapping("/join")
    public ResponseEntity<String> signUp(@RequestBody MemberReqDto requestDto) {
        try {
            memberService.signUp(requestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("회원가입이 완료되었습니다.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //페이지네이션 카운트수 Controller
    @GetMapping("/totalPages")
    public ResponseEntity<Integer> getTotalPages(@RequestParam(defaultValue = "10") int pageSize) {
        int totalPages = memberService.getTotalPages(pageSize);
        return ResponseEntity.ok(totalPages);
    }

    //회원 리스트 출력 Controller
    @GetMapping("/list")
    public ResponseEntity<List<MemberResDto>> getMemberList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "name") String sortBy){
        try {
            List<MemberResDto> members = memberService.getMembers(page, pageSize, sortBy);
            return ResponseEntity.ok(members);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    //회원 정보 변경 Controller
    @PutMapping("/{id}")
    public ResponseEntity<String> updateMember(@PathVariable Long id, @RequestBody MemberReqDto requestDto) {
        try {
            memberService.updateMember(id, requestDto);
            return ResponseEntity.ok("회원 정보가 수정되었습니다.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

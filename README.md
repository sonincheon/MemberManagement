# Spring Boot 회원 관리

## 사용 스택
- JDK 1.8 (Java 8)
- Spring Boot 2.5.5
- Hibernate JPA
- MySQL
- Postman
- Swagger
- JUnit 테스트

## API 문서 자동화 및 시각화
- Swagger: [http://localhost:8111/swagger-ui.html](http://localhost:8111/swagger-ui.html)

## 공통
- Entity / Request, Response DTO 생성 (Builder 활용)
- 아시아(서울) 시간 적용
- 스웨거 메소드
- Repository는 페이지네이션과 회원가입은 JUnit 테스트로 확인
- Security 설정 (스웨거, /api/**)

## 회원가입
- [POST] http://localhost:8111/api/user/join
  - 회원 아이디가 존재하지 않으면 해당 회원은 가입되지 않은 회원이므로 가입 작업을 수행
  - 미입력, 전화번호, 이메일 유효성 검사
  - 비밀번호 암호화하여 저장 (Spring Security 이용)

## 회원 비밀번호 확인
- [POST] http://localhost:8111/api/user/verifyPw/{id}
  - 비밀번호 변경 전, 비밀번호 재 확인용 Controller

## 회원정보 수정
- [PUT] http://localhost:8111/api/user/{id}
  - 변경된 정보만 수정
  - 전화번호, 이메일 유효성 검사
  - 비밀번호 재확인 후 비밀번호 변경

## 회원 리스트 출력
- [GET] http://localhost:8111/api/user/list
  - JPA 페이지네이션 활용
  - 현재 페이지 넘버/페이지 사이즈/정렬 방식을 받아 출력
  - 정렬 종류: 이름순, 가입일순

## 페이지네이션 수
- [GET] http://localhost:8111/api/user/totalPages?pageSize=10
  - JPA 페이지네이션 활용
  - 총 회원수와 페이지 사이즈에 따른 페이지 수 출력

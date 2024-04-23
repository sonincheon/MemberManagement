# spring boot 과제 제출

### 사용 스택 [jdk1.8 (java8) 활용]
Spring Boot 2.5.5 ,Hibernate JPA ,Mysql ,PostMan ,Swager ,JUNIT 테스트활용

###  스웨거 API 문서 자동화 및 시각화 / PostMan 활용 
http://localhost:8111/swagger-ui.html

###공통
Entity / Res,ReqDTO 생성 (Builder 활용) / 아시아(서울) 시간 적용 / 스웨거 메소드 
Repository는 페이지네이션과 회원가입은 JUNIT 테스트로 확인


### 회원가입 [Post] http://localhost:8111/api/user/join
회원 아이디가 존재하지 않으면 해당 회원은 가입되지 않은 회원이므로 가입 작업을 수행

### 회원정보수정 [PUT] http://localhost:8111/api/user/{id}
변경된 정보만 수정 

### 회원 리스트 출력 [GET] http://localhost:8111/api/user/list
JPA 페이지에이블 활용 , 현재 페이지 넘버/ 페이지 사이즈 / 정렬방식 을 받아 출력
정렬 종류 : 이름순, 가입일순

### 페이지네이션 수 [GET]http://localhost:8111/api/user/totalPages?pageSize=10"
JPA 페이지에이블 활용 , 총 회원수와 페이지사이즈에 따른 페이지 수 출력


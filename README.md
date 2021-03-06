# TodoWeb
## 프로젝트 목적
React.js, 스프링 부트, AWS로 배우는 웹개발 101이라는 책에 있는 예제 코드를 복기하고 관련 내용을 주석을 달면서 스프링에 대해서 공부하고자 진행한 프로젝트입니다.

---
## 프로젝트 환경
### 1. Java 8
### 2. Spring Boot 2.6.1
### 3. react@17.0.2

---

## 프로젝트 실행 방법
```
git clone https://github.com/RicardoKim/TodoWeb.git
cd TodoWeb
npm install
cd client
npm install
cd ..
npm run dev
```

---
## 프로젝트 실행 결과

### 1. 로딩 중 페이지
<img src = "./img/로딩중 페이지.png" height = 300 width = 500>

### 2. 로그인 페이지
<img src = "./img/로그인 페이지.png" height = 300 width = 500>

### 3. 계정생성 페이지
<img src = "./img/계정생성 페이지.png" height = 300 width = 500>

### 4. 할 일 목록 페이지
<img src = "./img/TodoWeb.png" height = 300 width = 500>

### 5. 계정 삭제 페이지
<img src = "./img/탈퇴페이지.png" height = 300 width = 500>


---
## Modify

### 계정 삭제 동작 과정
1. 탈퇴 버튼을 누르면 password 입력 페이지로 이동한다.
2. Password를 입력하면 /auth/delete 를 호출한다.
3. Request에 있는 token에서 parsing해서 userId를 추출한다.
4. 추출한 userId로 userEntity를 찾아서 응답으로 받은 password와 일치하는지 확인한다.
5. 일치하면 userId로 만든 Todo를 모두 삭제한다.
6. userEnity도 삭제한다.



### server
* UserController.js 의 deleteAccount 함수
    +  @AuthenticationPrincipal 로 사용자 인증 및 userId 추출
    +  userService의 checkPassword 함수 호출해서 비밀번호 일치 여부 확인
    + userService의 delete 함수 호출

* UserService.js 의 checkPassword 함수
    +  매개변수로 받은 userId로 userRepository에서 userEntity를 찾음
    +  찾은 userEntity의 password와 매개변수로 받은 password를 대조

* UserService.js 의 delete 함수
    + todoService의 deleteAll 함수 호출해서 user의 모든 Todo를 삭제
    + userEntity를 userRepository에서 삭제

* TodoService.js의 deleteAll 함수
    + todoRepository의 deleteAllByUserId 함수 호출해서 모든 userId에 해당하는 모든 Todo 삭제


---
## Issues

* @Transactional

* Local Storage vs Session Storage

* String vs userDTO


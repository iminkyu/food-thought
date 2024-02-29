## 프로젝트 설명
자신이 읽은 책을 업로드, 공유 하고, 다른 이용자들과 공유할 수 있는 SNS 백엔드 서버

## 개발환경
- Language - Java 17
- Spring Boot - 3.2.2 version
- IDE - IntelliJ
- DataBase - MySQL
- Project - Gradle(groovy)
- 협업툴 - Github, Notion, Slack, drow.io, ERDCloud

## API 명세서
[API명세서 링크](https://www.notion.so/teamsparta/1d224ccb919c45cd916e057f2074d0b6?v=559aeadffab8478e9b868eef210bd697&pvs=4)


## ERD TABLE
![image](https://github.com/Intel-I5/food-thought/assets/155534061/748e6996-b711-4d70-a3f1-ed0c4dcae16e)



## WireFrame
![team10-wireframe drawio](https://github.com/Intel-I5/food-thought/assets/155534061/5e9b5b21-4706-4b6a-b11d-4a5650538072)



### 기능 구현사항
- [x] 사용자 인증 기능
  - [x] 회원가입 기능
    - [x]  username, password를 Client에서 전달받기
      - [x] username은  최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)로 구성  (다른 규칙 적용)
      - [x] password는  최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9), 특수문자로 구성 (다른 규칙 적용)
      - [x] DB에 중복된 username이 없다면 회원을 저장하고 Client 로 성공했다는 메시지, 상태코드 반환
      - [x] 회원 권한 부여하기 (ADMIN, USER) - ADMIN 회원은 모든 게시글, 댓글 수정 / 삭제 가능
      - [x] 회원가입시 프로필 사진 업로드 기능
      
  - [x] 로그인 및 로그아웃 기능
    - [x] username, password를 Client에서 전달받기
    - [x] DB에서 username을 사용하여 저장된 회원의 유무를 확인하고 있다면 password 비교하기
    - [x] 로그인 성공 시, 로그인에 성공한 유저의 정보와 JWT를 활용하여 토큰을 발급하고, 발급한 토큰을 Cookie에 추가하고 성공했다는 메시지, 상태코드 와 함께 Client에 반환하기

  - [x] 프로필 관리
    - [x] 프로필 수정 기능
      - [x] 이름, 한 줄 소개와 같은 정보 조회/수정
      - [x] 프로필 사진 수정 기능
           
  - [x] 게시물 CRUD기능
    - [x] 게시물 작성, 조회, 수정, 삭제 기능
      - [x] 게시물 작성, 수정, 삭제 기능에 모두 Authorization 적용 (JWT와 같은 토큰 검증 시스템)
         
  - [x] 책 CRUD기능
    - [X]  책 등록, 조회, 수정, 삭제 기능 (등록과 수정, 삭제는 관리자 권한)
      - [X] 조회 시 책 제목으로 검색 및 정렬 기능  
  
  - [x] 좋아요 기능
    - [x] 사용자가 게시물에 좋아요를 남기거나 취소 가능
    - [x] 좋아요 가장 많은 3명의 게시물 출력
    
  - [x] 팔로우 기능
    - [x] 특정 사용자를 팔로우/언팔로우
    - [x] 본인은 팔로우 불가
    - [x] 팔로우 가장 많은 3명의 회원 출력

  - [x] 댓글 CRUD기능
    - [x] 사용자는 게시물에 댓글을 작성할 수 있고 본인의 댓글은 수정 및 삭제
      - [x] 또한, 게시물과 마찬가지로 댓글 조회를 제외한 나머지 기능들은 인가(Authorization)개념이 적용
    - [x] 대댓글 기능
      
  - [x] 관리자 페이지
    - [x] 유저
      - [x] 유저 전체 검색 기능
      - [x] 유저 단일 검색 기능
      - [x] 유저 삭제 기능
      
    - [x] 게시글
      - [x] 게시글 Block 기능
      - [x] Block된 게시글까지 전부 조회하는 기능
      - [x] 게시글 삭제 기능  
      
    - [x] 댓글
      - [x] 댓글 Block 기능
      - [x] Block된 댓글까지 전부 조회하는 기능
      - [x] 댓글 삭제 기능
    

        
  
      

# 익명 게시판 서버 만들기 (내일배움캠프 Spring 3기 - 이민주 개인과제)

# **Notification: 과제 시작 전**

- **Use Case Diagram 그려보기!**

    ![익명게시판 Use Case 다이어그램 drawio](https://github.com/leeminju/SpringBoard/assets/19209147/755361e4-3b82-4546-83cc-ef1d9cf3f7ed)

    https://app.diagrams.net/#G1lFcb-crgyTEC132uhAjTVpn6mAUjujcd
    
- **API 명세서 작성하기!**
    
    https://www.postman.com/descent-module-saganist-8135654/workspace/api/collection/30859314-e5e0093f-51a9-4450-be1b-2c091b9e4c71?action=share&creator=30859314
    
    [API 명세서](https://www.notion.so/1023f237132042ca9c1559965323202a?pvs=21)
    
**ERD 작성**
    
- ![image](https://github.com/leeminju/SpringBoard/assets/19209147/78ea63e1-3372-4ac2-b228-7fa88d552990)


    https://www.erdcloud.com/d/FSLx8mZbgzq532WXr
    

# 과제 설명

- 게시글 작성 기능
    - `제목`, `작성자명`, `비밀번호`, `작성 내용`, `작성일`을 저장할 수 있습니다.
    - →**@RequestBody  RequestDto**
    - 저장된 게시글의 정보를 반환 받아 확인할 수 있습니다..
        - 반환 받은 게시글의 정보에 `비밀번호`는 제외 되어있습니다. **→ResponseDto**

        **JPA Auditing 사용해 CreateAt @Timstamp 생성**

- 선택한 게시글 조회 기능 **@PathVariable id**
    - 선택한 게시글의 정보를 조회할 수 있습니다.
        - 반환 받은 게시글의 정보에 `비밀번호`는 제외 되어있습니다. **→ResponseDto**

- 게시글 목록 조회 기능
    - 등록된 게시글 전체를 조회할 수 있습니다.
        - 반환 받은 게시글의 정보에 `비밀번호`는 제외 되어있습니다.. **→ResponseDto**
    - 조회된 게시글 목록은 작성일 기준 내림차순으로 정렬 되어있습니다.
        
        **Query Method** : **List<Post> findAllOrderByCreateAt()**
        
- 선택한 게시글 수정 기능  **@PathVariable id, @RequestBody  RequestDto**
    - 선택한 게시글의 `제목`, `작성자명`, `작성 내용`을 수정할 수 있습니다.
        - 서버에 게시글 수정을 요청할 때 `비밀번호`를 함께 전달합니다
        - 선택한 게시글의 `비밀번호`와 요청할 때 함께 보낸 `비밀번호`가 일치할 경우에만 수정이 가능합니다.
    - 수정된 게시글의 정보를 반환 받아 확인할 수 있습니다.
        - 반환 받은 게시글의 정보에 `비밀번호`는 제외 되어있습니다.. **→ResponseDto**
        
- 선택한 게시글 삭제 기능  **@PathVariable id, @ RequestBody , @RequestBody  RequestDto**
    - 선택한 게시글을 삭제할 수 있습니다.
        - 서버에 게시글 삭제를 요청할 때 `비밀번호`를 함께 전달합니다.
        - 선택한 게시글의 `비밀번호`와 요청할 때 함께 보낸 `비밀번호`가 일치할 경우에만 삭제가 가능합니다.
        

#**추가 구현 기능**
- [ ]  선택한 게시글 수정 및 삭제 요청 시 `비밀번호`가 일치하지 않을 경우 API 요청 실패(예외상황)에 대해 판단할 수 있는 Status Code, Error 메시지등의 정보를 반환합니다.

# Q & A
1. 수정, 삭제 API의 request를 어떤 방식으로 사용 하셨나요? (param, query, body)
    - @RequestBody PostRequestDto를 사용했다!
2. RESTful한 API를 설계하셨나요? 어떤 부분이 그런가요? 어떤 부분이 그렇지 않나요?
    1. 해당 API에 적절하게 HTTP 메서드를 사용했다
    2. API의 리소스 식별자를 중복 없이 고유하게 잘 만들었다.
3. 적절한 관심사 분리를 적용하셨나요? (Controller, Service, Repository)
    Contoller에서는 API 방식정의와, 요청 & 반환할 형식 지정
    서비스에서 요청 받은 데이터를 이용해 Repositroy에서 결과를 받아와 웹에 전달할 형식으로 처리
    Repository에서는 DB에서의 로직 처리
   
5. API 명세서 작성 가이드라인을 검색하여 직접 작성한 API 명세서와 비교해보세요!
  Api 설명, 요청 변수, 성공 반환 값/실패 반환 값 표시했다!
# 후기

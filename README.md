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
# 피드백
- Github README  검색 -> README 템플릿 커스텀!

- 커밋 메시지를 조금 더 명확하게 하고 커밋을 세분화 -> 기능 별로 커밋이 분리되면 추후에 리팩터링을 하거나 문제가 생겼을 때 history 를 빠르게 찾고 파악할 수 있다는 장점
  
- Controller 매우 잘 해주셨는데 `/post` 같은 경우 중복되고 있기 때문에 RequestMapping으로 `/api` 와 함께 관리하면 더 좋을 것 같습니다!
    - 추가적으로 URL 네이밍을 할 때 일반적으로 단수 보다는 복수를 더 많이 사용
    - post 보다는 posts로 수정!
      (URL 네이밍도 검색)

- HTTP Status에 대해 강의에서 따로 자세히 다루거나 소스 코드에서 명확하게 표현을 못하고 있어서 추가적으로 학습해보시면 좋은 점에대해서도 말씀드리겠습니다!
    - 모든 성공 상황에 대해 200 status로 반환되어도 기능상 문제는 없지만 리소스가 생성되는 경우에는 201, 반환되는 컨텐트가 없을 때는 204 등으로 같은 성공이라도 좀 더 상세하게 성공 상황을 구분할 수 있는 방법도 있으니 고려해보시면 매우 좋을 것 같습니다!
    - 예를 들어 요청된 게시글이 없는 경우에도 `BAD_REQUEST` 표현하는 것이 틀린 것은 아니지만 NOT_FOUND(404)로 표현 하는 것도 한번 고려해 보시면 좋을 것 같습니다!
    - 추가로 HTTP Stauts 가 100, 200, 300, 400, 500 만 있는 것이 아니라 같은 200대 라도 201, 202, 204 등으로 세부적으로 나누어진 이유는 같은 성공이라도 Client에 더 명확하게 해당 상황을 전달하기 위함이라고 생각이 듭니다

- 그래서 한번 시간을 내서 Status Code 들을 정리해보시는 것도 추천드립니다.

- `IllegalArgumentException` 우리가 예측하지 못하는 예외의 상황에서도 발생하는 Exception들 입니다.
    - 예측할 수 있는 예외들에 알맞게 처리될 수 있는 Custom Exception 사용도 한번 고려해 보시면 좋을 것 같습니다!


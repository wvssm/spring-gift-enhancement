# spring-gift-enhancement
## step0
### 기능 요구 사항
- 위시 리스트 코드를 옮겨 온다. 

### 수행한 내용
- [X] week3 코드 옮겨오기
- [X] 멘토님 리뷰 반영하여 코드 리팩토링

## step1
### 기능 요구 사항
상품 정보에 카테고리를 추가한다. 상품과 카테고리 모델 간의 관계를 고려하여 설계하고 구현한다.
- 상품에는 항상 하나의 카테고리가 있어야 한다.
  - 상품 카테고리는 수정할 수 있다.
  - 관리자 화면에서 상품을 추가할 때 카테고리를 지정할 수 있다.
- 카테고리는 1차 카테고리만 있으며 2차 카테고리는 고려하지 않는다.
- 카테고리의 예시는 아래와 같다.
  - 교환권, 상품권, 뷰티, 패션, 식품, 리빙/도서, 레저/스포츠, 아티스트/캐릭터, 유아동/반려, 디지털/가전, 카카오프렌즈, 트렌드 선물, 백화점, ...

### 구현 기능 목록
category
- [X] category 등록
- [X] category 조회
- [X] category 삭제
- [X] category 이름 수정

관리자 페이지
- [X] 상품 목록에 카테고리 추가
- [X] 상품 등록시 카테고리를 선택할 수 있는 기능 추가
- [X] 상품의 카테고리를 변경할 수 있는 기능 추가

## step2
### 기능 요구 사항
상품 정보에 옵션을 추가한다. 상품과 옵션 모델 간의 관계를 고려하여 설계하고 구현한다.
- 상품에는 항상 하나 이상의 옵션이 있어야 한다.
  - 옵션 이름은 공백을 포함하여 최대 50자까지 입력할 수 있다.
  - 특수 문자
    - 가능: ( ), [ ], +, -, &, /, _
    - 그 외 특수 문자 사용 불가
  - 옵션 수량은 최소 1개 이상 1억 개 미만이다.
- 중복된 옵션은 구매 시 고객에게 불편을 줄 수 있다. 동일한 상품 내의 옵션 이름은 중복될 수 없다.
- (선택) 관리자 화면에서 옵션을 추가할 수 있다.

### 구현 기능 목록
Option
- [X] Option Entity 생성
- [X] Product - Option (양방향 OneToMany 연관 관계 설정)
- [X] OptionRequest, OptionResponse DTO 생성
  - [X] Option 이름, 수량 검증 기능 추가
- [X] Option Repository 생성
- [X] Option Service 생성 (CRUD 기능)
  - [X] 상품의 옵션이 1개 이하일 때는 옵션 삭제 불가
- [X] Option Controller 생성 (CRUD 기능)
- [X] Product Controller에 상품에 해당하는 option 조회 기능 추가

관리자 페이지
- [X] 상품 목록 페이지에서 상품의 옵션 관리 버튼 추가
- [X] 상품 옵션 목록 조회 페이지 추가
- [X] 상품 옵션 등록 페이지 추가
- [X] 상품 옵션 변경 페이지 추가
- [X] 상품 옵션 삭제 기능 추가
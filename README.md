# BuyOrNot Convention

> 본 문서는 **buyornot 프로젝트**의 협업 효율을 위한 규칙을 정의함

---
<details> <summary>Commit Convention</summary>
  
## Commit Convention

### 1. Commit Message Format
```

<type>(<domain>): <description>

````

### 2. Type 종류
- **feat** : 기능 구현
- **fix** : 버그 수정
- **test** : 테스트 코드 추가/수정
- **docs** : 문서 수정
- **refactor** : 리팩터링
- **chore** : 설정, 의존성, 구조 변경

### 3. Commit 예시
```bash
git commit -m "fix(follow): fix follow entity"
git commit -m "feat(follow): 팔로우 추가"
git commit -m "feat(follow): 언팔로우 추가"
git commit -m "test(follow): 테스트 추가"
git commit -m "docs(follow): API 문서"
````

* `domain`은 작업 대상 도메인 또는 기능 단위로 작성
* description은 **간결하지만 의미가 명확하게** 작성
</details>

---

<details> <summary>Branch Convention</summary>
  
## Branch Convention

### 1. Branch Naming Rule

```
feature/<issue_number>/<function-summary>
```

### 2. 규칙

* 기능 요약은 **영문, 소문자**
* 단어 구분은 `-` 사용
* 기능은 **최대 2단어로 요약**

### 3. 예시

```
feature/12/post-entity
feature/7/follow-api
feature/21/hot-post
```

* `develop` 브랜치 직접 작업 금지
* 모든 기능 개발은 feature 브랜치에서 진행
</details>
  
---

<details> <summary>Issue Convention</summary>

## Issue Convention

### 1. Issue 작성 방식

* **큰 기능 단위 Issue를 먼저 생성**
* 해당 Issue 하위에 **세부 작업을 나열하는 방식**으로 관리

### 2. Issue Title 규칙

```
[Feature] 기능명
```

### 3. 예시

```
[Feature] 핫게시글 - 모놀리식
```

### 4. Issue Description 작성 예시

```
- [ ] [task] 핫게시글 엔티티 생성
- [ ] [task] 핫게시글 등록 기능
- [ ] [task] 핫게시글 조회 기능
- [ ] [task] 핫게시글 정렬 기준 정의
```

* 세부 작업은 **체크리스트 형태로 명확히 나열**
* 세부 작업은 **Issue**로 변경하여 작업 관리
</details>

---

<details> <summary>PR  Convention</summary>
  
## Pull Request Convention

### 1. Pull Request 원칙

* `develop` 또는 `main` 브랜치 병합 시 PR 필수
* PR은 Commit 컨벤션을 따름

### 2. PR Title Rule

```
<type>(<domain>): <description>
```

### 3. 예시

```
feat(follow): 팔로우 기능 구현
fix(post): 핫게시글 조회 오류 수정
```

### 4. Merge Rule

* `develop` 브랜치로 **squash merge**
* commit, push, PR merge 전 **빌드 및 실행 정상 여부 확인**
</details>

---

<details> <summary>Code Convention</summary>

## Code Convention

### 클래스 네이밍 규칙
* 명사 또는 명사구 사용
* Upper Camel Case 사용
```java
UserController
ProductService
OrderRepository
```

### 메서드/변수
* 동사 또는 동차+전치사 형태
* Lower Camel Case
```java
createOrder()
findProductById()
validateUser()
```

### 변수
* Lower Camel Case 사용
* 의미 단위로 명확하게 작성
```java
totalPrice
orderCount
isPurchased
```

### 상수
* static final 필드
* UPPER_SNAKE_CASE 사용
```java
MAX_BUY_COUNT
ORDER_COMPLETE
PAYMENT_PENDING
```

### 에너테이션 규칙

### URL 규칙

* RESTful 방식
* 소문자 사용
* `_` 대신 `-`
* 마지막 `/` 사용 금지

```text
GET  /posts/hot
POST /follows
DELETE /follows/{id}
```
</details>

---

## Rule of Thumb

* Commit 메시지: 왜 바뀌었는지 명시
* Issue: 작업 범위 명시
* PR: 작업 내용 쉽게 이해되도록 명시

---

# Next.js 환경 분리 (입문용)

이 문서는 Next.js를 처음 사용하는 사람을 대상으로  
환경 분리와 환경 변수 사용 방법을 설명한다.


---

## 1. 환경이란 무엇인가?

웹 서비스를 개발할 때는 하나의 환경만 존재하지 않는다.

보통 다음과 같이 3개의 환경을 사용한다.

- Development (개발 환경)
- Staging (검증 환경)
- Production (운영 환경)


---

## 2. 각 환경 설명

### Development (dev)

개발자가 기능을 만들고 테스트하는 환경이다.

- 내 컴퓨터(로컬)에서 실행
- 코드 수정 즉시 반영
- 오류가 발생해도 괜찮음

실행 방법:

npm run dev


---

### Staging (stg)

운영에 배포하기 전에 테스트하는 환경이다.

- 실제 운영과 거의 동일한 환경
- QA(테스트) 진행
- 버그 최종 확인

Preview 환경이라고도 부른다.


---

### Production (prod)

실제 사용자들이 사용하는 환경이다.

- 안정성이 가장 중요
- 실제 데이터 사용
- 오류 발생 시 서비스 영향 큼


---

## 3. 왜 환경을 나누는가?

환경을 나누지 않으면 다음 문제가 발생한다.

예시:

- 개발 중인 코드가 바로 사용자에게 보임
- 테스트용 DB가 실제 DB를 덮어씀
- API 주소가 꼬임

예를 들어:

개발 환경:
http://localhost:8000

운영 환경:
https://api.myapp.com

👉 같은 코드를 사용하지만, 연결되는 서버는 다르다.

그래서 환경을 나눠야 한다.


---

## 4. 환경 변수란?

환경 변수는 "환경마다 달라지는 값"을 따로 관리하는 방법이다.

코드 안에 직접 값을 넣지 않고, 외부에서 주입한다.


---

## 5. 환경 변수 왜 쓰는가?

예를 들어:

❌ 잘못된 방법

const API_URL = "http://localhost:8000"

👉 이 상태로 배포하면 운영에서도 localhost를 바라보게 된다.


✅ 올바른 방법

환경마다 다른 값을 사용한다.

개발 환경 → localhost  
운영 환경 → 실제 서버 주소  


---

## 6. Next.js 환경 변수 파일

Next.js는 .env 파일을 사용한다.

파일 종류:

.env.local  
.env.development  
.env.production  


---

## 7. 파일 역할

.env.development  
→ 개발 환경에서 사용

.env.production  
→ 운영 환경에서 사용

.env.local  
→ 개인 설정 (Git에 올리지 않음)


---

## 8. 환경 변수 작성 방법

파일 안에 다음처럼 작성한다.

NEXT_PUBLIC_API_URL=http://localhost:8000  
DB_URL=dev-db  


---

## 9. 코드에서 사용하는 방법

JavaScript 코드에서 이렇게 사용한다.

process.env.NEXT_PUBLIC_API_URL


예시:

fetch(process.env.NEXT_PUBLIC_API_URL + "/users")


---

## 10. NEXT_PUBLIC 규칙 (중요)

Next.js에는 매우 중요한 규칙이 있다.

### 1) NEXT_PUBLIC_ 붙이면

브라우저에서도 사용 가능

예:

NEXT_PUBLIC_API_URL

👉 프론트 코드에서 사용 가능


---

### 2) 안 붙이면

서버에서만 사용 가능

예:

DB_URL

👉 브라우저에서는 접근 불가 (보안 유지)


---

## 11. 실제 동작 예시

.env.development

NEXT_PUBLIC_API_URL=http://localhost:8000


.env.production

NEXT_PUBLIC_API_URL=https://api.myapp.com


코드는 동일:

fetch(process.env.NEXT_PUBLIC_API_URL)


결과:

- 개발 → localhost 호출
- 운영 → 실제 서버 호출


👉 코드 수정 없이 환경만 바뀜


---

## 12. Next.js에서 환경 분리가 가능한 이유

Next.js는 특별한 방식으로 동작한다.


### 1) 빌드 시점

npm run build 실행 시

→ 환경 변수 값이 코드에 포함됨


---

### 2) 서버 / 클라이언트 분리

Next.js는 두 가지 환경에서 실행된다.

- 서버 (Node.js)
- 브라우저 (사용자)


이때:

- NEXT_PUBLIC → 브라우저 포함
- 일반 변수 → 서버만 사용


---

### 3) 결과

- 환경마다 다른 설정 적용 가능
- 보안 유지 가능
- 코드 수정 없이 환경 변경 가능


---

## 13. 배포 환경 (간단 이해)

배포 시 보통 이렇게 나뉜다.

- dev → 로컬 개발
- stg → 테스트 서버
- prod → 실제 서비스

플랫폼이 환경에 따라 다른 값을 자동으로 넣어준다.


---

## 14. 결론

환경 분리는 웹 개발에서 필수적인 개념이다.

Next.js에서는

- .env 파일 사용
- process.env로 접근
- NEXT_PUBLIC 규칙

을 통해 환경을 분리한다.

이 구조 덕분에

- 코드 재사용 가능
- 배포 안정성 증가
- 보안 유지 가능

이라는 장점을 얻을 수 있다.

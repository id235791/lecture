# Next.js 프로젝트 생성 및 환경 분리 실습 가이드

이 문서는 Next.js 프로젝트를 생성하고  
Development / Staging / Production 환경을 설정하는 과정을 다룬다.


---

## 1. 프로젝트 생성

터미널 실행

npx create-next-app@latest my-app

옵션 선택

- TypeScript: Yes
- ESLint: Yes
- App Router: Yes
- src 폴더: Yes (선택)
- Tailwind: No (선택)


프로젝트 이동

cd my-app


개발 서버 실행

npm run dev


브라우저 접속

http://localhost:3000


---

## 2. 환경 변수 파일 생성

프로젝트 루트에 아래 파일 생성

.env.development  
.env.production  
.env.local  


---

## 3. 환경 변수 작성

### .env.development

NEXT_PUBLIC_API_URL=http://localhost:8000


### .env.production

NEXT_PUBLIC_API_URL=https://api.myapp.com


### .env.local (선택)

NEXT_PUBLIC_API_URL=http://localhost:9999


---

## 4. 환경 변수 사용 코드 작성

파일 수정

src/app/page.tsx


예시 코드

```
export default function Home() {
  const apiUrl = process.env.NEXT_PUBLIC_API_URL;

  return (
    <div>
      <h1>환경 변수 테스트</h1>
      <p>{apiUrl}</p>
    </div>
  );
}
```

---

## 5. 개발 환경 확인

개발 서버 실행

npm run dev


브라우저 확인

→ http://localhost:3000

결과

http://localhost:8000 출력됨


---

## 6. 프로덕션 환경 확인

빌드 실행

npm run build


실행

npm run start


브라우저 확인

→ http://localhost:3000

결과

https://api.myapp.com 출력됨


---

## 7. Staging 환경 (선택)

### 방법 1: 별도 파일 생성

.env.staging

NEXT_PUBLIC_API_URL=https://stg-api.myapp.com


실행

NODE_ENV=staging npm run dev


(Next.js 기본 지원은 아니므로 추가 설정 필요)


---

### 방법 2: 배포 플랫폼 사용 (권장)

브랜치 전략

feature 브랜치 → Preview  
main 브랜치 → Production  


---

## 8. .gitignore 설정 확인

.env.local 파일이 포함되어 있는지 확인

.gitignore에 포함되어 있어야 한다

.env.local


---

## 9. 정리

- .env.development → 개발 환경
- .env.production → 운영 환경
- process.env로 사용
- NEXT_PUBLIC_ → 프론트에서 사용 가능


---

## 10. 체크리스트

- 프로젝트 생성 완료
- env 파일 생성 완료
- 변수 작성 완료
- 코드에서 사용 확인
- dev / prod 값 변경 확인 완료


---

## 완료

이제 Next.js에서 환경별 설정을 분리하여 사용할 수 있다.

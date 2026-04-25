# Next.js 기본 개념

Next.js는 React 기반의 웹 애플리케이션을 쉽게 개발할 수 있도록 도와주는 프레임워크이다.  
프론트엔드와 백엔드를 함께 처리할 수 있는 풀스택 프레임워크이다.  


---

## 1. Next.js란?

Next.js는 React를 기반으로 만들어진 프레임워크로,  
웹 서비스 개발에 필요한 다양한 기능을 기본적으로 제공한다.

- 서버 사이드 렌더링 (SSR)
- 정적 사이트 생성 (SSG)
- API 서버 기능
- 라우팅 자동 처리

👉 React만 사용할 때보다 더 많은 기능을 쉽게 사용할 수 있다.  


---

## 2. React와 Next.js 차이

React는 UI를 만들기 위한 라이브러리이다.  
Next.js는 웹 애플리케이션을 만들기 위한 프레임워크이다.

### React
- UI 개발 중심
- 라우팅 직접 구현
- SEO 처리 어려움

### Next.js
- 라우팅 자동 처리
- 서버 기능 포함
- SEO 최적화 가능
- 프로젝트 구조 제공


---

## 3. 프로젝트 구조

Next.js는 파일 기반 라우팅을 사용한다.

예시 구조:

/app  
  /page.tsx  
  /about/page.tsx  


### 결과

- / → page.tsx  
- /about → about/page.tsx  

👉 폴더 구조가 곧 URL이 된다.


---

## 4. 주요 개념

### 1) 페이지 (Page)

사용자가 보는 화면이다.

예시:

export default function Home() {
  return <h1>Hello Next.js</h1>;
}


---

### 2) 레이아웃 (Layout)

여러 페이지에서 공통으로 사용하는 UI이다.

예시:

헤더, 푸터, 네비게이션 등


---

### 3) 컴포넌트 (Component)

재사용 가능한 UI 단위이다.

예시:

버튼, 카드, 리스트 등


---

## 5. 렌더링 방식

Next.js는 여러 가지 렌더링 방식을 제공한다.

### CSR (Client Side Rendering)

브라우저에서 데이터를 가져와 화면을 그린다.

- 빠른 인터랙션
- SEO 불리


---

### SSR (Server Side Rendering)

서버에서 HTML을 생성해서 전달한다.

- SEO 유리
- 초기 로딩 안정적


---

### SSG (Static Site Generation)

빌드 시 HTML을 미리 생성한다.

- 매우 빠름
- 정적인 페이지에 적합


---

## 6. API 기능

Next.js는 간단한 서버 기능도 제공한다.

예시:

/app/api/user/route.ts


→ API 서버처럼 사용 가능


---

## 7. 환경 변수

Next.js는 환경변수를 통해 설정을 관리한다.

.env 파일을 사용하며  
process.env로 접근한다.


---

## 8. 장점

- 빠른 개발 가능
- SEO 최적화 지원
- 서버 기능 포함
- 자동 라우팅
- 배포 편리


---

## 9. 언제 사용하면 좋은가?

- SEO가 중요한 서비스
- 빠르게 MVP 개발할 때
- 풀스택 개발이 필요한 경우
- React 기반 프로젝트


---

## 10. 결론

Next.js는 React 기반의 강력한 프레임워크로  
프론트엔드와 백엔드를 함께 개발할 수 있는 환경을 제공한다.

기본 구조와 개념만 이해해도  
빠르게 웹 서비스를 개발할 수 있다.

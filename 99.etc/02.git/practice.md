# Git 협업 전략 실습 가이드 (PR / 브랜치 / 코드리뷰)

이 문서는 Git 브랜치 전략, 커밋 컨벤션, PR, 코드리뷰를  
직접 따라하면서 익히기 위한 실습 가이드이다.


---

## 1. 사전 준비

### Git 초기화

git init


### 원격 저장소 연결

git remote add origin https://github.com/gudi.git


### 기본 브랜치 생성

git checkout -b main


---

## 2. 브랜치 전략 구성

다음 구조로 브랜치를 구성한다.

main → 운영  
dev → 개발 통합  
feature/* → 기능 개발  


### dev 브랜치 생성

git checkout -b dev


---

## 3. feature 브랜치 생성 및 작업

### feature 브랜치 생성

git checkout -b feature/login


### 파일 생성

login.js 생성 후 내용 추가

console.log("login");


### 커밋 작성 (컨벤션 적용)

git add .
git commit -m "feat: 로그인 기능 추가"


---

## 4. dev 브랜치로 병합 (로컬)

git checkout dev  
git merge feature/login  


---

## 5. GitHub에 push

git push origin dev  
git push origin feature/login  


---

## 6. Pull Request 생성

GitHub 접속 후 진행

1. feature/login 브랜치 선택  
2. "Compare & pull request" 클릭  
3. base: dev / compare: feature/login 선택  
4. PR 제목 작성  

예시:

feat: 로그인 기능 추가

- 로그인 기능 구현
- 기본 UI 작성


---

## 7. 코드리뷰 진행

리뷰 시 확인할 것

- 코드 가독성
- 함수 분리 여부
- 네이밍 규칙
- 불필요한 코드 여부


### 리뷰 예시

❌ 잘못된 리뷰  
이 코드 이상함  

✔ 올바른 리뷰  
이 함수는 분리하면 가독성이 더 좋아질 것 같습니다.  


---

## 8. 수정 및 재커밋

리뷰 반영 후

git add .  
git commit -m "refactor: 로그인 함수 분리"  
git push origin feature/login  


---

## 9. PR Merge

리뷰 완료 후

- Approve 클릭  
- Merge pull request 실행  


---

## 10. main 배포 흐름

dev → main 병합

git checkout main  
git merge dev  
git push origin main  


---

## 11. 전체 흐름 정리

feature 브랜치 생성  
→ 기능 개발  
→ 커밋 작성  
→ PR 생성  
→ 코드리뷰  
→ 수정  
→ merge  
→ 배포  


---

## 12. 실습 체크리스트

- [ ] main / dev 브랜치 생성 완료  
- [ ] feature 브랜치 생성 완료  
- [ ] 커밋 컨벤션 적용  
- [ ] PR 생성 완료  
- [ ] 코드리뷰 진행  
- [ ] merge 완료  


---

## 13. 추가 실습 (선택)

### 1) 버그 수정 브랜치

git checkout -b feature/fix-login  

git commit -m "fix: 로그인 오류 수정"


### 2) 문서 수정

git commit -m "docs: README 수정"


---

## 완료

이제 Git 협업의 전체 흐름(PR, 브랜치 전략, 코드리뷰)을  
실제로 수행할 수 있다.

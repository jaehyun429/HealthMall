# HealthMall Frontend

HealthMall 쇼핑몰의 프론트엔드 애플리케이션입니다.

## 기술 스택

- React 18
- TypeScript
- Vite
- React Router
- Axios

## 설치 방법

```bash
# 의존성 설치
npm install

# 개발 서버 실행 (http://localhost:3000)
npm run dev

# 프로덕션 빌드
npm run build

# 빌드 미리보기
npm run preview
```

## 프로젝트 구조

```
frontend/
├── src/
│   ├── components/      # 재사용 가능한 컴포넌트
│   │   ├── Navbar.tsx
│   │   └── PrivateRoute.tsx
│   ├── pages/          # 페이지 컴포넌트
│   │   ├── HomePage.tsx
│   │   ├── LoginPage.tsx
│   │   ├── SignupPage.tsx
│   │   ├── ItemListPage.tsx
│   │   └── ItemCreatePage.tsx
│   ├── services/       # API 서비스
│   │   ├── api.ts
│   │   ├── authService.ts
│   │   └── itemService.ts
│   ├── types/          # TypeScript 타입 정의
│   │   └── index.ts
│   ├── App.tsx         # 메인 App 컴포넌트
│   ├── main.tsx        # 진입점
│   └── index.css       # 전역 스타일
├── index.html
├── package.json
├── tsconfig.json
└── vite.config.ts
```

## 주요 기능

### 인증
- 회원가입 (`/signup`)
- 로그인 (`/login`)
- JWT 토큰 기반 인증
- Private Route 보호

### 상품 관리
- 상품 목록 조회 (`/items`)
- 상품 등록 (`/items/new`)
- 상품 분류: 도서(B), 앨범(A), 영화(M)

## API 엔드포인트

백엔드 API는 `http://localhost:8080`에서 실행됩니다.

### 인증
- `POST /api/auth/signup` - 회원가입
- `POST /api/auth/login` - 로그인

### 상품
- `GET /api/items` - 상품 목록 조회
- `POST /api/items` - 상품 등록

## 환경 설정

프론트엔드는 기본적으로 포트 3000에서 실행되며, Vite 프록시를 통해 백엔드 API(`http://localhost:8080`)와 통신합니다.

백엔드 서버가 다른 포트에서 실행되는 경우 `vite.config.ts`의 `proxy` 설정을 수정하세요.

## 백엔드 연동

1. Spring Boot 백엔드 서버를 먼저 실행하세요 (포트 8080)
2. 프론트엔드 개발 서버를 실행하세요 (포트 3000)
3. 브라우저에서 `http://localhost:3000` 접속

## 주의사항

- JWT 토큰은 localStorage에 저장됩니다
- 401 에러 발생 시 자동으로 로그인 페이지로 리다이렉트됩니다
- CORS 설정이 백엔드에서 올바르게 설정되어 있어야 합니다

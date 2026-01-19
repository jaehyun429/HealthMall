import React from 'react';
import { useNavigate } from 'react-router-dom';
import { authService } from '../services/authService';

const HomePage: React.FC = () => {
  const navigate = useNavigate();
  const isAuthenticated = authService.isAuthenticated();

  return (
    <div style={{ textAlign: 'center', padding: '100px 20px' }}>
      <h1 style={{ fontSize: '48px', marginBottom: '20px' }}>HealthMall에 오신 것을 환영합니다</h1>
      <p style={{ fontSize: '20px', color: '#666', marginBottom: '40px' }}>
        건강한 쇼핑의 시작
      </p>
      <div style={{ display: 'flex', gap: '20px', justifyContent: 'center' }}>
        {isAuthenticated ? (
          <>
            <button
              onClick={() => navigate('/items')}
              style={{
                padding: '15px 30px',
                fontSize: '18px',
                backgroundColor: '#007bff',
                color: 'white',
                border: 'none',
                cursor: 'pointer',
                borderRadius: '5px',
              }}
            >
              상품 보러가기
            </button>
            <button
              onClick={() => navigate('/items/new')}
              style={{
                padding: '15px 30px',
                fontSize: '18px',
                backgroundColor: '#28a745',
                color: 'white',
                border: 'none',
                cursor: 'pointer',
                borderRadius: '5px',
              }}
            >
              상품 등록하기
            </button>
          </>
        ) : (
          <>
            <button
              onClick={() => navigate('/login')}
              style={{
                padding: '15px 30px',
                fontSize: '18px',
                backgroundColor: '#007bff',
                color: 'white',
                border: 'none',
                cursor: 'pointer',
                borderRadius: '5px',
              }}
            >
              로그인
            </button>
            <button
              onClick={() => navigate('/signup')}
              style={{
                padding: '15px 30px',
                fontSize: '18px',
                backgroundColor: '#28a745',
                color: 'white',
                border: 'none',
                cursor: 'pointer',
                borderRadius: '5px',
              }}
            >
              회원가입
            </button>
          </>
        )}
      </div>
    </div>
  );
};

export default HomePage;

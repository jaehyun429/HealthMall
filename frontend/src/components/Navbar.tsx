import React from 'react';
import { Link } from 'react-router-dom';
import { authService } from '../services/authService';

const Navbar: React.FC = () => {
  const isAuthenticated = authService.isAuthenticated();

  return (
    <nav style={{
      backgroundColor: '#343a40',
      padding: '15px 30px',
      display: 'flex',
      justifyContent: 'space-between',
      alignItems: 'center',
    }}>
      <div>
        <Link to="/" style={{ color: 'white', textDecoration: 'none', fontSize: '24px', fontWeight: 'bold' }}>
          HealthMall
        </Link>
      </div>
      <div style={{ display: 'flex', gap: '20px' }}>
        {isAuthenticated ? (
          <>
            <Link to="/items" style={{ color: 'white', textDecoration: 'none' }}>
              상품 목록
            </Link>
            <Link to="/items/new" style={{ color: 'white', textDecoration: 'none' }}>
              상품 등록
            </Link>
          </>
        ) : (
          <>
            <Link to="/login" style={{ color: 'white', textDecoration: 'none' }}>
              로그인
            </Link>
            <Link to="/signup" style={{ color: 'white', textDecoration: 'none' }}>
              회원가입
            </Link>
          </>
        )}
      </div>
    </nav>
  );
};

export default Navbar;

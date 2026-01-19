import api from './api';
import { SignupRequest, LoginRequest, LoginResponse } from '../types';

export const authService = {
  // 회원가입
  signup: async (data: SignupRequest): Promise<string> => {
    const response = await api.post<string>('/api/auth/signup', data);
    return response.data;
  },

  // 로그인
  login: async (data: LoginRequest): Promise<LoginResponse> => {
    const response = await api.post<LoginResponse>('/api/auth/login', data);
    if (response.data.token) {
      localStorage.setItem('token', response.data.token);
    }
    return response.data;
  },

  // 로그아웃
  logout: () => {
    localStorage.removeItem('token');
  },

  // 토큰 확인
  isAuthenticated: (): boolean => {
    return !!localStorage.getItem('token');
  },
};

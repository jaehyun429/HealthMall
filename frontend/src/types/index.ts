// 회원 관련 타입
export interface Address {
  city: string;
  street: string;
  zipcode: string;
}

export interface SignupRequest {
  name: string;
  email: string;
  password: string;
  address: Address;
}

export interface LoginRequest {
  email: string;
  password: string;
}

export interface LoginResponse {
  token: string;
}

// 상품 관련 타입
export interface ItemCreateRequest {
  name: string;
  price: number;
  stockQuantity: number;
  itemType: string; // 'B' for Book, 'A' for Album, 'M' for Movie
}

export interface ItemResponse {
  id: number;
  name: string;
  price: number;
  stockQuantity: number;
  itemType: string;
}

// 주문 관련 타입
export interface OrderRequest {
  memberId: number;
  itemId: number;
  count: number;
}

export interface Member {
  id: number;
  name: string;
  email: string;
  address: Address;
}

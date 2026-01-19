import api from './api';
import { ItemCreateRequest, ItemResponse } from '../types';

export const itemService = {
  // 상품 등록
  createItem: async (data: ItemCreateRequest): Promise<ItemResponse> => {
    const response = await api.post<ItemResponse>('/api/items', data);
    return response.data;
  },

  // 상품 목록 조회
  getItems: async (): Promise<ItemResponse[]> => {
    const response = await api.get<ItemResponse[]>('/api/items');
    return response.data;
  },
};

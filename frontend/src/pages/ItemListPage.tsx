import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { itemService } from '../services/itemService';
import { ItemResponse } from '../types';
import { authService } from '../services/authService';

const ItemListPage: React.FC = () => {
  const navigate = useNavigate();
  const [items, setItems] = useState<ItemResponse[]>([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  useEffect(() => {
    fetchItems();
  }, []);

  const fetchItems = async () => {
    setLoading(true);
    setError('');
    try {
      const data = await itemService.getItems();
      setItems(data);
    } catch (err: any) {
      setError(err.response?.data || '상품 목록을 불러오는데 실패했습니다.');
    } finally {
      setLoading(false);
    }
  };

  const handleLogout = () => {
    authService.logout();
    navigate('/login');
  };

  const getItemTypeLabel = (itemType: string) => {
    switch (itemType) {
      case 'B':
        return '도서';
      case 'A':
        return '앨범';
      case 'M':
        return '영화';
      default:
        return itemType;
    }
  };

  return (
    <div style={{ maxWidth: '1000px', margin: '50px auto', padding: '20px' }}>
      <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '20px' }}>
        <h2>상품 목록</h2>
        <div>
          <button
            onClick={() => navigate('/items/new')}
            style={{
              padding: '10px 20px',
              backgroundColor: '#28a745',
              color: 'white',
              border: 'none',
              cursor: 'pointer',
              marginRight: '10px',
            }}
          >
            상품 등록
          </button>
          <button
            onClick={handleLogout}
            style={{
              padding: '10px 20px',
              backgroundColor: '#dc3545',
              color: 'white',
              border: 'none',
              cursor: 'pointer',
            }}
          >
            로그아웃
          </button>
        </div>
      </div>

      {loading && <div>로딩 중...</div>}
      {error && <div style={{ color: 'red', marginBottom: '15px' }}>{error}</div>}

      {!loading && items.length === 0 && (
        <div style={{ textAlign: 'center', padding: '50px', color: '#666' }}>
          등록된 상품이 없습니다.
        </div>
      )}

      {!loading && items.length > 0 && (
        <table style={{ width: '100%', borderCollapse: 'collapse' }}>
          <thead>
            <tr style={{ backgroundColor: '#f8f9fa' }}>
              <th style={{ padding: '12px', border: '1px solid #ddd', textAlign: 'left' }}>ID</th>
              <th style={{ padding: '12px', border: '1px solid #ddd', textAlign: 'left' }}>상품명</th>
              <th style={{ padding: '12px', border: '1px solid #ddd', textAlign: 'left' }}>분류</th>
              <th style={{ padding: '12px', border: '1px solid #ddd', textAlign: 'right' }}>가격</th>
              <th style={{ padding: '12px', border: '1px solid #ddd', textAlign: 'right' }}>재고</th>
            </tr>
          </thead>
          <tbody>
            {items.map((item) => (
              <tr key={item.id}>
                <td style={{ padding: '12px', border: '1px solid #ddd' }}>{item.id}</td>
                <td style={{ padding: '12px', border: '1px solid #ddd' }}>{item.name}</td>
                <td style={{ padding: '12px', border: '1px solid #ddd' }}>
                  {getItemTypeLabel(item.itemType)}
                </td>
                <td style={{ padding: '12px', border: '1px solid #ddd', textAlign: 'right' }}>
                  {item.price.toLocaleString()}원
                </td>
                <td style={{ padding: '12px', border: '1px solid #ddd', textAlign: 'right' }}>
                  {item.stockQuantity}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default ItemListPage;

import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { itemService } from '../services/itemService';
import { ItemCreateRequest } from '../types';

const ItemCreatePage: React.FC = () => {
  const navigate = useNavigate();
  const [formData, setFormData] = useState<ItemCreateRequest>({
    name: '',
    price: 0,
    stockQuantity: 0,
    itemType: 'B',
  });
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: name === 'price' || name === 'stockQuantity' ? Number(value) : value,
    }));
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError('');
    setLoading(true);

    try {
      await itemService.createItem(formData);
      alert('상품이 등록되었습니다!');
      navigate('/items');
    } catch (err: any) {
      setError(err.response?.data || '상품 등록에 실패했습니다.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={{ maxWidth: '500px', margin: '50px auto', padding: '20px' }}>
      <h2>상품 등록</h2>
      <form onSubmit={handleSubmit}>
        <div style={{ marginBottom: '15px' }}>
          <label>상품명:</label>
          <input
            type="text"
            name="name"
            value={formData.name}
            onChange={handleChange}
            required
            style={{ width: '100%', padding: '8px', marginTop: '5px' }}
          />
        </div>
        <div style={{ marginBottom: '15px' }}>
          <label>상품 분류:</label>
          <select
            name="itemType"
            value={formData.itemType}
            onChange={handleChange}
            required
            style={{ width: '100%', padding: '8px', marginTop: '5px' }}
          >
            <option value="B">도서</option>
            <option value="A">앨범</option>
            <option value="M">영화</option>
          </select>
        </div>
        <div style={{ marginBottom: '15px' }}>
          <label>가격:</label>
          <input
            type="number"
            name="price"
            value={formData.price}
            onChange={handleChange}
            required
            min="0"
            style={{ width: '100%', padding: '8px', marginTop: '5px' }}
          />
        </div>
        <div style={{ marginBottom: '15px' }}>
          <label>재고 수량:</label>
          <input
            type="number"
            name="stockQuantity"
            value={formData.stockQuantity}
            onChange={handleChange}
            required
            min="0"
            style={{ width: '100%', padding: '8px', marginTop: '5px' }}
          />
        </div>
        {error && <div style={{ color: 'red', marginBottom: '15px' }}>{error}</div>}
        <div style={{ display: 'flex', gap: '10px' }}>
          <button
            type="submit"
            disabled={loading}
            style={{
              flex: 1,
              padding: '10px',
              backgroundColor: '#007bff',
              color: 'white',
              border: 'none',
              cursor: loading ? 'not-allowed' : 'pointer',
            }}
          >
            {loading ? '처리중...' : '등록'}
          </button>
          <button
            type="button"
            onClick={() => navigate('/items')}
            style={{
              flex: 1,
              padding: '10px',
              backgroundColor: '#6c757d',
              color: 'white',
              border: 'none',
              cursor: 'pointer',
            }}
          >
            취소
          </button>
        </div>
      </form>
    </div>
  );
};

export default ItemCreatePage;

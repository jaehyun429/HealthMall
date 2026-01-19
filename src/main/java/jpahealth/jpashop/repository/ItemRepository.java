package jpahealth.jpashop.repository;

import jpahealth.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;

import java.util.List;


public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByName(String name);

    // "가격이 특정 값보다 저렴한 상품을 찾고 싶다"는 메서드 '정의'
    List<Item> findByPriceLessThan(int price);
}
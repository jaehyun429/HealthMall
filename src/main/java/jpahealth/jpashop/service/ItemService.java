package jpahealth.jpashop.service;

import jpahealth.jpashop.domain.item.Item;
import jpahealth.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public Long saveItem(Item item) {
        // save 메서드는 JpaRepository에 그대로 있으므로 수정할 필요 없습니다.
        itemRepository.save(item);
        return null;
    }

    /**
     * 영속성 컨텍스트가 자동 변경 (Dirty Checking)
     */
    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        // 1. findOne(id) -> findById(id)로 변경
        // 2. Optional<Item>을 반환하므로, .orElseThrow()로 예외 처리
        Item findItem = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalStateException("해당 아이템이 존재하지 않습니다. ID: " + itemId));

        // 변경 감지를 이용한 업데이트
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);
    }

    public List<Item> findItems() {
        // findAll 메서드도 JpaRepository에 그대로 있으므로 수정할 필요 없습니다.
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        // 여기도 마찬가지로 findById와 Optional 처리로 변경
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalStateException("해당 아이템이 존재하지 않습니다. ID: " + itemId));
    }
}
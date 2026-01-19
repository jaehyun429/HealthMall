package jpahealth.jpashop.service;

import jpahealth.jpashop.domain.item.Clothes;
import jpahealth.jpashop.domain.item.Equipment;
import jpahealth.jpashop.domain.item.Food;
import jpahealth.jpashop.domain.item.Item;
import jpahealth.jpashop.dto.ItemCreateRequest;
import org.springframework.stereotype.Component;

@Component
public class ItemFactory {

    public Item createItem(ItemCreateRequest request) {
        String itemType = request.getItemType();

        if ("C".equals(itemType)) {
            Clothes clothes = new Clothes();
            clothes.setName(request.getName());
            clothes.setPrice(request.getPrice());
            // ... Clothes 관련 필드 설정 ...
            return clothes;
        } else if ("E".equals(itemType)) {
            Equipment equipment = new Equipment();
            equipment.setName(request.getName());
            // ... Equipment 관련 필드 설정 ...
            return equipment;
        }
        else if ("F".equals(itemType)) {
            Food food = new Food();
            food.setName(request.getName());
            // ... food 관련 필드 설정 ...
            return food;
        }
        // 유효하지 않은 타입에 대한 예외 처리
        throw new IllegalArgumentException("Invalid item type: " + itemType);
    }
}
package jpahealth.jpashop.dto;

import jpahealth.jpashop.domain.item.Item;
import lombok.Getter;

@Getter
public class ItemResponseDto {

    private final Long id;
    private final String name;
    private final int price;
    private final int stockQuantity;
    private final String itemType;

    // 이 외에 응답에 포함하고 싶은 추가 필드들...

    /**
     * Entity를 DTO로 변환하는 생성자
     */
    public ItemResponseDto(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.price = item.getPrice();
        this.stockQuantity = item.getStockQuantity();
        this.itemType = item.getItemType();
    }
}
package jpahealth.jpashop.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemCreateRequest {

    // --- 기존 필드 ---
    @NotEmpty(message = "상품 이름은 필수입니다.")
    private String name;

    @NotNull(message = "가격은 필수입니다.")
    @Positive(message = "가격은 0보다 커야 합니다.")
    private Integer price;

    @NotNull(message = "재고 수량은 필수입니다.")
    @PositiveOrZero(message = "재고 수량은 0 이상이어야 합니다.")
    private Integer stockQuantity;

    // --- 여기에 타입 필드 추가 ---
    @NotEmpty(message = "상품 타입을 지정해주세요. (예: B, A, M)")
    private String itemType; // 1. 클라이언트로부터 받을 상품 타입 필드

    /**
     * 상품 타입을 반환하는 메서드
     * @return DTO에 저장된 itemType 값
     */
    public String getItemType() {
        return this.itemType; // 2. 추가된 필드를 그대로 반환
    }

    // 이 외에 Book, Album 등의 모든 속성을 여기에 포함할 수도 있습니다.
    // 예: private String author;
    // 예: private String artist;
}
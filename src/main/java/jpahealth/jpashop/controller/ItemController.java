package jpahealth.jpashop.controller;

import jpahealth.jpashop.domain.item.Item;
import jpahealth.jpashop.dto.ItemCreateRequest;
import jpahealth.jpashop.dto.ItemResponseDto;
import jpahealth.jpashop.dto.ItemUpdateRequest;
import jpahealth.jpashop.service.ItemFactory;
import jpahealth.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController // 데이터(JSON)를 반환하는 컨트롤러
@RequestMapping("/api/items") // 이 컨트롤러의 모든 경로는 /api/items 로 시작
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final ItemFactory itemFactory;

    /**
     * 상품 등록 API
     */
    @PostMapping
    public ResponseEntity<?> createItem(@RequestBody ItemCreateRequest request) {
        // 팩토리를 통해 구체적인 상품 객체를 생성
        Item item = itemFactory.createItem(request);

        // 서비스는 Item 타입으로 받아서 처리
        itemService.saveItem(item);

        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    /**
     * 상품 목록 조회 API
     */
    @GetMapping("")
    public ResponseEntity<List<ItemResponseDto>> getItems() {
        List<Item> items = itemService.findItems();
        List<ItemResponseDto> result = items.stream()
                .map(ItemResponseDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }

    /**
     * 단일 상품 조회 API
     */
    @GetMapping("/{itemId}")
    public ResponseEntity<ItemResponseDto> getItem(@PathVariable Long itemId) {
        Item item = itemService.findOne(itemId);
        return ResponseEntity.ok(new ItemResponseDto(item));
    }

    /**
     * 상품 수정 API
     */
    @PatchMapping("/{itemId}") // PATCH는 부분 수정, PUT은 전체 수정을 의미
    public ResponseEntity<ItemResponseDto> updateItem(
            @PathVariable Long itemId,
            @RequestBody @Valid ItemUpdateRequest request) {

        itemService.updateItem(itemId, request.getName(), request.getPrice(), request.getStockQuantity());
        Item findItem = itemService.findOne(itemId);

        return ResponseEntity.ok(new ItemResponseDto(findItem));
    }
}
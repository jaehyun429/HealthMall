package jpahealth.jpashop.controller;

import jakarta.validation.Valid;
import jpahealth.jpashop.domain.item.Item;
import jpahealth.jpashop.dto.ItemCreateRequest;
import jpahealth.jpashop.dto.ItemResponseDto;
import jpahealth.jpashop.service.ItemFactory; // 팩토리 클래스 (별도 구현 필요)
import jpahealth.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemApiController {

    private final ItemService itemService;
    private final ItemFactory itemFactory; // 팩토리 패턴 사용 시

    @PostMapping
    public ResponseEntity<ItemResponseDto> createItem(@RequestBody @Valid ItemCreateRequest request) {
        Item item = itemFactory.createItem(request);
        Long itemId = itemService.saveItem(item);
        Item findItem = itemService.findOne(itemId);
        return new ResponseEntity<>(new ItemResponseDto(findItem), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ItemResponseDto>> getItems() {
        List<Item> items = itemService.findItems();
        List<ItemResponseDto> result = items.stream()
                .map(ItemResponseDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    // ... 단일 조회, 수정 API 등 추가 ...
}
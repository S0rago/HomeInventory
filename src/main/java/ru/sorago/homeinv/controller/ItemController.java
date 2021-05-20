package ru.sorago.homeinv.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.sorago.homeinv.data.request.ItemRequest;
import ru.sorago.homeinv.data.response.base.BaseResponse;
import ru.sorago.homeinv.data.response.type.ItemData;
import ru.sorago.homeinv.service.ItemService;

@Controller
@RequestMapping("api/v1/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping("")
    public ResponseEntity<BaseResponse> getAllItems(Integer offset, Integer perPage) {
        return ResponseEntity.ok(itemService.getAllItems(offset, perPage));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getItem(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.getItem(id));
    }

    @PostMapping("")
    public ResponseEntity<BaseResponse> addItem(@RequestBody ItemRequest request) {
        return ResponseEntity.ok(itemService.addItem(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteItem(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.deleteItem(id));
    }

    @PutMapping("/{id}}")
    public ResponseEntity<BaseResponse> editItem(@PathVariable Long id, @RequestBody ItemRequest request) {
        return ResponseEntity.ok(itemService.editItem(id, request));
    }
}

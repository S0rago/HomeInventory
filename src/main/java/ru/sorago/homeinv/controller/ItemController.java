package ru.sorago.homeinv.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.sorago.homeinv.data.request.ItemRequest;
import ru.sorago.homeinv.data.response.base.Response;
import ru.sorago.homeinv.service.ItemService;

@Controller
@RequestMapping("api/v1/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/")
    public ResponseEntity<Response> getAllItems(int offset, int perPage) {
        return ResponseEntity.ok(itemService.getAllItems(offset, perPage));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getItem(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.getItem(id));
    }

    @PostMapping("/")
    public ResponseEntity<Response> addItem(ItemRequest request) {
        return ResponseEntity.ok(itemService.addItem(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteItem(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.deleteItem(id));
    }

    @PutMapping("/{id}}")
    public ResponseEntity<Response> editItem(@PathVariable Long id, @RequestBody ItemRequest request) {
        return ResponseEntity.ok(itemService.editItem(id, request));
    }
}

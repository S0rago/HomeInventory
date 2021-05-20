package ru.sorago.homeinv.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.sorago.homeinv.data.request.TypeRequest;
import ru.sorago.homeinv.data.response.base.BaseResponse;
import ru.sorago.homeinv.service.ItemTypeService;

@Controller
@RequestMapping("api/vi/types")
@RequiredArgsConstructor
public class ItemTypeController {
    private final ItemTypeService itemTypeService;

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getType(@PathVariable Long id) {
        return ResponseEntity.ok(itemTypeService.getType(id));
    }

    @GetMapping("/")
    public ResponseEntity<BaseResponse> getAllTypes() {
        return ResponseEntity.ok(itemTypeService.getAllTypes());
    }

    @PostMapping("/")
    public ResponseEntity<BaseResponse> addType(TypeRequest request) {
        return ResponseEntity.ok(itemTypeService.addType(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> editType(@PathVariable Long id, TypeRequest request) {
        return ResponseEntity.ok(itemTypeService.editType(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteType(@PathVariable Long id) {
        return ResponseEntity.ok(itemTypeService.deleteType(id));
    }
}

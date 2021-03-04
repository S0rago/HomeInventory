package ru.sorago.homeinv.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sorago.homeinv.data.response.base.Response;
import ru.sorago.homeinv.service.ItemTypeService;

@Controller
@RequestMapping("api/v1/type")
@RequiredArgsConstructor
public class ItemTypeController {
    private final ItemTypeService itemTypeService;

    @PostMapping
    public ResponseEntity<Response> addType() {
        return ResponseEntity.ok(itemTypeService.addType());
    }
}

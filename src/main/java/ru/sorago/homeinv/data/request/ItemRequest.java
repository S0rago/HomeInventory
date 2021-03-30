package ru.sorago.homeinv.data.request;

import lombok.Data;
import ru.sorago.homeinv.model.ItemType;

@Data
public class ItemRequest {
    private String name;
    private ItemType type;
}

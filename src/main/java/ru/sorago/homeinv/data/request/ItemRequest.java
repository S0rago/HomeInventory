package ru.sorago.homeinv.data.request;

import lombok.Data;
import ru.sorago.homeinv.model.ItemProp;
import ru.sorago.homeinv.model.ItemType;

import java.util.Set;

@Data
public class ItemRequest {
    private String name;
    private String typeStr;
    private ItemType type;
    private Set<ItemProp> props;
}

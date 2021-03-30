package ru.sorago.homeinv.data.response.type;

import lombok.Data;
import ru.sorago.homeinv.model.ItemType;

@Data
public class ItemInResponse {
    private long id;
    private String name;
    private ItemType type;

    public ItemInResponse() {

    }

    public ItemInResponse(long id, String name, ItemType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
}

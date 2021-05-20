package ru.sorago.homeinv.data.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import ru.sorago.homeinv.model.ItemType;

@Data
public class ItemRequest {
    private String name;
    private String typeStr;
    private ItemType type;
    private String props;
}

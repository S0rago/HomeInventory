package ru.sorago.homeinv.data.response.type;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import ru.sorago.homeinv.model.ItemType;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemData {
    private long id;
    private String name;
    private ItemType type;
    private String typeStr;
    private String props;

    public ItemData() {

    }

    public ItemData(long id, String name, ItemType type, String typeStr, String props) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.typeStr = typeStr;
        this.props = props;
    }
}

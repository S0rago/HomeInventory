package ru.sorago.homeinv.data.response.type;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import ru.sorago.homeinv.model.ItemProp;
import ru.sorago.homeinv.model.ItemType;

import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemData {
    private long id;
    private String name;
    private ItemType type;
    private String typeStr;
    private Set<ItemProp> props;

    public ItemData() {

    }

    public ItemData(long id, String name, ItemType type, String typeStr, Set<ItemProp> props) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.typeStr = typeStr;
        this.props = props;
    }
}

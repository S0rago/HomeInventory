package ru.sorago.homeinv.service;

import ru.sorago.homeinv.data.request.ItemRequest;
import ru.sorago.homeinv.data.response.base.ListResponse;
import ru.sorago.homeinv.data.response.base.RecordResponse;
import ru.sorago.homeinv.data.response.type.ItemData;
import ru.sorago.homeinv.data.response.type.ResponseMessage;

public interface ItemService {
    ListResponse<ItemData> getAllItems(Integer offset, Integer perPage);
    RecordResponse<ItemData> getItem(Long id);
    RecordResponse<ItemData> addItem(ItemRequest request);
    RecordResponse<ResponseMessage> deleteItem(Long id);
    RecordResponse<ItemData> editItem(Long id, ItemRequest request);
}

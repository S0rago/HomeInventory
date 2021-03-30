package ru.sorago.homeinv.service;

import ru.sorago.homeinv.data.request.ItemRequest;
import ru.sorago.homeinv.data.response.base.ListResponse;
import ru.sorago.homeinv.data.response.base.RecordResponse;
import ru.sorago.homeinv.data.response.type.ItemInResponse;
import ru.sorago.homeinv.data.response.type.ResponseMessage;

public interface ItemService {
    ListResponse<ItemInResponse> getAllItems(int offset, int perPage);
    RecordResponse<ItemInResponse> getItem(Long id);
    RecordResponse<ItemInResponse> addItem(ItemRequest request);
    RecordResponse<ResponseMessage> deleteItem(Long id);
    RecordResponse<ItemInResponse> editItem(Long id, ItemRequest request);
}

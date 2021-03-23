package ru.sorago.homeinv.service;

import ru.sorago.homeinv.data.request.TypeRequest;
import ru.sorago.homeinv.data.response.base.ListResponse;
import ru.sorago.homeinv.data.response.base.RecordResponse;
import ru.sorago.homeinv.data.response.type.ResponseMessage;
import ru.sorago.homeinv.data.response.type.TypeData;

public interface ItemTypeService {
    RecordResponse<TypeData> getType(Long id);
    ListResponse<TypeData> getAllTypes();
    RecordResponse<TypeData> addType(TypeRequest request);
    RecordResponse<TypeData> editType(Long id, TypeRequest request);
    RecordResponse<ResponseMessage> deleteType(Long id);
}

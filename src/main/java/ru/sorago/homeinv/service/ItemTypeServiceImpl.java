package ru.sorago.homeinv.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sorago.homeinv.data.request.TypeRequest;
import ru.sorago.homeinv.data.response.base.ListResponse;
import ru.sorago.homeinv.data.response.base.RecordResponse;
import ru.sorago.homeinv.data.response.type.ResponseMessage;
import ru.sorago.homeinv.data.response.type.TypeData;
import ru.sorago.homeinv.exception.ApiError;
import ru.sorago.homeinv.exception.BadRequestException;
import ru.sorago.homeinv.model.ItemType;
import ru.sorago.homeinv.repository.ItemTypeRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ItemTypeServiceImpl implements ItemTypeService {
    private final ItemTypeRepository itemTypeRepository;

    @Override
    public RecordResponse<TypeData> getType(Long id) {
        RecordResponse<TypeData> response = new RecordResponse<>();
        Optional<ItemType> optionalType = itemTypeRepository.findById(id);
        if (optionalType.isPresent()) {
            ItemType type = optionalType.get();
            response.setData(TypeToData(type));
        }
        return response;
    }

    @Override
    public ListResponse<TypeData> getAllTypes() {
        ListResponse<TypeData> response = new ListResponse<>();
        for (ItemType t : itemTypeRepository.findAll()) {
            response.add(TypeToData(t));
        }
        return response;
    }

    @Override
    @Transactional
    public RecordResponse<TypeData> addType(TypeRequest request) {
        if (request == null) {
            throw new BadRequestException(new ApiError("Missing type"));
        }
        RecordResponse<TypeData> response = new RecordResponse<>();
        TypeData data = saveType(null, request);
        response.setData(data);
        return response;
    }

    @Override
    @Transactional
    public RecordResponse<TypeData> editType(Long id, TypeRequest request) {
        if (request == null) {
            throw new BadRequestException(new ApiError("Missing type"));
        }
        RecordResponse<TypeData> response = new RecordResponse<>();
        TypeData data = saveType(id, request);
        response.setData(data);
        return response;
    }

    @Override
    public RecordResponse<ResponseMessage> deleteType(Long id) {
        if (id == null) {
            throw new BadRequestException(new ApiError("Missing type id"));
        }
        itemTypeRepository.deleteById(id);
        return new RecordResponse<>(new ResponseMessage("Item deleted successfully"));
    }

    TypeData TypeToData(ItemType type) {
        TypeData data = new TypeData();
        data.setId(type.getId());
        data.setName(type.getName());
        return data;
    }

    private TypeData saveType(Long id, TypeRequest request) {
        ItemType itemType;
        if (id == null) {
            itemType = new ItemType();
        } else {
            itemType = itemTypeRepository.findById(id).orElseThrow();
        }
        itemType.setName(request.getName());

        itemTypeRepository.save(itemType);

        TypeData data = TypeToData(itemType);
        return data;
    }
}

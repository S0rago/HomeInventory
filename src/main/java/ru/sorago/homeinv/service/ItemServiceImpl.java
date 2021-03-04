package ru.sorago.homeinv.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.sorago.homeinv.core.ContextUtilities;
import ru.sorago.homeinv.core.OffsetPageRequest;
import ru.sorago.homeinv.data.request.ItemRequest;
import ru.sorago.homeinv.data.response.base.ListResponse;
import ru.sorago.homeinv.data.response.base.RecordResponse;
import ru.sorago.homeinv.data.response.type.ItemInResponse;
import ru.sorago.homeinv.data.response.type.ResponseMessage;
import ru.sorago.homeinv.exception.ApiError;
import ru.sorago.homeinv.exception.BadRequestException;
import ru.sorago.homeinv.model.Item;
import ru.sorago.homeinv.repository.ItemRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    @Override
    public ListResponse<ItemInResponse> getAllItems(int offset, int perPage) {
        ListResponse<ItemInResponse> response = new ListResponse<>();
        Pageable pageable = new OffsetPageRequest(offset, perPage, Sort.by("time").descending());
        Page<Item> itemPage = itemRepository.findAllByUserId(pageable, ContextUtilities.getCurrentUserId());
        itemPage.get().forEach(item -> response.add(itemToResponse(item)));
        return response;
    }

    @Override
    public RecordResponse<ItemInResponse> getItem(Long id) {
        if (id == null) {
            throw new BadRequestException(new ApiError("Missing item id"));
        }
        RecordResponse<ItemInResponse> response = new RecordResponse<>();
        Optional<Item> optionalItem = itemRepository.findById(id);
        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();
            ItemInResponse itemInResponse = itemToResponse(item);
            response.setData(itemInResponse);
        }
        return response;
    }

    @Override
    @Transactional
    public RecordResponse<ItemInResponse> addItem(ItemRequest request) {
        RecordResponse<ItemInResponse> response = new RecordResponse<>();
        ItemInResponse itemInResponse = saveItem(null, request);
        response.setData(itemInResponse);
        return response;
    }

    @Override
    public RecordResponse<ResponseMessage> deleteItem(Long id) {
        if (id == null) {
            throw new BadRequestException(new ApiError("Missing item id"));
        }
        itemRepository.deleteById(id);
        return new RecordResponse<>(new ResponseMessage("Item deleted successfully"));
    }

    @Override
    @Transactional
    public RecordResponse<ItemInResponse> editItem(Long id, ItemRequest request) {
        if (request == null) {
            throw new BadRequestException(new ApiError("Missing item"));
        }
        RecordResponse<ItemInResponse> response = new RecordResponse<>();
        ItemInResponse itemInResponse = saveItem(id, request);
        response.setData(itemInResponse);
        return response;
    }

    private ItemInResponse itemToResponse(Item item) {
        ItemInResponse itemInResponse = new ItemInResponse();
        itemInResponse.setId(item.getId());
        itemInResponse.setName(item.getName());
        itemInResponse.setType(item.getType());
        return itemInResponse;
    }

    private ItemInResponse saveItem(Long id, ItemRequest request) {
        ItemInResponse itemInResponse = new ItemInResponse();
        Item item;
        if (id == null) {
            item = new Item();
        } else {
            item = itemRepository.findById(id).orElseThrow();
        }
        item.setName(request.getName());
        item.setType(request.getType());

        itemRepository.save(item);

        itemInResponse.setId(item.getId());
        itemInResponse.setName(item.getName());
        itemInResponse.setType(item.getType());
        return itemInResponse;
    }
}

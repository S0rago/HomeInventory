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
import ru.sorago.homeinv.data.response.type.ItemData;
import ru.sorago.homeinv.data.response.type.ResponseMessage;
import ru.sorago.homeinv.exception.ApiError;
import ru.sorago.homeinv.exception.BadRequestException;
import ru.sorago.homeinv.model.Item;
import ru.sorago.homeinv.model.ItemType;
import ru.sorago.homeinv.model.User;
import ru.sorago.homeinv.repository.ItemRepository;
import ru.sorago.homeinv.repository.ItemTypeRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final ItemTypeRepository itemTypeRepository;

    @Override
    public ListResponse<ItemData> getAllItems(Integer offset, Integer perPage) {
        ListResponse<ItemData> response = new ListResponse<>();
        if (offset == null || perPage == null) {
            User user = ContextUtilities.getCurrentUser();
            List<Item> itemList = itemRepository.findAllByOwner(user);
            for (Item item : itemList) {
                response.add(itemToResponse(item));
            }
        } else {
            Pageable pageable = new OffsetPageRequest(offset, perPage, Sort.by("time").descending());
            Page<Item> itemPage = itemRepository.findAllByOwner(pageable, ContextUtilities.getCurrentUser());
            itemPage.get().forEach(item -> response.add(itemToResponse(item)));
        }
        return response;
    }

    @Override
    public RecordResponse<ItemData> getItem(Long id) {
        if (id == null) {
            throw new BadRequestException(new ApiError("Missing item id"));
        }
        RecordResponse<ItemData> response = new RecordResponse<>();
        Optional<Item> optionalItem = itemRepository.findById(id);
        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();
            ItemData itemData = itemToResponse(item);
            response.setData(itemData);
        }
        return response;
    }

    @Override
    @Transactional
    public RecordResponse<ItemData> addItem(ItemRequest request) {
        RecordResponse<ItemData> response = new RecordResponse<>();
        ItemData itemData = saveItem(null, request);
        response.setData(itemData);
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
    public RecordResponse<ItemData> editItem(Long id, ItemRequest request) {
        if (request == null) {
            throw new BadRequestException(new ApiError("Missing item"));
        }
        RecordResponse<ItemData> response = new RecordResponse<>();
        ItemData itemData = saveItem(id, request);
        response.setData(itemData);
        return response;
    }

    private ItemData itemToResponse(Item item) {
        ItemData itemData = new ItemData();
        itemData.setId(item.getId());
        itemData.setName(item.getName());
        itemData.setType(item.getType());
        itemData.setProps(item.getProps());
        return itemData;
    }

    private ItemData saveItem(Long id, ItemRequest request) {
        Item item;
        if (id == null) {
            item = new Item();
        } else {
            item = itemRepository.findById(id).orElseThrow();
        }
        item.setName(request.getName());
        if (request.getType() != null) {
            item.setType(request.getType());
        } else {
            ItemType type;
            Optional<ItemType> optional= itemTypeRepository.findByName(request.getTypeStr());
            if (optional.isPresent()) {
                type = optional.get();
            } else {
                type = new ItemType();
                type.setName(request.getTypeStr());
                type = itemTypeRepository.save(type);
            }
            item.setType(type);
        }
        itemRepository.save(item);
        ItemData itemData = new ItemData();
        itemData.setId(item.getId());
        item.setOwner(ContextUtilities.getCurrentUser());
        itemData.setName(item.getName());
        itemData.setType(item.getType());
        return itemData;
    }
}

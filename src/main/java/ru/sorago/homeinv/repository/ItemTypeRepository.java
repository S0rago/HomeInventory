package ru.sorago.homeinv.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sorago.homeinv.model.ItemType;

@Repository
public interface ItemTypeRepository extends CrudRepository<ItemType, Long> {
}

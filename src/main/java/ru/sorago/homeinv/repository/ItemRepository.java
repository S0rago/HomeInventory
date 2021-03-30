package ru.sorago.homeinv.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.sorago.homeinv.model.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
    Page<Item> findAllByUserId(Pageable pageable, int userId);
}

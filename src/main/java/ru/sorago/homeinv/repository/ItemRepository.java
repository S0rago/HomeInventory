package ru.sorago.homeinv.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.sorago.homeinv.model.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sorago.homeinv.model.User;

import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
    List<Item> findAllByOwner(User owner);
    Page<Item> findAllByOwner(Pageable pageable, User owner);
}

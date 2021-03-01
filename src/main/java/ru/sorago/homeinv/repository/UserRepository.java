package ru.sorago.homeinv.repository;

import ru.sorago.homeinv.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPasswordHash(String email, String passwordHash);
}

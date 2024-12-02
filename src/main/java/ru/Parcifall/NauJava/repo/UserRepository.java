package ru.Parcifall.NauJava.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.Parcifall.NauJava.ent.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@RepositoryRestResource
public interface UserRepository extends CrudRepository<User, Long> {
    @Query("FROM User WHERE subscription.title = :subscription")
    List<User> findBySubscription(String subscription);

    User findByName(String name);
}

package ru.Parcifall.NauJava.repo;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.Parcifall.NauJava.ent.Subscription;
import ru.Parcifall.NauJava.ent.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    @Query("FROM User WHERE subscription.title = :subscription")
    List<User> findBySubscription(String subscription);
}

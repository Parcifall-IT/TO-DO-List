package ru.Parcifall.NauJava.repo;

import org.springframework.data.jpa.repository.Query;
import ru.Parcifall.NauJava.data.access.CrudRepository;
import ru.Parcifall.NauJava.ent.Subscription;
import ru.Parcifall.NauJava.ent.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    @Query("FROM tbl_users WHERE subscription_title = :subscription")
    List<User> findBySubscription(String subscription);
}

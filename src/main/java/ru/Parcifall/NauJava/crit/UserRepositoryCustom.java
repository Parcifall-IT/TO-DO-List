package ru.Parcifall.NauJava.crit;

import ru.Parcifall.NauJava.ent.User;

import java.util.List;

public interface UserRepositoryCustom {
    List<User> findBySubscription(String subscription);
}

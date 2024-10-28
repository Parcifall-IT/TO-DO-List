package ru.Parcifall.NauJava.service;

import ru.Parcifall.NauJava.ent.User;

public interface UserService {
    User getUserByName(String Name);
    void addUser(User user);
    void deleteBySubscription(String subscription);
}

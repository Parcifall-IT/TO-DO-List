package ru.Parcifall.NauJava.service;

import ru.Parcifall.NauJava.ent.Task;
import ru.Parcifall.NauJava.ent.User;

public interface UserService {
    User getUserByName(String Name);
    void addUser(User user) throws Exception;
    void deleteBySubscription(String subscription);
    void addTaskToUser(User user, Task task);
}

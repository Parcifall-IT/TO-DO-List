package ru.Parcifall.NauJava.crit;

import ru.Parcifall.NauJava.ent.Task;

import java.util.List;

public interface NewTaskRepositoryCustom {
    List<Task> findByTitleAndDescription(String title, String description);
}

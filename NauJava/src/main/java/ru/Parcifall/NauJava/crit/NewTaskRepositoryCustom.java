package ru.Parcifall.NauJava.crit;

import ru.Parcifall.NauJava.ent.NewTask;

import java.util.List;

public interface NewTaskRepositoryCustom {
    List<NewTask> findByTitleAndDescription(String title, String description);
}

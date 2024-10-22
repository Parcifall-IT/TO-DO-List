package ru.Parcifall.NauJava.crit;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import ru.Parcifall.NauJava.ent.Task;

import java.util.List;

@Repository
public class NewTaskRepositoryImpl implements NewTaskRepositoryCustom {
    private final EntityManager entityManager;

    public NewTaskRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Task> findByTitleAndDescription(String title, String description) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Task> criteriaQuery = criteriaBuilder.createQuery(Task.class);

        Root<Task> taskRoot = criteriaQuery.from(Task.class);
        Predicate predicate = criteriaBuilder.and(
                criteriaBuilder.equal(taskRoot.get("title"), title),
                criteriaBuilder.equal(taskRoot.get("description"), description));

        criteriaQuery.select(taskRoot).where(predicate);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}

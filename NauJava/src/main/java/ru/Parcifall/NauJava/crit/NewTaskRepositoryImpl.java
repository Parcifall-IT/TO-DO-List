package ru.Parcifall.NauJava.crit;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import ru.Parcifall.NauJava.ent.NewTask;
import ru.Parcifall.NauJava.ent.User;

import java.util.List;

@Repository
public class NewTaskRepositoryImpl implements NewTaskRepositoryCustom {
    private final EntityManager entityManager;

    public NewTaskRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<NewTask> findByTitleAndDescription(String title, String description) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<NewTask> criteriaQuery = criteriaBuilder.createQuery(NewTask.class);

        Root<NewTask> taskRoot = criteriaQuery.from(NewTask.class);
        Predicate predicate = criteriaBuilder.and(
                criteriaBuilder.equal(taskRoot.get("title"), title),
                criteriaBuilder.equal(taskRoot.get("description"), description));

        criteriaQuery.select(taskRoot).where(predicate);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public void save(NewTask task) {
        entityManager.persist(task);
    }
}

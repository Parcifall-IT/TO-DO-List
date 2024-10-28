package ru.Parcifall.NauJava;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.Parcifall.NauJava.crit.NewTaskRepositoryCustom;
import ru.Parcifall.NauJava.crit.UserRepositoryCustom;
import ru.Parcifall.NauJava.ent.*;
import ru.Parcifall.NauJava.repo.*;
import ru.Parcifall.NauJava.service.UserService;

import java.util.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

//@Transactional
@SpringBootTest
class NauJavaApplicationTests {
	private final UserRepository userRepository;
	private final TaskRepository taskRepository;
	private final NewTaskRepositoryCustom taskRepositoryCustom;
	private final UserRepositoryCustom userRepositoryCustom;
	private final UserService userService;
	private final SubscriptionRepository subscriptionRepository;
	private final TaskTypeRepository typeRepository;
	private final TaskStatusRepository statusRepository;


	@Autowired
    NauJavaApplicationTests(UserRepository userRepository, TaskRepository taskRepository, NewTaskRepositoryCustom taskRepositoryCustom, UserRepositoryCustom userRepositoryCustom, UserService userService, SubscriptionRepository subscriptionRepository, TaskTypeRepository typeRepository, TaskStatusRepository statusRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.taskRepositoryCustom = taskRepositoryCustom;
        this.userRepositoryCustom = userRepositoryCustom;
        this.userService = userService;
        this.subscriptionRepository = subscriptionRepository;
        this.typeRepository = typeRepository;
        this.statusRepository = statusRepository;
    }

    @Test
	void testFindUserBySub() {
		String name = UUID.randomUUID().toString();
		Subscription subscription = new Subscription("admin", Calendar.getInstance(), 123.5);

		User user = new User(name);
		user.setSubscription(subscription);

		subscriptionRepository.save(subscription);
		userRepository.save(user);

		User foundUser = userRepository.findBySubscription("admin").getFirst();
		Assertions.assertNotNull(foundUser);
		Assertions.assertEquals(user.getId(), foundUser.getId());
		Assertions.assertEquals(user.getSubscription().getTitle(), foundUser.getSubscription().getTitle());
	}

	@Test
	void testFindTaskByTitleAndDisc() {
		String title = UUID.randomUUID().toString();
		String description = UUID.randomUUID().toString();
		Calendar period = Calendar.getInstance();
		TaskType type = new TaskType();
		TaskStatus status = new TaskStatus();

		Task task = new Task(type, title, description, status, period);

		typeRepository.save(type);
		statusRepository.save(status);
		taskRepository.save(task);

		Task foundTask = taskRepository.findByTitleAndDescription(title, description).getFirst();

		Assertions.assertNotNull(foundTask);
		Assertions.assertEquals(task.getId(), foundTask.getId());
		Assertions.assertEquals(task.getTitle(), foundTask.getTitle());
		Assertions.assertEquals(task.getDescription(), foundTask.getDescription());
	}

	@Test
	void testFindUserBySubCustom() {
		String name = UUID.randomUUID().toString();
		Subscription subscription = new Subscription("admin", Calendar.getInstance(), 123.5);

		User user = new User(name);
		user.setSubscription(subscription);

		subscriptionRepository.save(subscription);
		userRepository.save(user);

		User foundUser = userRepositoryCustom.findBySubscription("admin").getFirst();
		Assertions.assertNotNull(foundUser);
		Assertions.assertEquals(user.getId(), foundUser.getId());
		Assertions.assertEquals(user.getSubscription().getTitle(), foundUser.getSubscription().getTitle());
	}

	@Test
	void testFindTaskByTitleAndDiscCustom() {
		String title = UUID.randomUUID().toString();
		String description = UUID.randomUUID().toString();
		Calendar period = Calendar.getInstance();
		TaskType type = new TaskType();
		TaskStatus status = new TaskStatus();

		Task task = new Task(type, title, description, status, period);

		typeRepository.save(type);
		statusRepository.save(status);
		taskRepository.save(task);

		Task foundTask = taskRepositoryCustom.findByTitleAndDescription(title, description).getFirst();

		Assertions.assertNotNull(foundTask);
		Assertions.assertEquals(task.getId(), foundTask.getId());
		Assertions.assertEquals(task.getTitle(), foundTask.getTitle());
		Assertions.assertEquals(task.getDescription(), foundTask.getDescription());
	}

	@Test
	public void deleteBySubscription_ShouldDeleteUsers_WhenSubscriptionExists() {
		String title = "premium";
		Calendar period = Calendar.getInstance();
		Double cost = 123.5;
		Subscription subscription = new Subscription(title, period, cost);
		subscriptionRepository.save(subscription);

		User user1 = new User("user1");
		user1.setSubscription(subscription);
		User user2 = new User("user2");
		user2.setSubscription(subscription);

		userRepository.save(user1);
		userRepository.save(user2);

		userService.deleteBySubscription(title);

		List<User> users = userRepository.findBySubscription(title);
		Assertions.assertEquals(users, new ArrayList<User>());
	}

	@Test
	public void testDeleteBySubscription_ShouldRollbackTransaction_WhenDataAccessExceptionThrown() {
		String title = "premium";
		Calendar period = Calendar.getInstance();
		Double cost = 123.5;
		Subscription subscription = new Subscription(title, period, cost);
		subscriptionRepository.save(subscription);

		User user = new User("user");
		user.setSubscription(subscription);
		userRepository.save(user);

		assertThatThrownBy(() -> {
			userService.deleteBySubscription(title);
			throw new Exception() {};
		}).isInstanceOf(Exception.class);

		Assertions.assertNotNull(userRepository.findBySubscription(title));
	}
}

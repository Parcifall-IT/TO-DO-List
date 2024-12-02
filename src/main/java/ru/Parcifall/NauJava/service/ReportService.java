package ru.Parcifall.NauJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.Parcifall.NauJava.ent.Report;
import ru.Parcifall.NauJava.ent.ReportStatus;
import ru.Parcifall.NauJava.ent.Task;
import ru.Parcifall.NauJava.ent.User;
import ru.Parcifall.NauJava.repo.ReportRepository;
import ru.Parcifall.NauJava.repo.TaskRepository;
import ru.Parcifall.NauJava.repo.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ReportService {
    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public ReportService(ReportRepository reportRepository, UserRepository userRepository, TaskRepository taskRepository) {
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    public String getContent(Report report) {
        return report.getContent();
    }

    public Report getById(Long id) {
        return reportRepository.findById(id).get();
    }

    public Long createReport() {
        Report myReport = new Report();
        reportRepository.save(myReport);
        return myReport.getId();
    }

    public void generateReport(long reportId) {
        final long[] allCountTime = new long[1];
        Report report = reportRepository.findById(reportId).get();
        final int[] userCount = new int[1];
        final List<Task>[] tasksList = new List[1];
        final long[] userCountTime = new long[1];
        final long[] findTasksTime = new long[1];

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            Thread thread1 = new Thread(() -> {
                long startTime = System.currentTimeMillis();
                userCount[0] = countUsers();
                userCountTime[0] = System.currentTimeMillis() - startTime;
            });
            thread1.setName("countUsers");

            Thread thread2 = new Thread(() -> {
                long startTime = System.currentTimeMillis();
                tasksList[0] = findTasks();
                findTasksTime[0] = System.currentTimeMillis() - startTime;
            });
            thread2.setName("findTasks");

            long startTimeAll = System.currentTimeMillis();
            thread1.start();
            thread2.start();

            try {
                thread1.join();
                thread2.join();
            } catch (InterruptedException ex) {
                throw new IllegalStateException(ex);
            }
            allCountTime[0] = System.currentTimeMillis() - startTimeAll;
            return userCount[0] + "\n" +
                    userCountTime[0] + "\n" +
                    Arrays.toString(tasksList) + "\n" +
                    findTasksTime[0] + "\n" +
                    allCountTime[0];
        });

        future.thenAccept(result -> {
            report.setContent(result);
            report.setStatus(ReportStatus.COMPLETED);
            reportRepository.save(report);
        });

        future.exceptionally(ex -> {
            report.setStatus(ReportStatus.ERROR);
            reportRepository.save(report);
            return "Ошибка обработана";
        });
    }

    private int countUsers() {
        int result = 0;
        for (User u : userRepository.findAll()) {
            result++;
        }
        return result;
    }

    private List<Task> findTasks() {
        List<Task> result = new ArrayList<>();
        taskRepository.findAll().forEach(result::add);
        return result;
    }
}

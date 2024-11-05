package ru.Parcifall.NauJava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.Parcifall.NauJava.ent.Report;
import ru.Parcifall.NauJava.ent.ReportStatus;
import ru.Parcifall.NauJava.service.ReportService;

import java.util.HashMap;

@RestController
@RequestMapping("/reports")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping("/create")
    public ResponseEntity<Long> createReport() {
        long reportId = reportService.createReport();
        reportService.generateReport(reportId);
        return ResponseEntity.ok(reportId);
    }

    @GetMapping("/get/{id}")
    public ModelAndView getReport(@PathVariable("id") long reportId) {
        Report report = reportService.getById(reportId);

        if (report == null) {
            return new ModelAndView("error");
        }

        ModelAndView modelAndView = new ModelAndView("report");

        String[] content = report.getContent().split("\n");

        String userCount = content[0];
        String countUsersTime = content[1];
        String tasks = content[2];
        String findTasksTime = content[3];
        String totalTime = content[4];

        modelAndView.addObject("status", report.getStatus() == ReportStatus.COMPLETED ? "Отчет завершен." :
                report.getStatus() == ReportStatus.ERROR ? "Отчет завершился с ошибкой." :
                        "Отчет еще формируется.");
        modelAndView.addObject("userCount", userCount);
        modelAndView.addObject("countUsersTime", countUsersTime);
        modelAndView.addObject("tasks", tasks);
        modelAndView.addObject("findTasksTime", findTasksTime);
        modelAndView.addObject("totalTime", totalTime);

        return modelAndView;
    }
}

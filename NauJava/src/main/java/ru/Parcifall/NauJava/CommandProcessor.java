//package ru.Parcifall.NauJava;
//
//import ru.Parcifall.NauJava.ent.Status;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import ru.Parcifall.NauJava.service.TaskService;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//
//@Component
//public class CommandProcessor {
//    private final TaskService taskService;
//
//    @Autowired
//    public CommandProcessor(TaskService taskService) {
//        this.taskService = taskService;
//    }
//
//    public void processCommand(String input) {
//        String[] cmd = input.split(" ");
//        switch (cmd[0]) {
//            case "create" -> {
//                String time = cmd[4] + " " + cmd[5];
//                String pattern = "yyyy-MM-dd HH:mm:ss";
//                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
//                try {
//                    Date date = sdf.parse(time);
//                    Calendar calendar = Calendar.getInstance();
//                    calendar.setTime(date);
//                    taskService.createTask(Long.valueOf(cmd[1]), cmd[2], cmd[3], calendar, Status.INITIALISED);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//            }
//            case "start" -> taskService.updateStatus(Long.valueOf(cmd[1]), Status.PROGRESS);
//
//            case "finish" -> taskService.updateStatus(Long.valueOf(cmd[1]), Status.FINISHED);
//
//            case "ruin" -> taskService.updateStatus(Long.valueOf(cmd[1]), Status.FAILED);
//
//            case "deadline" -> {
//                String time = cmd[2] + " " + cmd[3];
//                String pattern = "yyyy-MM-dd HH:mm:ss";
//                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
//                try {
//                    Date date = sdf.parse(time);
//                    Calendar calendar = Calendar.getInstance();
//                    calendar.setTime(date);
//                    taskService.updateDeadline(Long.valueOf(cmd[1]), calendar);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            case "show" -> taskService.findById(Long.valueOf(cmd[1]));
//
//            case "delete" -> taskService.deleteById(Long.valueOf(cmd[1]));
//
//            default -> System.out.println("Введена неизвестная команда...");
//        }
//    }
//}

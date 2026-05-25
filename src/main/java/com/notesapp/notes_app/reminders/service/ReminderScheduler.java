package com.notesapp.notes_app.reminders.service;

import com.notesapp.notes_app.email.service.EmailService;
import com.notesapp.notes_app.tasks.entity.Task;
import com.notesapp.notes_app.tasks.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReminderScheduler {

    private final TaskRepository taskRepository;

    private final EmailService emailService;

    @Scheduled(fixedRate = 60000)
    public void checkTaskReminders() {

        List<Task> tasks =
                taskRepository.findAll();

        for (Task task : tasks) {

            if (
                    !task.isReminderEnabled()
                            ||
                            task.isCompleted()
            ) {

                continue;
            }

            LocalDateTime now =
                    LocalDateTime.now();

            LocalDateTime dueDate =
                    task.getDueDate();

            if (dueDate == null) {

                continue;
            }

            long hoursUntilDue =
                    Duration.between(
                            now,
                            dueDate
                    ).toHours();

            if (
                    hoursUntilDue <= 24
                            &&
                            !task.isOneDayReminderSent()
            ) {

                sendReminder(
                        task,
                        "1 Day Reminder"
                );

                task.setOneDayReminderSent(true);
            }

            if (
                    hoursUntilDue <= 12
                            &&
                            hoursUntilDue > 1
                            &&
                            !task.isTwelveHourReminderSent()
            ) {

                sendReminder(
                        task,
                        "12 Hour Reminder"
                );

                task.setTwelveHourReminderSent(true);
            }

            if (
                    hoursUntilDue <= 1
                            &&
                            !task.isOneHourReminderSent()
            ) {

                sendReminder(
                        task,
                        "1 Hour Reminder"
                );

                task.setOneHourReminderSent(true);
            }

            taskRepository.save(task);
        }
    }

    private void sendReminder(
            Task task,
            String reminderType
    ) {

        System.out.println("REMINDER METHOD CALLED");

        System.out.println(task.getTitle());

        System.out.println(task.getUser().getEmail());

        emailService.sendReminderEmail(

                task.getUser().getEmail(),

                reminderType
                        + " - "
                        + task.getTitle()
        );

        System.out.println("EMAIL SENT");
    }
}
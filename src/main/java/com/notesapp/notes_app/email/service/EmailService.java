package com.notesapp.notes_app.email.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendReminderEmail(

            String toEmail,

            String taskTitle

    ) {

        SimpleMailMessage message =
                new SimpleMailMessage();

        message.setTo(toEmail);

        message.setSubject(
                "Task Reminder - Remindly"
        );

        message.setText(
                "Reminder for your task: "
                        + taskTitle
        );

        mailSender.send(message);
    }
}
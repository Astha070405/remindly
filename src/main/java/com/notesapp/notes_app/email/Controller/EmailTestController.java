package com.notesapp.notes_app.email.Controller;

import com.notesapp.notes_app.email.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmailTestController {
    private final EmailService emailService;

    @GetMapping("api/email/test")
    public String testEmail(){
        emailService.sendReminderEmail(
                "remindly.notify@gmail.com",

                "Finish Spring Boot Project"
        );
        return "Email sent";
    }

}

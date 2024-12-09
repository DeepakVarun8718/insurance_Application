package in.deepak.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.deepak.service.NotificationService;

@RestController
@RequestMapping("insurance")
public class NotificationController {
    private NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

   

    @GetMapping("/email/{recipientEmail}/message/{message}/send")
    public String sendEmail(@PathVariable String recipientEmail, @PathVariable String message) {
        notificationService.sendNotification(recipientEmail, message);
        return "CHECK MAIL";
    }
}
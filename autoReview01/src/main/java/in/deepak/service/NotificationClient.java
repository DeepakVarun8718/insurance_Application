package in.deepak.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@FeignClient(name = "NOTIFICATION-APPLICATION1")
public interface NotificationClient {
    
    @RequestMapping("/insurance/email1/{recipientEmail}/message1/{message}/send")
    void sendEmail(@PathVariable String recipientEmail, @PathVariable String message);
}

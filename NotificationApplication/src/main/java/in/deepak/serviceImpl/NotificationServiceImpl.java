package in.deepak.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.internet.MimeMessage;

import in.deepak.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private JavaMailSender javaMailSender;
    
    @Override
    public void sendNotification(String recipientEmail, String message) {
    	
    	System.out.println("Mail send successfully "+recipientEmail+"  "+message);
   
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        try {
            helper.setTo(recipientEmail);
            helper.setSubject("Notification from Insurance Application");
            helper.setText(message);
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
    }
}

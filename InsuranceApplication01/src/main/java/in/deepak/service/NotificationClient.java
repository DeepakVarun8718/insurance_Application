package in.deepak.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient(name = "NOTIFICATION-APPLICATION1")
public interface NotificationClient {
 
	@RequestMapping("/insurance/email1/{email1}/message1/{message1}/send")
	void sendMail(@PathVariable String email1,@PathVariable String message1); 
	
	
	
	@RequestMapping("/insurance/name/{name}")
	void sendName(@PathVariable String name);
}

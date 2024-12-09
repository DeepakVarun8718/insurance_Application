package in.deepak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AutoReview01Application {

	public static void main(String[] args) {
		SpringApplication.run(AutoReview01Application.class, args);
	}

}

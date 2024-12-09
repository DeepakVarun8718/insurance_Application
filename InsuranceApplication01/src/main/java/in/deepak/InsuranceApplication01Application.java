package in.deepak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class InsuranceApplication01Application {

	public static void main(String[] args) {
		SpringApplication.run(InsuranceApplication01Application.class, args);
	}

}

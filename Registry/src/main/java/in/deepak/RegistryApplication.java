package in.deepak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RegistryApplication {

	public static void main(String[] args) {
//		log.info("Starting Eureka Registry Service Application");
		System.out.println("Starting Eureka Registry Service Application");
		SpringApplication.run(RegistryApplication.class, args);
//		log.info("Eureka Registry Server Running Perfectly");
		System.out.println("Eureka Registry Server Running Perfectly");
	}

}

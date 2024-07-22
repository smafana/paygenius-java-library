package za.co.paygenius.developer.paygeniusjavalibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PayGeniusJavaLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayGeniusJavaLibraryApplication.class, args);
	}

}

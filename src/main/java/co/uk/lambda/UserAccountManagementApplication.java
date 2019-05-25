package co.uk.lambda;

import co.uk.lambda.domain.Account;
import co.uk.lambda.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class UserAccountManagementApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserAccountManagementApplication.class, args);
	}
}

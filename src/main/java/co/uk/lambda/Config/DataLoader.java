package co.uk.lambda.Config;

import co.uk.lambda.domain.Account;
import co.uk.lambda.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class DataLoader implements ApplicationRunner {

    private final AccountRepository repository;

    @Autowired
    public DataLoader(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account account1 = new Account(1L,"Account1");
        Account account2 = new Account(2L,"Account2");
        Account account3 = new Account(3L,"Account3");
        Account account4 = new Account(4L,"Account4");

        log.info("Inserting data in DB");

        repository.save(account1);
        repository.save(account2);
        repository.save(account3);
        repository.save(account4);

        log.info("Account count: {}", repository.count());
    }
}

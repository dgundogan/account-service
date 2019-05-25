package co.uk.lambda.service;

import co.uk.lambda.domain.Account;
import co.uk.lambda.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;

    public AccountServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Account> getAccountById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Account> getAllAccounts() {
        return repository.findAll();
    }

    @Override
    public void updateAccountById(Long id, Account account) {
        if (this.repository.findById(id).isPresent())
            repository.save(account);
    }

    @Override
    public void deleteAccountById(Long id) {
        repository.deleteById(id);
    }
}

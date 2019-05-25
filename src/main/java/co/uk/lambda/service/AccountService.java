package co.uk.lambda.service;

import co.uk.lambda.domain.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    Optional<Account> getAccountById(Long id);

    List<Account> getAllAccounts();

    void updateAccountById(Long id, Account account);

    void deleteAccountById(Long id);
}

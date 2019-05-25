package co.uk.lambda.controller;

import co.uk.lambda.domain.Account;
import co.uk.lambda.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
@Slf4j
public class AccountController {

    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getById(@PathVariable("id") long id) {
        log.debug("Fetching Account with id {}", id);
        Optional result = this.service.getAccountById(id);
        if (result.isPresent())
            return ResponseEntity.ok((Account) result.get());
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping()
    public ResponseEntity<List<Account>> getAll() {
        return ResponseEntity.ok(this.service.getAllAccounts());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable("id") long id, Account account) {
        this.service.updateAccountById(id, account);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") long id) {
        log.debug("Fetching & deleting Account with id {}", id);
        service.deleteAccountById(id);
    }
}

package co.uk.lambda.controller;

import co.uk.lambda.domain.Account;
import co.uk.lambda.service.AccountService;
import co.uk.lambda.service.AccountServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AccountControllerTest {

    private AccountService service;
    private AccountController controller;

    @Before
    public void setUp() {
        service = Mockito.mock(AccountServiceImpl.class);
        controller = new AccountController(service);
    }

    @Test
    public void getById() {
        Optional<Account> expected = Optional.of(Account.builder().id(10L).name("test").build());
        when(this.service.getAccountById(any(Long.class))).thenReturn(expected);
        ResponseEntity actual = controller.getById(10L);
        Assert.assertEquals(HttpStatus.OK, actual.getStatusCode());
        verify(this.service).getAccountById(10L);
    }

    @Test
    public void getAll() {
        ResponseEntity actual = controller.getAll();
        Assert.assertEquals(HttpStatus.OK, actual.getStatusCode());
        verify(this.service).getAllAccounts();
    }

    @Test
    public void update() {
        Account expected = Account.builder().id(10L).name("test").build();
        ResponseEntity actual = controller.update(10L, expected);
        Assert.assertEquals(HttpStatus.OK, actual.getStatusCode());
        verify(this.service).updateAccountById(10L, expected);
    }

    @Test
    public void deleteById() {
        controller.deleteById(any(Long.class));
        verify(this.service).deleteAccountById(any(Long.class));
    }
}
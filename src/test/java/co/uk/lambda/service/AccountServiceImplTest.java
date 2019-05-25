package co.uk.lambda.service;

import co.uk.lambda.domain.Account;
import co.uk.lambda.repository.AccountRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AccountServiceImplTest {

    private AccountRepository repository;
    private AccountService service;

    @Before
    public void setUp() {
        repository = Mockito.mock(AccountRepository.class);
        service = new AccountServiceImpl(repository);
    }

    @Test
    public void givenId_whenCallGetAccountById_thenReturnsAccount() {
        Optional<Account> expected = Optional.of(Account.builder().id(10L).name("test").build());

        when(repository.findById(any(Long.class))).thenReturn(expected);

        Optional<Account> actual = service.getAccountById(10L);

        verify(repository).findById(10L);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getAllAccounts() {
        List<Account> expected = Collections.singletonList(Account.builder().id(10L).name("test").build());

        when(repository.findAll()).thenReturn(expected);

        List<Account> actual = service.getAllAccounts();

        verify(repository).findAll();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void givenAccount_whenCallUpdateAccountById_thenUpdateAccount() {

        Account expected = Account.builder().id(10L).name("test").build();
        when(this.service.getAccountById(10L)).thenReturn(Optional.of(Account.builder().id(10L).build()));

        service.updateAccountById(10L, expected);

        verify(repository).save(expected);
    }

    @Test
    public void givenAccountId_whenCallDeleteAccountById_thenDeleteAccount() {
        service.deleteAccountById(any(Long.class));

        verify(repository).deleteById(any(long.class));
    }
}
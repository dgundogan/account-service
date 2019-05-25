package co.uk.lambda.controller;

import co.uk.lambda.domain.Account;
import co.uk.lambda.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = AccountController.class)
@AutoConfigureWebClient
@EnableAutoConfiguration
public class AccountControllerIntegrationTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private AccountService service;
    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void getById() throws Exception {
        Account account = Account.builder().id(10L).name("test").build();
        when(service.getAccountById(10L)).thenReturn(Optional.of(account));

        mockMvc.perform(get("/accounts/10")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void getAll() throws Exception {
        List<Account> accountList = Collections.singletonList(Account.builder().id(10L).name("test").build());
        when(service.getAllAccounts()).thenReturn(accountList);

        mockMvc.perform(get("/accounts")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void update() throws Exception {
        Resource resource = new ClassPathResource("/fixtures/account.json");

        when(service.getAccountById(10L)).thenReturn(Optional.of(Account.builder().id(10L).name("test").build()));

        mockMvc.perform(put("/accounts/10")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(IOUtils.toString(resource.getInputStream(), "UTF-8")
                )).andExpect(status().isOk());
    }

    @Test
    public void deleteById() throws Exception {
        mockMvc.perform(delete("/accounts/10")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNoContent());
    }
}
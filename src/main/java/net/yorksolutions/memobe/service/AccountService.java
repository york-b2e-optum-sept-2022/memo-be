package net.yorksolutions.memobe.service;

import net.yorksolutions.memobe.dto.NewAccountRequestDTO;
import net.yorksolutions.memobe.entity.Account;
import net.yorksolutions.memobe.repository.AccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


@Service
public class AccountService {

    AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account create(NewAccountRequestDTO requestDTO) {
        try {
            return this.accountRepository.save(
                    new Account(requestDTO.username, requestDTO.password)
            );
        } catch (RuntimeException exception) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    public Account login(String username, String password) {
        Optional<Account> accountOpt = this.accountRepository.findByUsernameAndPassword(username, password);
        if (accountOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return accountOpt.get();
    }
}

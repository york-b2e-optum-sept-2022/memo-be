package net.yorksolutions.memobe.service;

import net.yorksolutions.memobe.dto.NewMemoRequestDTO;
import net.yorksolutions.memobe.dto.UpdateMemoRequestDTO;
import net.yorksolutions.memobe.entity.Account;
import net.yorksolutions.memobe.entity.Memo;
import net.yorksolutions.memobe.repository.AccountRepository;
import net.yorksolutions.memobe.repository.MemoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class MemoService {

    private MemoRepository memoRepository;
    private AccountRepository accountRepository;

    public MemoService(MemoRepository memoRepository, AccountRepository accountRepository) {
        this.memoRepository = memoRepository;
        this.accountRepository = accountRepository;
    }

    public Memo create(NewMemoRequestDTO requestDTO) {
        Optional<Account> accountOpt = this.accountRepository.findById(requestDTO.ownerId);
        if (accountOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return this.memoRepository.save(
                new Memo(requestDTO.title, requestDTO.body, accountOpt.get())
        );
    }

    public Iterable<Memo> getMemoList(Long accountId) {
        Optional<Account> accountOpt = this.accountRepository.findById(accountId);
        if (accountOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return this.memoRepository.findAllByOwner(accountOpt.get());
    }

    public void delete(Long memoId) {
        this.memoRepository.deleteById(memoId);
    }

    public Memo update(UpdateMemoRequestDTO requestDTO) {
        Optional<Memo> memoOpt = this.memoRepository.findById(requestDTO.id);
        if (memoOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Memo memo = memoOpt.get();
        memo.setTitle(requestDTO.title);
        memo.setBody(requestDTO.body);
        memo.setFinished(requestDTO.finished);

        return this.memoRepository.save(memo);
    }
}

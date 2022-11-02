package net.yorksolutions.memobe.repository;

import net.yorksolutions.memobe.entity.Account;
import net.yorksolutions.memobe.entity.Memo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemoRepository extends CrudRepository<Memo, Long> {
    Iterable<Memo> findAllByOwner(Account owner);
}

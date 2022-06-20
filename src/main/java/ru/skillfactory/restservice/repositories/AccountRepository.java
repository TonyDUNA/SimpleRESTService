package ru.skillfactory.restservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.skillfactory.restservice.models.Account;


@org.springframework.stereotype.Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query("select a.balance from Account a where a.id = :id")
    Integer getBalanceById(@Param("id") int id);

    @Modifying
    @Query(value = "update Account a set a.balance = :balance where a.id = :id")
    void saveUpdatedBalance(@Param("id") int id,
                           @Param("balance") Integer balance);


}



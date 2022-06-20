package ru.skillfactory.restservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skillfactory.restservice.models.Operations;

@Repository
public interface OperationsRepository extends JpaRepository<Operations, Integer> {
//    @Modifying
//    @Query(value = "insert into Operations (accountId, operationType, amount, operationTime) values (?,?,?,?)", nativeQuery = true)
//    void putOperation(@Param("accountId") int accountId,
//                      @Param("operationType") Integer operationType,
//                      @Param("amount") Integer amount,
//                      @Param("operationTime") LocalDateTime operationTime);


}

package ru.skillfactory.restservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.skillfactory.restservice.models.Operations;


import java.time.LocalDate;
import java.util.List;

@org.springframework.stereotype.Repository
public interface OperationsRepository extends JpaRepository<Operations, Integer> {

    @Query("from Operations o where o.account.id=:id and o.operationDate between :dateFrom and :dateTo")
    List<Operations> findOperationsByIdAndDates(@Param("id") int id,
                                        @Param("dateFrom")LocalDate dateFrom,
                                        @Param("dateTo")LocalDate dateTo);

    @Query("from Operations o where o.account.id=:id")
    List<Operations> findOperationsById(@Param("id") int id);

    @Query("from Operations o where o.account.id=:id and o.operationDate >=:dateFrom")
    List<Operations> findOperationsByIdAndDateFrom(@Param("id") int id,
                                                @Param("dateFrom")LocalDate dateFrom);

    @Query("from Operations o where o.account.id=:id and o.operationDate <=:dateTo")
    List<Operations> findOperationsByIdAndDateTo(@Param("id") int id,
                                                @Param("dateTo")LocalDate dateTo);


}




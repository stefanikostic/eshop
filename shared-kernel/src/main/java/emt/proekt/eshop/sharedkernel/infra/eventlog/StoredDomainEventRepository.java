package emt.proekt.eshop.sharedkernel.infra.eventlog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoredDomainEventRepository extends JpaRepository<StoredDomainEvent,Long> {
    @Query("select max(se.id) from StoredDomainEvent se")
    Long findHighestDomainEventId();

    @Query("select se from StoredDomainEvent se where se.id > :low and se.id <= :high order by se.id")
    List<StoredDomainEvent> findEventsBetween(@Param("low") Long low, @Param("high") Long high);
}

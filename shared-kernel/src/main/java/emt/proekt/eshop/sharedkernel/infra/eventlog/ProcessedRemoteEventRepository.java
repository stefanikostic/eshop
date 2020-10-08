package emt.proekt.eshop.sharedkernel.infra.eventlog;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessedRemoteEventRepository extends JpaRepository<ProcessedRemoteEvent,String> {
}

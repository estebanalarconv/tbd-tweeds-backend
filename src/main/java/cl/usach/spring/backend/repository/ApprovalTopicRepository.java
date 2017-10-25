package cl.usach.spring.backend.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import cl.usach.spring.backend.entities.ApprovalTopic;

public interface ApprovalTopicRepository extends PagingAndSortingRepository<ApprovalTopic, Integer>{

}

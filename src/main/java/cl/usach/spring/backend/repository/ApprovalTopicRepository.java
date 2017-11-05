package cl.usach.spring.backend.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import cl.usach.spring.backend.entities.ApprovalTopic;

public interface ApprovalTopicRepository extends PagingAndSortingRepository<ApprovalTopic, Integer>{

	//@Query("select a from approval_topic where a.topic_id = ?1")
   //ApprovalTopic findByTopicId(int id);
}

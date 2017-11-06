package cl.usach.spring.backend.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import cl.usach.spring.backend.entities.HistoryApprovalTopic;
public interface HistoryApprovalTopicRepository extends PagingAndSortingRepository<HistoryApprovalTopic, Integer>{
	
	
	@Query(value = "SELECT * FROM history_approval_topic h  where h.topic_id = :id", nativeQuery = true) 
    Iterable<HistoryApprovalTopic> findTitleByTopic(@Param("id") int id);
}

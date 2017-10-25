package cl.usach.spring.backend.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import cl.usach.spring.backend.entities.HistoryApprovalTopic;
public interface HistoryApprovalTopicRepository extends PagingAndSortingRepository<HistoryApprovalTopic, Integer>{
}

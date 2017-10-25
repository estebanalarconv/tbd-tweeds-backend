package cl.usach.spring.backend.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import cl.usach.spring.backend.entities.HistoryTweetsTopic;
public interface HistoryTweetsTopicRepository extends PagingAndSortingRepository<HistoryTweetsTopic, Integer>{
	
}

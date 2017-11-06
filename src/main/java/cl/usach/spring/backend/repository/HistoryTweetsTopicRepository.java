package cl.usach.spring.backend.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import cl.usach.spring.backend.entities.HistoryTweetsTopic;
public interface HistoryTweetsTopicRepository extends PagingAndSortingRepository<HistoryTweetsTopic, Integer>{
	
	@Query(value = "SELECT * FROM history_tweets_topic h  where h.topic_id = :id", nativeQuery = true) 
    Iterable<HistoryTweetsTopic> findByTopic(@Param("id") int id);
	
}

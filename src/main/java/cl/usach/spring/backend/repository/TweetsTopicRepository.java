package cl.usach.spring.backend.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import cl.usach.spring.backend.entities.TweetsTopic;

public interface TweetsTopicRepository extends PagingAndSortingRepository<TweetsTopic, Integer> {
	

}
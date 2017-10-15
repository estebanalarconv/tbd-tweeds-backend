package cl.usach.spring.backend.repository;

import cl.usach.spring.backend.entities.Tweet;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TweetRepository extends PagingAndSortingRepository<Tweet, Integer>{
}

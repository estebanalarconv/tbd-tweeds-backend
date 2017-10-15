package cl.usach.spring.backend.repository;

import cl.usach.spring.backend.entities.Topic;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TopicRepository extends PagingAndSortingRepository<Topic, Integer> {
}

package cl.usach.spring.backend.repository;

import cl.usach.spring.backend.entities.StatisticTopic;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StatisticTopicRepository extends PagingAndSortingRepository<StatisticTopic, Integer> {
}

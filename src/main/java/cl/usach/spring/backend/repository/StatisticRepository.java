package cl.usach.spring.backend.repository;

import cl.usach.spring.backend.entities.Statistic;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StatisticRepository extends PagingAndSortingRepository<Statistic, Integer>{
}

package cl.usach.spring.backend.repository;

import cl.usach.spring.backend.entities.Keyword;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface KeywordRepository extends PagingAndSortingRepository<Keyword, Integer> {
}

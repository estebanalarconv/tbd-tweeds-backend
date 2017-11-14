package cl.usach.spring.backend.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import cl.usach.spring.backend.entities.ApprovalTopicByRegion;

public interface ApprovalTopicByRegionRepository extends PagingAndSortingRepository<ApprovalTopicByRegion, Integer>{
	
	@Query(value = "SELECT * FROM approval_topic_by_region h where h.region_id = :id", nativeQuery = true) 
    Iterable<ApprovalTopicByRegion> findByRegion(@Param("id") int id);
}

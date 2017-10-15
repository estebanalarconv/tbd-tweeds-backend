package cl.usach.spring.backend.repository;

import cl.usach.spring.backend.entities.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Integer>{
}

package parkyj3213.photograminsta.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{
	// JPA query method
	User findByUsername(String username);
}

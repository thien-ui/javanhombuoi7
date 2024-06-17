package doanjava.webbannuochoa.Repository;


import doanjava.webbannuochoa.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}


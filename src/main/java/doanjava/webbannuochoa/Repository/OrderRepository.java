package doanjava.webbannuochoa.Repository;

import doanjava.webbannuochoa.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

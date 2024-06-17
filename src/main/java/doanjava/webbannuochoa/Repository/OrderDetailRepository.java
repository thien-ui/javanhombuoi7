package doanjava.webbannuochoa.Repository;

import doanjava.webbannuochoa.models.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
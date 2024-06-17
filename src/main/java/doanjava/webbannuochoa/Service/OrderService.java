package doanjava.webbannuochoa.Service;

import doanjava.webbannuochoa.Repository.OrderDetailRepository;
import doanjava.webbannuochoa.Repository.OrderRepository;
import doanjava.webbannuochoa.models.CartItem;
import doanjava.webbannuochoa.models.Order;
import doanjava.webbannuochoa.models.OrderDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final CartService cartService; // Assuming you have a CartService

    @Transactional
    public Order createOrder(String customerName, String phone, String address, String note, List<CartItem> cartItems) {
        Order order = new Order();
        order.setCustomerName(customerName);
        order.setPhone(phone); // Bổ sung số điện thoại
        order.setAddress(address); // Bổ sung địa chỉ
        order.setNote(note); // Bổ sung lưu ý
        order = orderRepository.save(order);

        for (CartItem item : cartItems) {
            OrderDetail detail = new OrderDetail();
            detail.setOrder(order);
            detail.setProduct(item.getProduct());
            detail.setQuantity(item.getQuantity());
            orderDetailRepository.save(detail);
        }

        // Xóa giỏ hàng sau khi đặt hàng
        cartService.clearCart();

        return order;
    }
}
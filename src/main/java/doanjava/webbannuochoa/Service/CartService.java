package doanjava.webbannuochoa.Service;

import doanjava.webbannuochoa.Repository.ProductRepository;
import doanjava.webbannuochoa.models.CartItem;
import doanjava.webbannuochoa.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@SessionScope
public class CartService {
    private List<CartItem> cartItems = new ArrayList<>();
    @Autowired
    private ProductRepository productRepository;

    public void addToCart(Product product, int quantity) {
        // Kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng hay chưa
        Optional<CartItem> existingItemOptional = cartItems.stream()
                .filter(item -> item.getProduct().getId().equals(product.getId()))
                .findFirst();

        if (existingItemOptional.isPresent()) {
            // Nếu sản phẩm đã tồn tại trong giỏ hàng, tăng số lượng lên
            CartItem existingItem = existingItemOptional.get();
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            // Nếu sản phẩm chưa tồn tại trong giỏ hàng, thêm sản phẩm mới vào giỏ hàng
            cartItems.add(new CartItem(product, quantity));
        }
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void removeFromCart(Long productId) {
        cartItems.removeIf(item -> item.getProduct().getId().equals(productId));
    }

    public void clearCart() {
        cartItems.clear();
    }
}

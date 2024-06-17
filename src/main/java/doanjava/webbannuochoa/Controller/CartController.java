package doanjava.webbannuochoa.Controller;

import doanjava.webbannuochoa.Service.CartService;
import doanjava.webbannuochoa.Service.ProductService;
import doanjava.webbannuochoa.models.CartItem;
import doanjava.webbannuochoa.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService; // Đảm bảo bạn đã inject ProductService

    @GetMapping
    public String showCart(Model model) {
        model.addAttribute("cartItems", cartService.getCartItems());
        return "/cart/cart";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam Long productId, @RequestParam int quantity) {
        // Kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng hay chưa
        Optional<CartItem> existingItemOptional = cartService.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (existingItemOptional.isPresent()) {
            // Nếu sản phẩm đã tồn tại trong giỏ hàng, tăng số lượng lên
            CartItem existingItem = existingItemOptional.get();
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            // Nếu sản phẩm chưa tồn tại trong giỏ hàng, thêm sản phẩm mới vào giỏ hàng
            Product product = productService.getProductById(productId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + productId));
            cartService.addToCart(product, quantity);
        }

        return "redirect:/cart";
    }

    @GetMapping("/remove/{productId}")
    public String removeFromCart(@PathVariable Long productId) {
        cartService.removeFromCart(productId);
        return "redirect:/cart";
    }

    @GetMapping("/clear")
    public String clearCart() {
        cartService.clearCart();
        return "redirect:/cart";
    }
}

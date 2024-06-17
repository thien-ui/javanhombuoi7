package doanjava.webbannuochoa.Controller;


import doanjava.webbannuochoa.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
@Autowired
private ProductService productService;
    @GetMapping("/product")
    public String home(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products/products-list"; // Tên đường dẫn của tệp index.html trong thư mục templates/home
    }
}
package doanjava.webbannuochoa.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private String description;
    @Column(length = 10000)
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {this.imageUrl = imageUrl;}

    public Product(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
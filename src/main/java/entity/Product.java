package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    public Product(String productName, Department department,
                   String imagePath, double price, int amount) {
        this.productName = productName;
        this.department = department;
        this.imagePath = imagePath;
        this.price = price;
        this.amount = amount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @Column(name = "product_name")
    private String productName;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "price")
    private double price;

    @Column(name = "amount")
    private int amount;
}
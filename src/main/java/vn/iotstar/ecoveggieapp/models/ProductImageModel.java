package vn.iotstar.ecoveggieapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "product_images")
public class ProductImageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int product_image_id;

    @Column(nullable = false, length = 255)
    private String product_image;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "product_id", nullable = false)
    private ProductModel product;

    // Constructors
    public ProductImageModel() {
    }

    public ProductImageModel(String product_image, ProductModel product) {
        this.product_image = product_image;
        this.product = product;
    }

    // Getters and Setters
    public int getProduct_image_id() {
        return product_image_id;
    }

    public void setProduct_image_id(int product_image_id) {
        this.product_image_id = product_image_id;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }
}

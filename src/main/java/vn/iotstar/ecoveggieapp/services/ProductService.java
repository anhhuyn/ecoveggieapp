package vn.iotstar.ecoveggieapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.iotstar.ecoveggieapp.models.ProductModel;
import vn.iotstar.ecoveggieapp.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    // Lấy tất cả sản phẩm
    public List<ProductModel> getAllProducts() {
        return productRepository.getAllProducts();
    }

    // Lấy sản phẩm mới nhất
    public List<ProductModel> getNewestProducts() {
        return productRepository.getNewestProducts();
    }

    // Lấy sản phẩm theo giá tăng dần
    public List<ProductModel> getProductsByPriceAsc() {
        return productRepository.getProductsByPriceAsc();
    }

    // Lấy sản phẩm theo giá giảm dần
    public List<ProductModel> getProductsByPriceDesc() {
        return productRepository.getProductsByPriceDesc();
    }

    // Lấy sản phẩm theo khoảng giá
    public List<ProductModel> getProductsByPriceRange(double minPrice, double maxPrice) {
        return productRepository.getProductsByPriceRange(minPrice, maxPrice);
    }
}

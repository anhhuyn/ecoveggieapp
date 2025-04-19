package vn.iotstar.ecoveggieapp.services;

import java.util.List;
import java.util.Optional;

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
    
 // Lấy sản phẩm theo lượt bán giảm dần
    public List<ProductModel> getProductsBySoldQuantityDesc() {
        return productRepository.getProductsBySoldQuantityDesc();
    }
    
    // Tìm kiếm sản phẩm theo tên
    public List<ProductModel> searchProductsByName(String productName) {
        return productRepository.searchProductsByName(productName);
    }
    
    //Lọc theo danh mục
    public List<ProductModel> getProductsByCategoryId(int categoryId) {
        return productRepository.getProductsByCategoryId(categoryId);
    }

    // Lấy thông tin 1 sản phẩm theo id
    public Optional<ProductModel> getProductById(int productId) {
        return productRepository.getProductById(productId);
    }



}

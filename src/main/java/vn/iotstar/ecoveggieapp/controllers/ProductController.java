package vn.iotstar.ecoveggieapp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import vn.iotstar.ecoveggieapp.models.ProductModel;
import vn.iotstar.ecoveggieapp.services.ProductService;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    ProductService productService;

    // Lấy tất cả sản phẩm
    @GetMapping("/all")
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        List<ProductModel> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Lấy sản phẩm mới nhất
    @GetMapping("/newest")
    public ResponseEntity<List<ProductModel>> getNewestProducts() {
        List<ProductModel> products = productService.getNewestProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Lấy sản phẩm theo giá từ thấp đến cao
    @GetMapping("/price/asc")
    public ResponseEntity<List<ProductModel>> getProductsByPriceAsc() {
        List<ProductModel> products = productService.getProductsByPriceAsc();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Lấy sản phẩm theo giá từ cao đến thấp
    @GetMapping("/price/desc")
    public ResponseEntity<List<ProductModel>> getProductsByPriceDesc() {
        List<ProductModel> products = productService.getProductsByPriceDesc();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Lấy sản phẩm theo khoảng giá
    @GetMapping("/price/range")
    public ResponseEntity<List<ProductModel>> getProductsByPriceRange(
            @RequestParam double minPrice, @RequestParam double maxPrice) {
        List<ProductModel> products = productService.getProductsByPriceRange(minPrice, maxPrice);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    
 // Lấy sản phẩm theo lượt bán giảm dần
    @GetMapping("/sold/desc")
    public ResponseEntity<List<ProductModel>> getProductsBySoldQuantityDesc() {
        List<ProductModel> products = productService.getProductsBySoldQuantityDesc();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    
    // Tìm kiếm sản phẩm theo tên
    @GetMapping("/search")
    public ResponseEntity<List<ProductModel>> searchProductsByName(@RequestParam String name) {
        List<ProductModel> products = productService.searchProductsByName(name);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    
    //Lọc theo danh mục
    @GetMapping("/category/{id}")
    public ResponseEntity<List<ProductModel>> getProductsByCategoryId(@PathVariable("id") int categoryId) {
        List<ProductModel> products = productService.getProductsByCategoryId(categoryId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    
 // Lấy chi tiết sản phẩm theo ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductModel> getProductById(@PathVariable("id") int productId) {
        Optional<ProductModel> product = productService.getProductById(productId);
        if (!product.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }


    @PutMapping("/{id}/update-quantity")
    public ResponseEntity<String> updateSoldAndStockQuantity(
            @PathVariable("id") int productId,
            @RequestParam("quantity") int quantity) {

        try {
            productService.updateSoldAndStockQuantity(productId, quantity);
            return new ResponseEntity<>("Cập nhật số lượng thành công", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cập nhật thất bại: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}


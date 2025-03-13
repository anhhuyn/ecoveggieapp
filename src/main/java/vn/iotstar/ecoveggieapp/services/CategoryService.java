package vn.iotstar.ecoveggieapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.iotstar.ecoveggieapp.models.CategoryModel;
import vn.iotstar.ecoveggieapp.repository.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	// Lấy tất cả danh mục
	public List<CategoryModel> getAllCategories() {
		return categoryRepository.getAllCategories();
	}

	// // Lấy danh mục kèm số lượng sản phẩm
	public List<CategoryModel> getAllCategoriesWithProductCount() {
		List<Object[]> results = categoryRepository.getAllCategoriesWithProductCount();

		return results.stream().map(obj -> {
			CategoryModel category = new CategoryModel();
			category.setCategory_id((Integer) obj[0]);
			category.setCategory_name((String) obj[1]);
			category.setThumbnail((String) obj[2]);
			category.setProductCount(((Number) obj[3]).intValue()); // Số lượng sản phẩm
			return category;
		}).collect(Collectors.toList());
	}

	// Lấy danh mục theo tên kèm số lượng sản phẩm
	public List<CategoryModel> getCategoriesByName(String categoryName) {
		List<Object[]> results = categoryRepository.getCategoriesByNameWithProductCount(categoryName);

		return results.stream().map(obj -> {
			CategoryModel category = new CategoryModel();
			category.setCategory_id((Integer) obj[0]);
			category.setCategory_name((String) obj[1]);
			category.setThumbnail((String) obj[2]);
			category.setProductCount(((Number) obj[3]).intValue()); // Số lượng sản phẩm
			return category;
		}).collect(Collectors.toList());
	}

	// Đếm số lượng sản phẩm trong mỗi danh mục
	public int countProductsByCategory(int categoryId) {
		return categoryRepository.countProductsByCategory(categoryId);
	}

}

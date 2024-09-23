package com.revature.revshop.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.revature.revshop.dto.CategoryDto;
import com.revature.revshop.dto.ProductRequest;
import com.revature.revshop.dto.ProductResponse;
import com.revature.revshop.model.Category;
import com.revature.revshop.model.Product;
import com.revature.revshop.repository.CategoryRepository;
import com.revature.revshop.repository.ProductRepository;

@Service
public class ProductService {
	
	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;

	@Autowired
	public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}
	
	// Mapping ProductRequest to Product entity
	public Product mapToProduct(ProductRequest productRequest) {
		Category category = categoryRepository.findById(productRequest.getCategoryId())
		                     .orElseThrow(() -> new RuntimeException("Category not found"));
		
		return Product.builder()
				.name(productRequest.getName())
				.description(productRequest.getDescription())
				.skuCode(productRequest.getSkuCode())
				.price(productRequest.getPrice())
				.imageurl(productRequest.getImageurl())  // Added the imageurl
				.category(category)
				.build();								
	}
	
	// Mapping Product entity to ProductResponse DTO
	public ProductResponse mapToProductResponse(Product product) {
		Category category = product.getCategory();
		CategoryDto categoryDto = CategoryDto.builder()
				.id(category.getId())
				.name(category.getName())
				.build();
		
		return ProductResponse.builder()
				.id(product.getId())
				.name(product.getName())
				.description(product.getDescription())
				.skuCode(product.getSkuCode())
				.price(product.getPrice())
				.imageurl(product.getImageurl())  // Added the imageurl
				.category(category)  // Passing CategoryDto here
				.build();
	}

	// Create Product
	public ProductResponse createProduct(ProductRequest productRequest) {
		Product product = mapToProduct(productRequest);
		product = productRepository.save(product);
		return mapToProductResponse(product);
	}
	
	// Get Product by Id
	public ProductResponse getProductById(Long id) {
		Product product = productRepository.findById(id)
		                      .orElseThrow(() -> new RuntimeException("Product not found"));
		return mapToProductResponse(product);		
	}
	
	// Update Product by Id
	public ProductResponse updateProductById(ProductRequest productRequest, Long id) {
		Product product = mapToProduct(productRequest);
		product.setId(id);
		product = productRepository.save(product);
		return mapToProductResponse(product);
	}
	
	// Delete Product by Id
	public boolean deleteProductById(Long id) {
		try {
			productRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	// Get all products
	public List<ProductResponse> getAllProducts() {
		List<Product> products = productRepository.findAll();
		return products.stream().map(this::mapToProductResponse).toList();
	}
}

//package com.revature.revshop;
//
//import com.revature.revshop.dto.CategoryDto;
//import com.revature.revshop.dto.ProductRequest;
//import com.revature.revshop.dto.ProductResponse;
//import com.revature.revshop.model.Category;
//import com.revature.revshop.model.Product;
//import com.revature.revshop.repository.CategoryRepository;
//import com.revature.revshop.repository.ProductRepository;
//import com.revature.revshop.service.ProductService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//class ProductServiceApplicationTests {
//
//    @Mock
//    private ProductRepository productRepository;
//
//    @Mock
//    private CategoryRepository categoryRepository;
//
//    @InjectMocks
//    private ProductService productService;
//
//    private ProductRequest productRequest;
//    private Product product;
//    private Category category;
//
//    @BeforeEach
//    void setUp() {
//        category = Category.builder()
//                .id(1L)
//                .name("Electronics")
//                .logo("electronics.png")
//                .build();
//
//        productRequest = ProductRequest.builder()
//                .name("Laptop")
//                .description("A powerful laptop")
//                .skuCode("LAP123")
//                .price(999.99)
//                .categoryId(1L)
//                .build();
//
//        product = Product.builder()
//                .id(1L)
//                .name("Laptop")
//                .description("A powerful laptop")
//                .skuCode("LAP123")
//                .price(999.99)
//                .category(category)
//                .build();
//    }
//
//    @Test
//    void createProduct_ShouldReturnProductResponse() {
//        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
//        when(productRepository.save(any(Product.class))).thenReturn(product);
//
//        ProductResponse response = productService.createProduct(productRequest);
//
//        assertNotNull(response);
//        assertEquals("Laptop", response.getName());
//        assertEquals("Electronics", response.getCategoryDto().getName());
//    }
//
//    @Test
//    void getProductById_ShouldReturnProductResponse() {
//        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
//
//        ProductResponse response = productService.getProductById(1L);
//
//        assertNotNull(response);
//        assertEquals("Laptop", response.getName());
//        assertEquals("Electronics", response.getCategoryDto().getName());
//    }
//
//    @Test
//    void updateProductById_ShouldReturnUpdatedProductResponse() {
//        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
//        when(productRepository.save(any(Product.class))).thenReturn(product);
//
//        ProductResponse response = productService.updateProductById(productRequest, 1L);
//
//        assertNotNull(response);
//        assertEquals("Laptop", response.getName());
//        assertEquals("Electronics", response.getCategoryDto().getName());
//    }
//
//    @Test
//    void deleteProductById_ShouldReturnTrue() {
//        doNothing().when(productRepository).deleteById(1L);
//
//        boolean result = productService.deleteProductById(1L);
//
//        assertTrue(result);
//    }
//
//    @Test
//    void getAllProducts_ShouldReturnListOfProductResponses() {
//        when(productRepository.findAll()).thenReturn(Arrays.asList(product));
//
//        List<ProductResponse> responses = productService.getAllProducts();
//
//        assertNotNull(responses);
//        assertEquals(1, responses.size());
//        assertEquals("Laptop", responses.get(0).getName());
//    }
//
//    @Test
//    void contextLoads() {
//        // This test ensures that the Spring context loads correctly
//    }
//}
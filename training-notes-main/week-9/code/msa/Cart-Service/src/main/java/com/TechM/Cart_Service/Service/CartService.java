package com.TechM.Cart_Service.Service;

import com.TechM.Cart_Service.Entity.Cart;
import com.TechM.Cart_Service.Entity.CartItem;
import com.TechM.Cart_Service.Repository.CartRepository;
import com.TechM.Cart_Service.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    

    @Autowired
    private RestTemplate restTemplate;
    
    private static final String PRODUCT_SERVICE_URL = "http://localhost:8081/api/product?id=";

    // Get the cart for a specific user
    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    // Add an item to the cart
    public Cart addItemToCart(Long userId, Long productId, int quantity) {
        // Fetch product details from Product Service
        ProductResponse product = getProductById(productId);

        if (product == null) {
            throw new RuntimeException("Product not found");
        }

        // Retrieve the cart for the user
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            cart = new Cart();
            cart.setUserId(userId);
        }

        // Add product to cart
        CartItem item = new CartItem();
        item.setProductId(productId);
        item.setProductName(product.getName());
        item.setPrice(product.getPrice());
        item.setQuantity(quantity);

        cart.getCartItems().add(item);
        return cartRepository.save(cart);
    }

    // Fetch product details from Product Service using RestTemplate
    private ProductResponse getProductById(Long productId) {
        return restTemplate.getForObject(PRODUCT_SERVICE_URL + productId, ProductResponse.class);
    }


    // Update item quantity in the cart
    public Cart updateCartItem(Long userId, Long productId, int quantity) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) return null;

        for (CartItem item : cart.getCartItems()) {
            if (item.getProductId().equals(productId)) {
                item.setQuantity(quantity);
                break;
            }
        }

        return cartRepository.save(cart);
    }

    // Remove an item from the cart
    public Cart removeItemFromCart(Long userId, Long productId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) return null;

        cart.getCartItems().removeIf(item -> item.getProductId().equals(productId));

        return cartRepository.save(cart);
    }

    // Clear the cart
    public void clearCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart != null) {
            cart.getCartItems().clear();
            cartRepository.save(cart);
        }
    }
}

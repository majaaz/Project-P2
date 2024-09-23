package com.TechM.Cart_Service.Repository;

import com.TechM.Cart_Service.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{
    Cart findByUserId(Long userId);
}

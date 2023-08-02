package com.r2s.demo.repository;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.r2s.demo.model.CartlineItem;

@Repository
public interface CartLineItemRespository extends JpaRepository<CartlineItem, Long>{
}

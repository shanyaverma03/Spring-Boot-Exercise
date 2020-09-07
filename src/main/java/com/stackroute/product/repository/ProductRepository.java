package com.stackroute.product.repository;

import com.stackroute.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

   List<Product> findAllByCategory(String category);

}

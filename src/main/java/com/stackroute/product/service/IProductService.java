package com.stackroute.product.service;

import com.stackroute.product.domain.Product;
import com.stackroute.product.exception.CategoryDoesNotExistException;
import com.stackroute.product.exception.ProductAlreadyExistsException;
import com.stackroute.product.exception.ProductDoesNotExistException;

import java.util.List;

public interface IProductService {

    public Product saveProduct(Product product) throws ProductAlreadyExistsException;

    public List<Product> getAllProducts() ;

    public Product getProduct(int prodId) throws ProductDoesNotExistException;

    public List<Product> getProductsByCategory(String category) throws CategoryDoesNotExistException;

    public void deleteProduct(int prodId) throws  ProductDoesNotExistException;

    public Product updateproduct(Product product) throws ProductDoesNotExistException;
}

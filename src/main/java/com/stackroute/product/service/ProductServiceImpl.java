package com.stackroute.product.service;

import com.stackroute.product.domain.Product;
import com.stackroute.product.exception.CategoryDoesNotExistException;
import com.stackroute.product.exception.ProductAlreadyExistsException;
import com.stackroute.product.exception.ProductDoesNotExistException;
import com.stackroute.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product saveProduct(Product product) throws ProductAlreadyExistsException {

        Optional<Product> optional =  productRepository.findById(product.getProdId());
        if(optional.isPresent())
        {
            throw new ProductAlreadyExistsException("Product Alreday Exists");
        }
        Product createdProduct=  productRepository.save(product);
        return createdProduct;

    }

    @Override
    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    @Override
    public Product getProduct(int prodId) throws ProductDoesNotExistException {

        Optional<Product> optional= productRepository.findById(prodId);
        if(!optional.isPresent()){
            throw new ProductDoesNotExistException("Product does not exist");
        }


        return optional.get();
    }

    @Override
    public List<Product> getProductsByCategory(String category) throws CategoryDoesNotExistException {
        List<Product> ans= productRepository.findAllByCategory(category);
        return ans;

    }

    @Override
    public void deleteProduct(int prodId) throws ProductDoesNotExistException {

        Optional<Product> optional= productRepository.findById(prodId);
        if(!optional.isPresent()){
            throw new ProductDoesNotExistException("Product does not exist");
        }

        productRepository.deleteById(prodId);


    }

    @Override
    //The id of the product can't be updated. Update only the name or the category
    public Product updateproduct(Product product) throws ProductDoesNotExistException {

        Optional<Product> optional= productRepository.findById(product.getProdId());
        if(!optional.isPresent()){
            throw new ProductDoesNotExistException("Product does not exist");
        }


        product.setProdId(product.getProdId());
        product.setProdName(product.getProdName());
        product.setCategory(product.getCategory());
       return productRepository.save(product);
    }
}

package com.stackroute.product.controller;

import com.stackroute.product.domain.Product;
import com.stackroute.product.exception.CategoryDoesNotExistException;
import com.stackroute.product.exception.ProductAlreadyExistsException;
import com.stackroute.product.exception.ProductDoesNotExistException;
import com.stackroute.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/productservice")
public class ProductController {

    private IProductService productService;
    private ResponseEntity responseEntity;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/product")
    public ResponseEntity<?> saveEmployee(@RequestBody Product product){
        try {
            Product createdProduct =  productService.saveProduct(product);
            responseEntity = new ResponseEntity(createdProduct , HttpStatus.CREATED);
        }catch (ProductAlreadyExistsException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }catch (Exception e)
        {
            System.out.println(e);
            responseEntity = new ResponseEntity("Some Internal Error Try after sometime" , HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @GetMapping("/products")
    public ResponseEntity getAllProducts()
    {
        try{


            List<Product> productList =  productService.getAllProducts();
            responseEntity = new ResponseEntity(productList , HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            responseEntity = new ResponseEntity("Some Internal Error Try after sometime" , HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;

    }

    @GetMapping("/products/{prodId}")
    public ResponseEntity getproduct(@PathVariable("prodId") int prodId){

        try{

            Product product= productService.getProduct(prodId);
            responseEntity = new ResponseEntity(product , HttpStatus.OK);
        }catch (ProductDoesNotExistException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
        catch (Exception e){
            System.out.println(e);
            responseEntity = new ResponseEntity("Some Internal Error Try after sometime" , HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return  responseEntity;

    }

    @GetMapping("/products/byCategory/{category}")
    public ResponseEntity byCategory(@PathVariable(value = "category") String category) throws CategoryDoesNotExistException {
        List<Product> products= productService.getProductsByCategory(category);
        responseEntity = new ResponseEntity(products , HttpStatus.OK);
        return  responseEntity;
    }

    @DeleteMapping("/products/{prodId}")
    public void deleteProduct(@PathVariable("prodId") int prodId){
        try {
            productService.deleteProduct(prodId);
            System.out.println("Deleted product with id as" + prodId);
        }
        catch (ProductDoesNotExistException e){
            System.out.println(e);
        }
    }

    @PostMapping("/updateProduct/{prodId}")
    public ResponseEntity updateproduct(@RequestBody Product product, @PathVariable("prodId") int prodId){
        try {
            Product createdProduct =  productService.updateproduct(product);
            responseEntity = new ResponseEntity(createdProduct , HttpStatus.CREATED);
        }catch (ProductDoesNotExistException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }catch (Exception e)
        {
            System.out.println(e);
            responseEntity = new ResponseEntity("Some Internal Error Try after sometime" , HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }
}

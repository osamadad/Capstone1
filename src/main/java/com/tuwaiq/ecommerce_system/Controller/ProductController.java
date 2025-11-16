package com.tuwaiq.ecommerce_system.Controller;

import Api.ApiResponse;
import com.tuwaiq.ecommerce_system.Model.Product;
import com.tuwaiq.ecommerce_system.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody @Valid Product product, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        else {
            productService.addProduct(product);
            return ResponseEntity.status(200).body(new ApiResponse("The product have been added successfully"));
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> getCategories(){
        ArrayList<Product> products = productService.getProducts();
        if (products.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("There are no products to show"));
        }
        else {
            return ResponseEntity.status(200).body(products);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable String id, @RequestBody @Valid Product product, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        if (productService.updateProduct(id,product)){
            return ResponseEntity.status(200).body(new ApiResponse("The product have been updated successfully"));
        }
        else {
            return ResponseEntity.status(400).body(new ApiResponse("There is no product with that id found"));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id){
        if (productService.deleteProduct(id)){
            return ResponseEntity.status(200).body(new ApiResponse("The product have been deleted successfully"));
        }
        else {
            return ResponseEntity.status(400).body(new ApiResponse("There are no product with that id found"));
        }
    }
}

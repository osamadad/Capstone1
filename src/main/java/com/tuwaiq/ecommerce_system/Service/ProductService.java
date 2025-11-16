package com.tuwaiq.ecommerce_system.Service;

import com.tuwaiq.ecommerce_system.Model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {
    
    ArrayList<Product> products = new ArrayList<>();

    public void addProduct(Product product){
        products.add(product);
    }

    public ArrayList<Product> getProducts(){
        return products;
    }

    public Boolean updateProduct(String id,Product product){
        for (int i = 0; i< products.size(); i++) {
            if (products.get(i).getId().equalsIgnoreCase(id)){
                products.set(i,product);
                return true;
            }
        }
        return false;
    }

    public Boolean deleteProduct(String id){
        for (Product product: products){
            if (product.getId().equalsIgnoreCase(id)){
                products.remove(product);
                return true;
            }
        }
        return false;
    }
}

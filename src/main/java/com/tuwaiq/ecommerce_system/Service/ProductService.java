package com.tuwaiq.ecommerce_system.Service;

import com.tuwaiq.ecommerce_system.Model.Category;
import com.tuwaiq.ecommerce_system.Model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ProductService {
    
    ArrayList<Product> products = new ArrayList<>();
    private final CategoryService categoryService;

    public boolean addProduct(Product product){
        for (Category category:categoryService.categories){
            if (category.getId().equalsIgnoreCase(product.getCategoryId())){
                products.add(product);
                return true;
            }
        }
        return false;
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

    public ArrayList<Product> getProductsByCategorySortedByPrice(String category){
        String categoryId="";
        ArrayList<Product> sortedProductByPrice=new ArrayList<>();
        for (Category category1: categoryService.categories){
            if (category1.getName().equalsIgnoreCase(category)){
                categoryId=category1.getId();
            }
        }
        for (Product product:products){
            if (product.getCategoryId().equalsIgnoreCase(categoryId)){
                sortedProductByPrice.add(product);
            }
        }
        sortedProductByPrice.sort((product1, product2) -> Double.compare(product1.getPrice(), product2.getPrice()));
        return sortedProductByPrice;
    }
}

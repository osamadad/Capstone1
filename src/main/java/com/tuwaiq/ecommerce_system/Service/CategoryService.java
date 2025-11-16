package com.tuwaiq.ecommerce_system.Service;

import com.tuwaiq.ecommerce_system.Model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryService {

    ArrayList<Category> categories = new ArrayList<>();

    public void addCategory(Category category){
        categories.add(category);
    }

    public ArrayList<Category> getCategories(){
        return categories;
    }

    public Boolean updateCategory(String id,Category category){
        for (int i = 0; i< categories.size()-1; i++) {
            if (categories.get(i).getId().equalsIgnoreCase(id)){
                categories.set(i,category);
                return true;
            }
        }
        return false;
    }

    public Boolean deleteCategory(String id){
        for (Category category: categories){
            if (category.getId().equalsIgnoreCase(id)){
                categories.remove(category);
                return true;
            }
        }
        return false;
    }
}

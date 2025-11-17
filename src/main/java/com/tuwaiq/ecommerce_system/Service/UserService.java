package com.tuwaiq.ecommerce_system.Service;

import com.tuwaiq.ecommerce_system.Model.MerchantStock;
import com.tuwaiq.ecommerce_system.Model.Product;
import com.tuwaiq.ecommerce_system.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {

    ArrayList<User> users = new ArrayList<>();
    private final MerchantStockService merchantStockService;
    public final ProductService productService;

    public void addUser(User user) {
        users.add(user);
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public Boolean updateUser(String id, User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equalsIgnoreCase(id)) {
                users.set(i, user);
                return true;
            }
        }
        return false;
    }

    public Boolean deleteUser(String id) {
        for (User user : users) {
            if (user.getId().equalsIgnoreCase(id)) {
                users.remove(user);
                return true;
            }
        }
        return false;
    }

    public int buyProduct(String userId, String merchantId, String productId) {
        int errorType=1;
        double productPrice = 0;
        for (Product product : productService.getProducts()) {
            if (product.getId().equalsIgnoreCase(productId)) {
                productPrice = product.getPrice();
            }else {
                errorType=3;
            }
        }
        for (User user : users) {
            if (user.getId().equalsIgnoreCase(userId)) {
                for (MerchantStock merchantStock : merchantStockService.getMerchantStocks()) {
                    if (merchantStock.getMerchantId().equalsIgnoreCase(merchantId)) {
                        if (merchantStock.getProductId().equalsIgnoreCase(productId)) {
                            if (merchantStock.getStock() > 0) {
                                if (user.getBalance() >= productPrice) {
                                    merchantStock.setStock(merchantStock.getStock()-1);
                                    user.setBalance(user.getBalance()-productPrice);
                                    return 0;
                                }else{
                                    errorType=5;
                                }
                            }else {
                                errorType=4;
                            }
                        }else {
                            errorType=3;
                        }
                    }else {
                        errorType=2;
                    }
                }
                errorType=1;
            }
        }
        return errorType;
    }

}

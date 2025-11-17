package com.tuwaiq.ecommerce_system.Service;

import com.tuwaiq.ecommerce_system.Model.Merchant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class MerchantService {

    ArrayList<Merchant> merchants= new ArrayList<>();
    HashMap<String,Double> merchantDiscountedProduct=new HashMap<>();

    public void addMerchant(Merchant merchant){
        merchants.add(merchant);
    }

    public ArrayList<Merchant> getMerchants(){
        return merchants;
    }

    public boolean updateMerchant(String id, Merchant merchant){
        for (int i=0;i<merchants.size();i++){
            if (merchants.get(i).getId().equalsIgnoreCase(id)){
                merchants.set(i,merchant);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMerchant(String id){
        for (Merchant merchant:merchants){
            if (merchant.getId().equalsIgnoreCase(id)){
                merchants.remove(merchant);
                return true;
            }
        }
        return false;
    }

    public void discountProduct(String productId, double discount){
        merchantDiscountedProduct.put(productId,discount);
    }

}


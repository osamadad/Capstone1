package com.tuwaiq.ecommerce_system.Service;

import com.tuwaiq.ecommerce_system.Model.MerchantStock;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@Service
public class MerchantStockService {

    ArrayList<MerchantStock> merchantStocks = new ArrayList<>();

    public void addMerchantStock(MerchantStock merchantStock){
        merchantStocks.add(merchantStock);
    }

    public ArrayList<MerchantStock> getMerchantStocks(){
        return merchantStocks;
    }

    public Boolean updateMerchantStock(String id,MerchantStock merchantStock){
        for (int i = 0; i< merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId().equalsIgnoreCase(id)){
                merchantStocks.set(i,merchantStock);
                return true;
            }
        }
        return false;
    }

    public Boolean deleteMerchantStock(String id){
        for (MerchantStock merchantStock: merchantStocks){
            if (merchantStock.getId().equalsIgnoreCase(id)){
                merchantStocks.remove(merchantStock);
                return true;
            }
        }
        return false;
    }

    public int increaseProductStock(String merchantId, String productId, int newStock){
        int errorType=1;
        for (MerchantStock merchantStock: merchantStocks){
            if (merchantStock.getMerchantId().equalsIgnoreCase(merchantId)){
                if (merchantStock.getProductId().equalsIgnoreCase(productId)){
                    merchantStock.setStock(merchantStock.getStock()+newStock);
                    return 0;
                }else {
                    errorType=1;
                }
            }else {
                errorType=2;
            }
        }
        return errorType;
    }
}

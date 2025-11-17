package com.tuwaiq.ecommerce_system.Service;

import com.tuwaiq.ecommerce_system.Model.Merchant;
import com.tuwaiq.ecommerce_system.Model.MerchantStock;
import com.tuwaiq.ecommerce_system.Model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MerchantStockService {

    ArrayList<MerchantStock> merchantStocks = new ArrayList<>();
    private final ProductService productService;
    private final MerchantService merchantService;

    public String addMerchantStock(MerchantStock merchantStock) {
        String value = merchantProductValidation(merchantStock.getMerchantId(), merchantStock.getProductId());
        if (value.equalsIgnoreCase("ok")) {
            merchantStocks.add(merchantStock);
        }
        return value;
    }

    public ArrayList<MerchantStock> getMerchantStocks() {
        return merchantStocks;
    }

    public Boolean updateMerchantStock(String id, MerchantStock merchantStock) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId().equalsIgnoreCase(id)) {
                merchantStocks.set(i, merchantStock);
                return true;
            }
        }
        return false;
    }

    public Boolean deleteMerchantStock(String id) {
        for (MerchantStock merchantStock : merchantStocks) {
            if (merchantStock.getId().equalsIgnoreCase(id)) {
                merchantStocks.remove(merchantStock);
                return true;
            }
        }
        return false;
    }

    public String merchantProductValidation(String merchantId, String productId) {
        for (Merchant merchant: merchantService.merchants){
            if (!merchant.getId().equalsIgnoreCase(merchantId)){
                return "merchant id error";
            }
        }
        for (Product product: productService.products){
            if (!product.getId().equalsIgnoreCase(productId)){
                return "product id error";
            }
        }
        return "ok";
    }

    public String increaseProductStock(String merchantId, String productId, int newStock) {
        String errorType = "General error";
        for (MerchantStock merchantStock : merchantStocks) {
            if (merchantStock.getMerchantId().equalsIgnoreCase(merchantId)) {
                if (merchantStock.getProductId().equalsIgnoreCase(productId)) {
                    merchantStock.setStock(merchantStock.getStock() + newStock);
                    return "ok";
                } else {
                    errorType = "product id error";
                }
            } else {
                errorType = "merchant id error";
            }
        }
        return errorType;
    }
}

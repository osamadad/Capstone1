package com.tuwaiq.ecommerce_system.Controller;

import Api.ApiResponse;
import com.tuwaiq.ecommerce_system.Model.MerchantStock;
import com.tuwaiq.ecommerce_system.Service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/merchant-stock")
@RequiredArgsConstructor
public class MerchantStockController {

    private final MerchantStockService merchantStockService;

    @PostMapping("/add")
    public ResponseEntity<?> addMerchantStock(@RequestBody @Valid MerchantStock merchantStock, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        else {
            merchantStockService.addMerchantStock(merchantStock);
            return ResponseEntity.status(200).body(new ApiResponse("The merchant stock have been added successfully"));
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> getMerchantStocks(){
        ArrayList<MerchantStock> merchantStocks = merchantStockService.getMerchantStocks();
        if (merchantStocks.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("There are no merchant stocks to show"));
        }
        else {
            return ResponseEntity.status(200).body(merchantStocks);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMerchantStock(@PathVariable String id, @RequestBody @Valid MerchantStock merchantStock, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        if (merchantStockService.updateMerchantStock(id,merchantStock)){
            return ResponseEntity.status(200).body(new ApiResponse("The merchant stock have been updated successfully"));
        }
        else {
            return ResponseEntity.status(400).body(new ApiResponse("There is no merchant stock with that id found"));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMerchantStock(@PathVariable String id){
        if (merchantStockService.deleteMerchantStock(id)){
            return ResponseEntity.status(200).body(new ApiResponse("The merchant stock have been deleted successfully"));
        }
        else {
            return ResponseEntity.status(400).body(new ApiResponse("There are no merchant stock with that id found"));
        }
    }

    @PutMapping("/increase-stock/{merchantId}/{productId}/{newStock}")
    public ResponseEntity<?> increaseProductStock(@PathVariable String merchantId, @PathVariable String productId, @PathVariable int newStock){
        if (merchantStockService.increaseProductStock(merchantId,productId,newStock)){
            return ResponseEntity.status(200).body(new ApiResponse("The new stock have been added successfully"));
        }else {
            return ResponseEntity.status(400).body(new ApiResponse("There were no matches for product id or merchant id"));
        }
    }
}

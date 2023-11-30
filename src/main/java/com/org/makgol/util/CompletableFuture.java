package com.org.makgol.util;

import com.org.makgol.stores.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class CompletableFuture {
    static StoreService storeService;

    @Autowired
    public CompletableFuture(StoreService storeService) {
        this.storeService = storeService;
    }

    public static java.util.concurrent.CompletableFuture<String> fetchDataAsync(String email) {
        return java.util.concurrent.CompletableFuture.supplyAsync(() -> {
            // 비동기 작업 수행 후 결과 반환
            try{
                storeService.saveStoresProcess(email);
            } catch (Exception e){
                e.printStackTrace();
            }

            return "Data fetched successfully";
        });
    }
}
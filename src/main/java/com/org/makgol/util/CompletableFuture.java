package com.org.makgol.util;

public class CompletableFuture {
    public static java.util.concurrent.CompletableFuture<String> fetchDataAsync() {
        return java.util.concurrent.CompletableFuture.supplyAsync(() -> {
            // 비동기 작업 수행 후 결과 반환
            try {
                for(int i=0; i < 10; i++) {
                    Thread.sleep(1000); // 예시를 위해 1초 대기
                    System.out.println(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Data fetched successfully";
        });
    }
}
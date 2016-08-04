package com.policy.stock.runner.stockinfo;

import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

public class StockInfoDataStore {
	
	private static LinkedBlockingQueue<Map<String,String>> stockInfos = new LinkedBlockingQueue<Map<String,String>>(500);

	public static LinkedBlockingQueue<Map<String,String>> getStockInfos() {
		return stockInfos;
	}

	public static void setStockInfos(LinkedBlockingQueue<Map<String,String>> stockInfos) {
		StockInfoDataStore.stockInfos = stockInfos;
	}


}

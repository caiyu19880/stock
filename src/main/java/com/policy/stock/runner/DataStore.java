package com.policy.stock.runner;

import java.util.concurrent.LinkedBlockingQueue;

public class DataStore {
	
	private static LinkedBlockingQueue<String> stockInfos = new LinkedBlockingQueue<String>(500);

	public static LinkedBlockingQueue<String> getStockInfos() {
		return stockInfos;
	}

	public static void setStockInfos(LinkedBlockingQueue<String> stockInfos) {
		DataStore.stockInfos = stockInfos;
	}
	

}

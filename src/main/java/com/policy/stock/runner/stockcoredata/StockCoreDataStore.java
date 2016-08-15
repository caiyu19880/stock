package com.policy.stock.runner.stockcoredata;

import java.util.concurrent.LinkedBlockingQueue;

public class StockCoreDataStore {

	private static LinkedBlockingQueue<String> codes = new LinkedBlockingQueue<String>(4000);

	public static LinkedBlockingQueue<String> getCodes() {
		return codes;
	}

	public static void setCodes(LinkedBlockingQueue<String> codes) {
		StockCoreDataStore.codes = codes;
	}

}

package com.policy.stock.runner.stockinfo;

import com.policy.stock.runner.stockcoredata.StockCoreDataStore;

public class StockCodePrintRunner extends Thread {

	@Override
	public void run() {
	    int count = 0;
		while(true){
			String stockCode = "";
			try {
			    stockCode = StockCoreDataStore.getCodes().take();
			    System.out.println("count : " +String.valueOf(count)+ " : "+stockCode);
			    count++;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

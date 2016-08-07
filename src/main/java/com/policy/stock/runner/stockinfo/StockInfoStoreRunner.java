package com.policy.stock.runner.stockinfo;

import java.io.File;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.policy.stock.utils.FileUtil;

public class StockInfoStoreRunner extends Thread {

	@Override
	public void run() {
		while(true){
			String stockCode = "";
			Map<String, String> result = null;
			try {
				result = StockInfoDataStore.getStockInfos().take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stockCode = result.get("stockCode");
			if (null != result && StringUtils.isNotEmpty(result.get("stockCode")) && StringUtils.isNotEmpty(result.get("stockDetails"))) {
				File file = new File("D:\\temp\\" + stockCode + "_" + System.currentTimeMillis() + ".txt");
				try {
					FileUtil.appendFile(file, result.get("stockDetails"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}

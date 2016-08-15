package com.policy.stock;

import com.policy.stock.runner.stockinfo.StockInfoParseRunner;
import com.policy.stock.runner.stockinfo.StockInfoStoreRunner;

public class Main {

	public static void main(String[] args) {
		StockInfoParseRunner runner = new StockInfoParseRunner("sh600571");
		runner.run();
//		StockInfoParseRunner runner1 = new StockInfoParseRunner("sh600662");
//		runner1.run();
//		StockInfoParseRunner runner2 = new StockInfoParseRunner("sh600843");
//		runner2.run();
//		
//		StockInfoParseRunner runner3 = new StockInfoParseRunner("sh600129");
//		runner3.run();
//		
		StockInfoStoreRunner consumer = new StockInfoStoreRunner();
		consumer.start();
		
//		FinaceParseRunner runner = new FinaceParseRunner("000002");
//		runner.run();
//		String hostname = "ftp.gushenbbs.com";
//	    int port = 21;
//	    String username = "gushenbbs170508";
//	    String password = "gushenbbs170508";
//	    String pathname = "/2015年5月至2016年7月数据/201607/20160731/002/"; 
//	    String filename = "002074.csv"; 
//	    //String originfilename = "C:\\Users\\Downloads\\Downloads.rar";
//	    //FavFTPUtil.uploadFileFromProduction(hostname, port, username, password, pathname, filename, originfilename);
//	    String localpath = "E:\\stockdata";
//	      
//	    FavFTPUtil.downloadFile(hostname, port, username, password, pathname, filename, localpath);
	}

}

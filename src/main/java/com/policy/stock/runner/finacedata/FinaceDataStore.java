package com.policy.stock.runner.finacedata;

import java.util.concurrent.LinkedBlockingQueue;

import com.policy.stock.model.FinaceData;

public class FinaceDataStore {
	
    public static LinkedBlockingQueue<FinaceData> finaceDatas = new LinkedBlockingQueue<FinaceData>(5000);
	
	public static LinkedBlockingQueue<String> codes = new LinkedBlockingQueue<String>(4000);

	public static LinkedBlockingQueue<FinaceData> getFinaceDatas() {
		return finaceDatas;
	}

	public static void setFinaceDatas(LinkedBlockingQueue<FinaceData> finaceDatas) {
		FinaceDataStore.finaceDatas = finaceDatas;
	}

	public static LinkedBlockingQueue<String> getCodes() {
		return codes;
	}

	public static void setCodes(LinkedBlockingQueue<String> codes) {
		FinaceDataStore.codes = codes;
	}

}

package com.policy.stock.runner.finacedata;

import com.policy.stock.model.FinaceData;
import com.policy.stock.service.IFinaceDataService;

public class FinaceStoreRunner extends Thread {

	private IFinaceDataService finaceDataService;

	public FinaceStoreRunner(IFinaceDataService service) {
		finaceDataService = service;
	}

	@Override
	public void run() {
		while (true) {
			FinaceData data = null;
			try {
				data = FinaceDataStore.getFinaceDatas().take();
				finaceDataService.save(data);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

package com.policy.stock.service;

import java.util.List;

import com.policy.stock.model.StockCoreData;

public interface IStockCoreDataService {
	
	public void save(StockCoreData data);
	public void saveAll(List<StockCoreData> datas);
}

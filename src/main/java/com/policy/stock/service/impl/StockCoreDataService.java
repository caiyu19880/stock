package com.policy.stock.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.policy.stock.mapper.StockCoreDataMapper;
import com.policy.stock.model.StockCoreData;
import com.policy.stock.service.IStockCoreDataService;

@Service
public class StockCoreDataService implements IStockCoreDataService {

	@Autowired
	private StockCoreDataMapper stockCoreDataMapper;

	@Override
	public void save(StockCoreData data) {
		// TODO Auto-generated method stub
		stockCoreDataMapper.save(data);
	}

	@Override
	public void saveAll(List<StockCoreData> datas) {
		// TODO Auto-generated method stub
		stockCoreDataMapper.saveAll(datas);
	}

}

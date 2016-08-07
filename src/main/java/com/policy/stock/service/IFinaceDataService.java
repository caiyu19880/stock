package com.policy.stock.service;

import java.util.List;

import com.policy.stock.model.FinaceData;

public interface IFinaceDataService {
	
	public void save(FinaceData data);
	public void saveAll(List<FinaceData> datas);
}

package com.policy.stock.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.policy.stock.mapper.FinaceDataMapper;
import com.policy.stock.model.FinaceData;
import com.policy.stock.service.IFinaceDataService;

@Service
public class FinaceDataService implements IFinaceDataService {

	@Autowired
	private FinaceDataMapper finaceDataMapper;
	
	@Override
	public void save(FinaceData data) {
		// TODO Auto-generated method stub
		finaceDataMapper.save(data);
	}

	@Override
	public void saveAll(List<FinaceData> datas) {
		// TODO Auto-generated method stub
		finaceDataMapper.saveAll(datas);
	}

}

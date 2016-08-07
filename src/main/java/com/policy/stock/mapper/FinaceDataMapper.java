package com.policy.stock.mapper;

import java.util.List;

import com.policy.stock.model.FinaceData;

public interface FinaceDataMapper {

	public void save(FinaceData data);
	public void saveAll(List<FinaceData> datas);
}

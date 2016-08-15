package com.policy.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.policy.stock.model.StockCoreData;

public interface StockCoreDataMapper {

	public void save(@Param("data") StockCoreData data);
	public void saveAll(List<StockCoreData> datas);
}

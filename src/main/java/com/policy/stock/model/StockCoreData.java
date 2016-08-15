package com.policy.stock.model;

import java.sql.Timestamp;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class StockCoreData {

	private int id;
	private String stockCode;
	private Timestamp updateDate;
	private String sy;// 收益（一）
	private Double peD;// pe（动）
	private String jzc;// 净资产
	private String sjl;// 市净率
	private String zsr;// 总收入
	private String zsrtb;// 同比
	private String jlr;// 净利润
	private String jlrtb;// 净利润同比
	private String mll;// 毛利率
	private String jll;// jinglilv
	private String roe;
	private String fzl;// fuzhailv
	private String zgb;// zongguben
	private String zz;// zongzhi
	private String ltg;// liutonggu
	private String lz;// liuzhi
	private String mgwfplr;// 每股未分配利润
	private Timestamp inStockDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public String getSy() {
		return sy;
	}

	public void setSy(String sy) {
		this.sy = sy;
	}

	public Double getPeD() {
		return peD;
	}

	public void setPeD(Double peD) {
		this.peD = peD;
	}

	public String getJzc() {
		return jzc;
	}

	public void setJzc(String jzc) {
		this.jzc = jzc;
	}

	public String getSjl() {
		return sjl;
	}

	public void setSjl(String sjl) {
		this.sjl = sjl;
	}

	public String getZsr() {
		return zsr;
	}

	public void setZsr(String zsr) {
		this.zsr = zsr;
	}

	public String getZsrtb() {
		return zsrtb;
	}

	public void setZsrtb(String zsrtb) {
		this.zsrtb = zsrtb;
	}

	public String getJlr() {
		return jlr;
	}

	public void setJlr(String jlr) {
		this.jlr = jlr;
	}

	public String getJlrtb() {
		return jlrtb;
	}

	public void setJlrtb(String jlrtb) {
		this.jlrtb = jlrtb;
	}

	public String getMll() {
		return mll;
	}

	public void setMll(String mll) {
		this.mll = mll;
	}

	public String getJll() {
		return jll;
	}

	public void setJll(String jll) {
		this.jll = jll;
	}

	public String getRoe() {
		return roe;
	}

	public void setRoe(String roe) {
		this.roe = roe;
	}

	public String getFzl() {
		return fzl;
	}

	public void setFzl(String fzl) {
		this.fzl = fzl;
	}

	public String getZgb() {
		return zgb;
	}

	public void setZgb(String zgb) {
		this.zgb = zgb;
	}

	public String getZz() {
		return zz;
	}

	public void setZz(String zz) {
		this.zz = zz;
	}

	public String getLz() {
		return lz;
	}

	public void setLz(String lz) {
		this.lz = lz;
	}

	public String getMgwfplr() {
		return mgwfplr;
	}

	public void setMgwfplr(String mgwfplr) {
		this.mgwfplr = mgwfplr;
	}

	public Timestamp getInStockDate() {
		return inStockDate;
	}

	public void setInStockDate(Timestamp inStockDate) {
		this.inStockDate = inStockDate;
	}

	public String getLtg() {
		return ltg;
	}

	public void setLtg(String ltg) {
		this.ltg = ltg;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}

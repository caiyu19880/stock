package com.policy.stock.runner.stockcoredata;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.htmlparser.Parser;
import org.htmlparser.filters.CssSelectorNodeFilter;
import org.htmlparser.tags.TableColumn;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.policy.stock.model.StockCoreData;
import com.policy.stock.service.IStockCoreDataService;

public class StockCoreDataParseRunner extends Thread {

	private String baseUrl = "http://quote.eastmoney.com/{stockCode}.html";

	private String stockCode;

	private IStockCoreDataService stockCoreDataService;

	public StockCoreDataParseRunner(IStockCoreDataService service) {
		stockCoreDataService = service;
	}

	@Override
	public void run() {
		while(true){
			
			try {
				stockCode = StockCoreDataStore.getCodes().take();
				if (StringUtils.isNotEmpty(stockCode)) {
					this.parseHtmlContent(stockCode);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void parseHtmlContent(String codeStr) {
		StockCoreData data = null;
		try {
			if(codeStr.startsWith("0")|| codeStr.startsWith("3")){
				codeStr = "sz" + codeStr;
			}else if(codeStr.startsWith("6")){
				codeStr = "sh" + codeStr;
			}
			
			Parser myParser = new Parser(baseUrl.replace("{stockCode}", codeStr));
			myParser.setEncoding("GBK");
			CssSelectorNodeFilter alertFilter = new CssSelectorNodeFilter("table[id='rtp2'] td");
			NodeList nodeList = myParser.extractAllNodesThatMatch(alertFilter);
			TableColumn td = null;
			String tdContent = null;
			data = new StockCoreData();
			data.setStockCode(codeStr);
			data.setUpdateDate(new Timestamp(new Date().getTime()));
			for (int i = 0; i < nodeList.size(); i++) {
				td = (TableColumn) nodeList.elementAt(i);
				tdContent = td.toPlainTextString();
				if (StringUtils.isNotEmpty(tdContent)) {
					this.setStockCoreDataValue(tdContent, data);
				}
			}
		} catch (ParserException e) {
			System.out.println("ParserException : Comsumer " + e);
		}

		if (null != data) {
			try {
				stockCoreDataService.save(data);
			} catch (Exception e) {
				System.out.println("stockCoreDataService.save(data) error due to , data : " + data.toString()
						+ " error is :" + e.getMessage());
			}
		}

		codeStr = null;
		data = null;
	}

	private void setStockCoreDataValue(String tdContent, StockCoreData data) {
		if (StringUtils.isNotEmpty(tdContent)) {
			String switchStr = tdContent.split("：")[0];
			switch (switchStr) {
			case "收益(一)":
				String sy = "NA";// 异常值
				try {
					sy = tdContent.split("：")[1].trim();
				} catch (Exception e) {
					e.printStackTrace();
				}
				data.setSy(sy);
				break;
			case "PE(动)":
				double pe = 99999999;// 异常值
				try {
					pe = Double.parseDouble(tdContent.split("：")[1].trim());
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				data.setPeD(pe);
				break;
			case "净资产":
				String jzc = "NA";// 异常值
				try {
					jzc = tdContent.split("：")[1].trim();
				} catch (Exception e) {
					e.printStackTrace();
				}
				data.setJzc(jzc);
				break;
			case "市净率":
				String sjl = "NA";// 异常值
				try {
					sjl = tdContent.split("：")[1].trim();
				} catch (Exception e) {
					e.printStackTrace();
				}
				data.setSjl(sjl);
				break;
			case "总收入":
				String zsr = "NA";// 异常值
				try {
					zsr = tdContent.split("：")[1].trim();
				} catch (Exception e) {
					e.printStackTrace();
				}
				data.setZsr(zsr);
				break;
			case "同比":
				String tb = "NA";// 异常值
				try {
					tb = tdContent.split("：")[1].trim();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (StringUtils.isEmpty(data.getJlrtb())) {
					data.setJlrtb(tb);
				} else {
					data.setZsrtb(tb);
				}
				break;
			case "净利润":
				String jlr = "NA";// 异常值
				try {
					jlr = tdContent.split("：")[1].trim();
				} catch (Exception e) {
					e.printStackTrace();
				}
				data.setJlr(jlr);
				break;
			case "毛利率":
				String mll = "NA";// 异常值
				try {
					mll = tdContent.split("：")[1].trim();
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				data.setMll(mll);
				break;
			case "净利率":
				String jll = "NA";// 异常值
				try {
					jll = tdContent.split("：")[1].trim();
				} catch (Exception e) {
					e.printStackTrace();
				}
				data.setJll(jll);
				break;
			case "ROE":
				String roe = "NA";// 异常值
				try {
					roe = tdContent.split("：")[1].trim();
				} catch (Exception e) {
				}
				data.setRoe(roe);
				break;
			case "负债率":
				String fzl = "NA";// 异常值
				try {
					fzl = tdContent.split("：")[1].trim();
				} catch (Exception e) {
					e.printStackTrace();
				}
				data.setFzl(fzl);
				break;
			case "总股本":
				String zgb = "NA";// 异常值
				try {
					zgb = tdContent.split("：")[1].trim();
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				data.setZgb(zgb);
				break;
			case "总值":
				String zz = "NA";// 异常值
				try {
					zz = tdContent.split("：")[1].trim();
				} catch (Exception e) {
					e.printStackTrace();
				}
				data.setZz(zz);
				break;
			case "流通股":
				String ltg = "NA";// 异常值
				try {
					ltg = tdContent.split("：")[1].trim();
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				data.setLtg(ltg);
				break;
			case "流值":
				String lz = "NA";// 异常值
				try {
					lz = tdContent.split("：")[1].trim();
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				data.setLz(lz);
				break;
			case "每股未分配利润":
				String mgwfplr = "NA";// 异常值
				try {
					mgwfplr = tdContent.split("：")[1].trim();
				} catch (Exception e) {
					e.printStackTrace();
				}
				data.setMgwfplr(mgwfplr);
				break;
			case "上市时间":
				String inStockDate = "NA";// 异常值
				try {
					inStockDate = tdContent.split("：")[1].trim();
					data.setInStockDate(Timestamp.valueOf(inStockDate + " 00:00:00"));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				break;
			}

		}
	}

}

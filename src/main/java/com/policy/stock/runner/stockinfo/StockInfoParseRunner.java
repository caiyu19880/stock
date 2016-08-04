package com.policy.stock.runner.stockinfo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.htmlparser.Parser;
import org.htmlparser.filters.CssSelectorNodeFilter;
import org.htmlparser.tags.TableColumn;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class StockInfoParseRunner extends Thread {

	private String baseUrl = "http://quote.eastmoney.com/{stockCode}.html";

	private String stockCode;

	public StockInfoParseRunner(String stockCode) {
		this.stockCode = stockCode;
	}

	@Override
	public void run() {
		if (StringUtils.isNotEmpty(stockCode)) {
			this.parseHtmlContent(stockCode);
		}
	}

	private void parseHtmlContent(String codeStr) {
		StringBuffer resStr = new StringBuffer("");
		try {
			Parser myParser = new Parser(baseUrl.replace("{stockCode}", codeStr));
			myParser.setEncoding("GBK");
			CssSelectorNodeFilter alertFilter = new CssSelectorNodeFilter("table[id='rtp2'] td");
			NodeList nodeList = myParser.extractAllNodesThatMatch(alertFilter);
			TableColumn td = null;
			String tdContent = null;
			resStr.append(codeStr).append("\r\n");
			resStr.append("Date:").append(new Date().toLocaleString()).append("\r\n");
			for (int i = 0; i < nodeList.size(); i++) {
				td = (TableColumn) nodeList.elementAt(i);
				tdContent = td.toPlainTextString();
				if(StringUtils.isNotEmpty(tdContent)){
					resStr.append(tdContent).append("\t");
				}else{
					resStr.append("NA").append("\t");
				}
				resStr.append("\r\n");
			}
		} catch (ParserException e) {
			System.out.println("ParserException : Comsumer " + e);
			resStr.append(codeStr);
		}

		try {
			Map<String,String> result = new HashMap<String,String>();
			result.put("stockCode",stockCode);
			result.put("stockDetails",resStr.toString());
			StockInfoDataStore.getStockInfos().put(result);
		} catch (InterruptedException e) {
			System.out.println("InterruptedException : Comsumer:getHtmlContents().put " + e);
		}
		codeStr = null;
	}

}

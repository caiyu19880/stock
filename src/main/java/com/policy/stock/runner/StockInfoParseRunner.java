package com.policy.stock.runner;

import org.apache.commons.lang3.StringUtils;
import org.htmlparser.Parser;
import org.htmlparser.filters.CssSelectorNodeFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class StockInfoParseRunner extends Thread {

	private String baseUrl = "http://basic.10jqka.com.cn/mobile/{stockCode}/managern.html";

	private String stockCode;
	
	public StockInfoParseRunner(String stockCode){
		this.stockCode = stockCode;
	}
	
	@Override
	public void run() {
		String listedCode = null;
		while (true) {
			try {
				if (StringUtils.isNotEmpty(listedCode)) {
					this.parseHtmlContent(listedCode);
				}
			} catch (Exception e) {
				System.out.println("Exception : stockCode: " + stockCode + " details : "+ e);
			}
		}
	}

	private void parseHtmlContent(String listedCode) {
		StringBuffer resStr = new StringBuffer("");
		try {
			Parser myParser = new Parser(baseUrl.replace("{stockCode}", listedCode.substring(0,listedCode.indexOf("."))));
			myParser.setEncoding("GBK");
			CssSelectorNodeFilter alertFilter = new CssSelectorNodeFilter("div[id='rpt2']");
			NodeList nodeList = myParser.extractAllNodesThatMatch(alertFilter);
			Div oneDiv = null;
			
			for (int i = 0; i < nodeList.size(); i++) {
				oneDiv = (Div) nodeList.elementAt(i);
				resStr.append(listedCode).append("\t");
				resStr.append(oneDiv.getParent().getParent().toPlainTextString().split("\\r\\n")[3].trim()).append("\t");
				resStr.append(oneDiv.getParent().getParent().toPlainTextString().split("\\r\\n")[6].trim()).append("\t");
				resStr.append(oneDiv.getParent().getParent().toPlainTextString().split("\\r\\n")[7].trim()).append("\t");
				resStr.append(oneDiv.getParent().getParent().toPlainTextString().split("\\r\\n")[8].trim());
				resStr.append("\r\n");
			}
		} catch (ParserException e) {
			System.out.println("ParserException : Comsumer " + e);
			resStr.append(listedCode);
		}

		try {
			DataStore.getStockInfos().put(resStr.toString());
		} catch (InterruptedException e) {
			System.out.println("InterruptedException : Comsumer:getHtmlContents().put " + e);
		}
		listedCode = null;
	}

}

package com.policy.stock.runner.finacedata;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.CssSelectorNodeFilter;
import org.htmlparser.tags.TableColumn;
import org.htmlparser.tags.TableRow;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;
import org.springframework.util.CollectionUtils;

import com.policy.stock.model.FinaceData;
import com.policy.stock.service.IFinaceDataService;
import com.policy.stock.utils.StringUtil;

public class FinaceZcfzbParseRunner extends Thread {

	private String baseUrl = "http://quotes.money.163.com/f10/zcfzb_{stockCode}.html?type=year";

	private String stockCode;
	
	private IFinaceDataService finaceDataService;

	public FinaceZcfzbParseRunner(IFinaceDataService service) {
		finaceDataService = service;
	}

	@Override
	public void run() {
		while(true){
			try {
				stockCode = FinaceDataStore.getCodes().take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (StringUtils.isNotEmpty(stockCode)) {
				this.parseHtmlContent(stockCode);
			}
		}
	}

	private void parseHtmlContent(String codeStr) {
		List<FinaceData> finaceDatas = new ArrayList<FinaceData>();
		try {
			Parser myParser = new Parser(baseUrl.replace("{stockCode}", codeStr));
			myParser.setEncoding("UTF-8");
			NodeFilter filter = new CssSelectorNodeFilter("table");
			// CssSelectorNodeFilter alertFilter = new
			// CssSelectorNodeFilter("table[class='table_bg001']");
			NodeList nodeList = myParser.extractAllNodesThatMatch(filter);
			TableTag table = null;
			String tablecss = null;
			FinaceData data = null;
			List<String> indexFinaceNames = null;
			String indexType = null;
			for (int i = 0; i < nodeList.size(); i++) {
				table = (TableTag) nodeList.elementAt(i);
				if (null == table) {
					continue;
				}
				tablecss = table.getAttribute("class");
				if (StringUtils.isEmpty(tablecss) || !tablecss.contains("table_bg001")) {
					continue;
				}
				// 资产负债表title
				if (tablecss.equals("table_bg001 border_box limit_sale")) {
				    indexFinaceNames = new ArrayList<String>();
					for (TableRow row : table.getRows()) {
					    indexFinaceNames.add(StringUtil.replaceBlank(row.toPlainTextString()));
					}
					continue;
				}

				// 资产负债表content
				if (tablecss.equals("table_bg001 border_box limit_sale scr_table")) {
					TableRow tempRow = null;
					TableColumn tempTd = null;
					String rowCss = null;
					for (int j = 1; j < table.getRowCount(); j++) {
						tempRow = table.getRows()[j];
						rowCss = tempRow.getAttribute("class");
						
						if("upbg".equals(rowCss) || tempRow.getChildCount() == 1){
						    indexType = indexFinaceNames.get(j);
						    continue;
						}
						
						for (int k = 0; k < 6; k++) {
							try {
								data = new FinaceData();
								data.setIndexName(indexFinaceNames.get(j));
								data.setType(indexType);
								data.setStockCode(codeStr);
								tempTd = tempRow.getColumns()[k];
								data.setIndexValue(tempTd.toPlainTextString());
								data.setYear(this.caculateYear(k));
								finaceDatas.add(data);
							} catch (Exception e) {
								System.out.println("ParserException : code :  " + codeStr + " error is :" + e);
							}
						}
						data = null;
					}
					indexFinaceNames = null;
					continue;
				}
			}
		} catch (Exception e) {
			System.out.println("ParserException : Comsumer " + e);
		}

		try {
			if(!CollectionUtils.isEmpty(finaceDatas)){
				finaceDataService.saveAll(finaceDatas);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finaceDatas = null;
		codeStr = null;
	}
	
	private String caculateTitle(int j) {
		String result = "NA";
		switch (j) {
		case 5:
			result = "盈利能力";
			break;
		case 6:
			result = "偿还能力";
			break;
		case 7:
			result = "成长能力";
			break;
		case 8:
			result = "营运能力";
			break;
		default:
			result = "NA";
			break;
		}

		return result;

	}

	private String caculateYear(int k) {
		String result = "NA";
		switch (k) {
		case 0:
			result = "2015-12-31";
			break;
		case 1:
			result = "2014-12-31";
			break;
		case 2:
			result = "2013-12-31";
			break;
		case 3:
			result = "2012-12-31";
			break;
		case 4:
			result = "2011-12-31";
			break;
		case 5:
			result = "2010-12-31";
			break;
		default:
			result = "NA";
			break;
		}

		return result;

	}

}

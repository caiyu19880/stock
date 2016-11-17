package com.policy.stock.runner.finacedata;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.CssSelectorNodeFilter;
import org.htmlparser.tags.TableColumn;
import org.htmlparser.tags.TableRow;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;

import com.policy.stock.runner.stockcoredata.StockCoreDataStore;

public class FinaceAllStockCodeParseRunner extends Thread {
    
    // 股票列表url
	private String baseUrl = "http://app.finance.ifeng.com/list/stock.php?t={type}&f=symbol&o=asc&p={pageNum}";
	
	@Override
	public void run() {
	    String type = "";
	    int pageNum = 0;
		try {
		    type = "ha";
		    while(this.parseHtmlContent(type, pageNum) || pageNum > 100){
		        pageNum ++;
		    }
		    
		    type = "hb";
		    pageNum = 0;
            while(this.parseHtmlContent(type, pageNum) || pageNum > 100){
                pageNum ++;
            }
            
            type = "sa";
            pageNum = 0;
            while(this.parseHtmlContent(type, pageNum) || pageNum > 100){
                pageNum ++;
            }
            
            type = "sb";
            pageNum = 0;
            while(this.parseHtmlContent(type, pageNum) || pageNum > 100){
                pageNum ++;
            }
            
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean parseHtmlContent(String type, int pageNum) {
		try {
			Parser myParser = new Parser(baseUrl.replace("{type}", type).replace("{pageNum}", String.valueOf(pageNum)));
			myParser.setEncoding("UTF-8");
			NodeFilter filter = new CssSelectorNodeFilter("table");
			// CssSelectorNodeFilter alertFilter = new
			// CssSelectorNodeFilter("table[class='table_bg001']");
			NodeList nodeList = myParser.extractAllNodesThatMatch(filter);
			TableTag table = null;
			String codeStr = "";
			for (int i = 0; i < nodeList.size(); i++) {
				table = (TableTag) nodeList.elementAt(i);
				if (null == table) {
					continue;
				}
				if (table.getRows().length == 2) {
					return false;
				}
			    
			    TableRow tempRow = null;
                TableColumn tempTd = null;
                for (int j = 1; j < table.getRowCount()-1; j++) {
                    tempRow = table.getRows()[j];
                    try {
                        tempTd = tempRow.getColumns()[0];
                        codeStr = tempTd.toPlainTextString();
                        StockCoreDataStore.codes.put(codeStr);
                    } catch (Exception e) {
                        System.out.println("ParserException : code :  " + codeStr + " error is :" + e);
                    }
                    codeStr = null;
                }
                continue;
			}
		} catch (Exception e) {
			System.out.println("ParserException : Comsumer " + e);
		}
		return true;
	}
	
}

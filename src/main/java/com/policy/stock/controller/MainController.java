package com.policy.stock.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.policy.stock.runner.finacedata.AllFinaceDataParseRunner;
import com.policy.stock.runner.finacedata.FinaceAllStockCodeParseRunner;
import com.policy.stock.runner.finacedata.FinaceCwbbzyParseRunner;
import com.policy.stock.runner.finacedata.FinaceDataStore;
import com.policy.stock.runner.finacedata.FinaceParseRunner;
import com.policy.stock.runner.finacedata.FinaceXjllbParseRunner;
import com.policy.stock.runner.finacedata.FinaceZcfzbParseRunner;
import com.policy.stock.runner.stockcoredata.StockCoreDataParseRunner;
import com.policy.stock.runner.stockcoredata.StockCoreDataStore;
import com.policy.stock.service.IFinaceDataService;
import com.policy.stock.service.IStockCoreDataService;

@RestController
public class MainController {
    
    @Autowired
    private IFinaceDataService finaceDataService;
    
    @Autowired
    private IStockCoreDataService stockCoreDataService;
    
    @RequestMapping(value = "/stock/allDataTrigger")
    public void allDataTrigger(HttpServletRequest request, HttpServletResponse response) {
        FinaceAllStockCodeParseRunner runner = new FinaceAllStockCodeParseRunner();
        runner.run();
        
        AllFinaceDataParseRunner producer;
        for(int i =0 ;i <2;i++){
            producer = new AllFinaceDataParseRunner(finaceDataService);
            producer.start();
        }
    }
    
    @RequestMapping(value = "/stock/trigger")
    public void trigger(HttpServletRequest request, HttpServletResponse response) {
    	String basepath = request.getSession().getServletContext().getRealPath("/");
    	List<String> codeList = this.getAllListedCode(basepath);
    	FinaceDataStore.getCodes().addAll(codeList);
    	FinaceParseRunner producer;
    	for(int i =0 ;i <1;i++){
    		producer = new FinaceParseRunner(finaceDataService);
    		producer.start();
    	}
    }
    
    @RequestMapping(value = "/stock/triggerXjllb")
    public void triggerXjllb(HttpServletRequest request, HttpServletResponse response) {
        String basepath = request.getSession().getServletContext().getRealPath("/");
        List<String> codeList = this.getAllListedCode(basepath);
        FinaceDataStore.getCodes().addAll(codeList);
        FinaceXjllbParseRunner producer;
        for(int i =0 ;i <1;i++){
            producer = new FinaceXjllbParseRunner(finaceDataService);
            producer.start();
        }
    }
    
    @RequestMapping(value = "/stock/triggerZcfzb")
    public void triggerZcfzb(HttpServletRequest request, HttpServletResponse response) {
        String basepath = request.getSession().getServletContext().getRealPath("/");
        List<String> codeList = this.getAllListedCode(basepath);
        FinaceDataStore.getCodes().addAll(codeList);
        FinaceZcfzbParseRunner producer;
        for(int i =0 ;i <1;i++){
            producer = new FinaceZcfzbParseRunner(finaceDataService);
            producer.start();
        }
    }
    
    @RequestMapping(value = "/stock/triggerCwbbzy")
    public void triggerCwbbzy(HttpServletRequest request, HttpServletResponse response) {
        String basepath = request.getSession().getServletContext().getRealPath("/");
        List<String> codeList = this.getAllListedCode(basepath);
        FinaceDataStore.getCodes().addAll(codeList);
        FinaceCwbbzyParseRunner producer;
        for(int i =0 ;i <1;i++){
            producer = new FinaceCwbbzyParseRunner(finaceDataService);
            producer.start();
        }
    }
    
    @RequestMapping(value = "/stock/coredata/trigger")
    public void coreData(HttpServletRequest request, HttpServletResponse response) {
    	String basepath = request.getSession().getServletContext().getRealPath("/");
    	List<String> codeList = this.getAllListedCode(basepath);
    	StockCoreDataStore.getCodes().addAll(codeList);
    	StockCoreDataParseRunner producer;
    	for(int i =0 ;i < 5;i++){
    		producer = new StockCoreDataParseRunner(stockCoreDataService);
    		producer.start();
    	}
    	
    }
    
    private List<String> getAllListedCode(String basepath){
    	
    	List<String> allListedCode = new ArrayList<String>();
    	
    	String encoding = "UTF-8"; // 编码方式
		File txt = new File(basepath + File.separator + "WEB-INF" + File.separator + "classes" + File.separator +  "listedcode.txt");
		InputStreamReader reader = null;
		try {
			reader = new InputStreamReader(new FileInputStream(txt), encoding);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		if(null == reader){
			return allListedCode;
		}
		
		LineNumberReader br = null;
		try {
			br = new LineNumberReader(reader);
			String s = "";
			while ((s = br.readLine()) != null) {
				if(StringUtils.isNotEmpty(s)){
					allListedCode.add(s);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    	return allListedCode;
    }
    
}

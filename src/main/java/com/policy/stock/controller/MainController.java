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

import com.policy.stock.runner.finacedata.FinaceDataStore;
import com.policy.stock.runner.finacedata.FinaceParseRunner;
import com.policy.stock.runner.finacedata.FinaceStoreRunner;
import com.policy.stock.service.IFinaceDataService;

@RestController
public class MainController {
    
    @Autowired
    private IFinaceDataService finaceDataService;
    
    @RequestMapping(value = "/stock/trigger")
    public void trigger(HttpServletRequest request, HttpServletResponse response) {
    	String basepath = request.getSession().getServletContext().getRealPath("/");
    	List<String> codeList = this.getAllListedCode(basepath);
    	FinaceDataStore.getCodes().addAll(codeList);
    	FinaceParseRunner producer;
    	FinaceStoreRunner consumer;
    	for(int i =0 ;i <1;i++){
    		producer = new FinaceParseRunner(finaceDataService);
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

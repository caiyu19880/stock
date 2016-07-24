package com.policy.stock.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class FileUtil {
	public static void appendFile(File f, String content) throws Exception {
		try {

			OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(f, true), "UTF-8");
			BufferedWriter fbw = new BufferedWriter(writer);
			fbw.write(content);
			fbw.close();
		 } catch (Exception e) {
			System.out.println("Error: " + e.getMessage());

		}
	}
}

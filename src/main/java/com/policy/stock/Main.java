package com.policy.stock;

import com.policy.stock.runner.StockInfoParseRunner;

public class Main {

	public static void main(String[] args) {
		StockInfoParseRunner runner = new StockInfoParseRunner("sz000413");
		runner.run();
	}

}

package com.seltzer.util;

import org.apache.log4j.Logger;

public class Sleep {

	private static Logger log = Logger.getLogger(Sleep.class);
	
	public static void forSec(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			log.error("Sleep interrupted", e);
		}
	}
	
}

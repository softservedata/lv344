package com.softserve.edu.opencart.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class TimeUtils {

	public static enum TimeTemplates {
		TIME_REPORT("yyyy/MM/dd HH:mm:ss"),
		TIME_DB("yyyy-MM-dd HH:mm:ss.S"),
		TIME_FULL("yyyy_MM_dd_HH-mm-ss"),
		TIME_HHMMSS("HH-mm-ss"),
		TIME_MMSS("mm-ss");
		//
		private String field;

		private TimeTemplates(String field) {
			this.field = field;
		}

		@Override
		public String toString() {
			return this.field;
		}
	}

	private SimpleDateFormat simpleDateFormat;

	public TimeUtils() {
		this.simpleDateFormat = new SimpleDateFormat(TimeTemplates.TIME_FULL.toString());
	}

	public TimeUtils(TimeTemplates timeTemplate) {
		this.simpleDateFormat = new SimpleDateFormat(timeTemplate.toString());
	}

	public String getTimeText() {
		return simpleDateFormat.format(new Date());
	}

	public String getTimeText(long date) {
		return simpleDateFormat.format(new Date(date));
	}

}
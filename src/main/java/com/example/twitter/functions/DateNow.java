package com.example.twitter.functions;

import java.sql.Timestamp;
import java.util.Date;

public class DateNow {
	
	public static Timestamp getTimeNow() {
		Date d = new Date();
		return new Timestamp(d.getTime());
	}

}


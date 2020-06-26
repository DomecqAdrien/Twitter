package com.example.twitter.functions;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CreateCookie {
	
	public static void setCookie(HttpServletResponse response, String key, String value) {
		Cookie cookie = new Cookie(key,value);
    	cookie.setPath("/");
    	response.addCookie(cookie);		
	}

}

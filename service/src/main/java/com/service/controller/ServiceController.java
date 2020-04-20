package com.service.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.service.impl.LegiDoxServiceImpl;

@RestController
@CrossOrigin

public class ServiceController {
	
	@RequestMapping(method = RequestMethod.GET, value="/api/test")	
	@ResponseBody
	public String gethtml()
	{
		return 
				"  <label for=\"f1\">Fact1:</label>\n" + 
				"  <input type=\"text\" id=\"f1\" name=\"f1\"><br>\n" + 
				"  <label for=\"f2\">Fact2:</label>\n" + 
				"  <input type=\"text\" id=\"f2\" name=\"f2\"><br>\n" 	;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/api/map")	
	@ResponseBody
	public Map<String, String> map()
	{
		LegiDoxServiceImpl service = new LegiDoxServiceImpl();
		
		return service.getMap();
	}
}

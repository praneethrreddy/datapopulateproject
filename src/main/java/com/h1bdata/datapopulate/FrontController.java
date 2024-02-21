package com.h1bdata.datapopulate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FrontController {

	@GetMapping("Hello")
//	@RequestMapping(method=RequestMethod.GET)
	public String firstMethod() {
		return "Hello World!";
	}

}

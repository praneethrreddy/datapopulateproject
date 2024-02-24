package com.h1bdata.datapopulate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.h1bdata.datapopulate.model.PermData;
import com.h1bdata.datapopulate.model.TestData;
import com.h1bdata.datapopulate.service.MyService;

@RestController
@RequestMapping("/test")
public class FrontController {

	@Autowired
    private final MyService myService;
	
//	
//	@GetMapping("Hello")
////	@RequestMapping(method=RequestMethod.GET)	
//	public String firstMethod() {
//		return "Hello World!";
//	}
//	

    public FrontController(MyService myService) {
		super();
		this.myService = myService;
	}
    
    
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PermData addCase(@RequestBody PermData permdata) {
    	return myService.addCase(permdata);
    }

    @GetMapping("/getAllCases")
    public List<PermData> getAllCases() {
    	return myService.getAllCases();
    }
    
	@GetMapping("/{num}")
    public ResponseEntity<List<PermData>> getDocumentsByName(@PathVariable String num) {
        return ResponseEntity.ok(myService.getCasesByCaseNumber(num));
    }
	
	@PutMapping
	public PermData modifyCase(@RequestBody PermData permdata) {
		return myService.updateCase(permdata);
		
	}
	
	@DeleteMapping("/{caseId}")
	public String deleteCase(@PathVariable String caseId) {
		return myService.deleteCase(caseId);
		
	}
	

}

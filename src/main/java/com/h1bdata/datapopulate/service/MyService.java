package com.h1bdata.datapopulate.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h1bdata.datapopulate.model.PermData;
import com.h1bdata.datapopulate.model.TestData;
import com.h1bdata.datapopulate.repository.PermDataRepository;

import java.util.List;
import java.util.UUID;


@Service
public class MyService {

    @Autowired
    private final PermDataRepository permDataRepository;
    
    
    //CRUD operations
 
    public MyService(PermDataRepository permDataRepository) {
		super();
		this.permDataRepository = permDataRepository;
	}

    public PermData addCase(PermData permdata) {
    	//permdata.setCase_number(UUID.randomUUID(). toString().split("-")[0]);
    	return permDataRepository.save(permdata);
    }

    public List<PermData> getAllCases() {
    	return permDataRepository.findAll();
    }
	public List<PermData> getCasesByCaseNumber(String casenum) {
        return  permDataRepository.findByNumber(casenum);
    }
	
	public PermData updateCase(PermData permdata) {
	
        //get the document from DB
		PermData data = permDataRepository.findByNumber(permdata.getCase_number()).get(0);
		data.setEmployer_name(permdata.getEmployer_name());
		data.setCase_status(permdata.getCase_status());
		return permDataRepository.save(data);
		
    }
	
	public String deleteCase(String id) {
		permDataRepository.deleteById(id);;
		
		return id+" is deleted from dashboard";
	}
	
//	public List<TestData> getCasesByName(String name) {
//        return permDataRepository.findByName(name);
//    }
}
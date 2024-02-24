package com.h1bdata.datapopulate.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import com.h1bdata.datapopulate.model.PermData;
import com.h1bdata.datapopulate.model.TestData;

@Repository
public interface PermDataRepository extends MongoRepository<PermData, String>{
	
	@Query("{'CASE_NUMBER':?0}")
	List<PermData> findByNumber(String number);
	
//	@Query("{'name':?0}")
//	List<TestData> findByName(String name);
}


	

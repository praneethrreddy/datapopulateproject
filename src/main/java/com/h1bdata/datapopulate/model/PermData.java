package com.h1bdata.datapopulate.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("permdata")
public class PermData {

    @Id
    private String id;
   @Field(name = "CASE_NUMBER")
    @Indexed (unique = true)
    private String case_number;
   @Field(name = "CASE_STATUS")
    private String case_status;
   @Field(name = "EMPLOYER_NAME")
    private String employer_name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCase_number() {
		return case_number;
	}
	public void setCase_number(String case_number) {
		this.case_number = case_number;
	}
	public String getCase_status() {
		return case_status;
	}
	public void setCase_status(String case_status) {
		this.case_status = case_status;
	}
	public String getEmployer_name() {
		return employer_name;
	}
	public void setEmployer_name(String employer_name) {
		this.employer_name = employer_name;
	}

    // getters and setters


	
	  
}
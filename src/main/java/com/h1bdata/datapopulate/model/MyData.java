package com.h1bdata.datapopulate.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MyData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String CASE_NUMBER;
    private String CASE_STATUS;
    private String EMPLOYER_NAME;

    // getters and setters

	public String getCASE_NUMBER() {
		return CASE_NUMBER;
	}

	public void setCASE_NUMBER(String cASE_NUMBER) {
		CASE_NUMBER = cASE_NUMBER;
	}

	public String getCASE_STATUS() {
		return CASE_STATUS;
	}

	public void setCASE_STATUS(String cASE_STATUS) {
		CASE_STATUS = cASE_STATUS;
	}

	public String getEMPLOYER_NAME() {
		return EMPLOYER_NAME;
	}

	public void setEMPLOYER_NAME(String eMPLOYER_NAME) {
		EMPLOYER_NAME = eMPLOYER_NAME;
	}

   
}
package com.h1bdata.datapopulate;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DatapopulateApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatapopulateApplication.class, args);
	}
	
	@Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job importUserJob;

    @Bean
    public CommandLineRunner commandLineRunner() {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                jobLauncher.run(importUserJob, new JobParametersBuilder().toJobParameters());
            }
        };
    }
    
}

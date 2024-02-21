package com.h1bdata.datapopulate.batch;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.h1bdata.datapopulate.listener.JobCompletionNotificationListener;
import com.h1bdata.datapopulate.model.MyData;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<MyData> reader() {
        return new FlatFileItemReaderBuilder<MyData>()
                .name("myDataReader")
                .resource(new ClassPathResource("testData.csv"))
                .delimited()
                .names(new String[]{"CASE_NUMBER", "CASE_STATUS", "EMPLOYER_NAME"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<MyData>() {{
                    setTargetType(MyData.class);
                }})
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<MyData> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<MyData>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO permTestData (CASE_NUMBER, CASE_STATUS, EMPLOYER_NAME) VALUES (:CASE_NUMBER, :CASE_STATUS, :EMPLOYER_NAME)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<MyData> writer) {
        return stepBuilderFactory.get("step1")
                .<MyData, MyData>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }

    private ItemProcessor<? super MyData, ? extends MyData> processor() {
		// TODO Auto-generated method stub
		return data -> data;
	}

	@Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }
}
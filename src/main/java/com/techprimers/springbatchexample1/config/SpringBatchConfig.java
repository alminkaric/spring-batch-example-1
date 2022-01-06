package com.techprimers.springbatchexample1.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.batch.item.validator.SpringValidator;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.Validator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.techprimers.springbatchexample1.batch.DBWriter;
import com.techprimers.springbatchexample1.dto.LineDTO;
import com.techprimers.springbatchexample1.listener.InvalidItemsListener;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory,
                   StepBuilderFactory stepBuilderFactory,
                   ItemReader<LineDTO> itemReader,
                   ItemProcessor<LineDTO, LineDTO> itemProcessor,
                   InvalidItemsListener invalidItemsListener,
                   DBWriter itemWriter
    ) {
    	
    	final List<LineDTO> invalidLines = new ArrayList<>();
    	invalidItemsListener.setInvalidLines(invalidLines);
    	itemWriter.setInvalidLines(invalidLines);

        Step step = stepBuilderFactory.get("ETL-file-load")
                .<LineDTO, LineDTO>chunk(100)
                .reader(itemReader)
                .listener(invalidItemsListener)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();


        return jobBuilderFactory.get("ETL-Load")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    @Bean
    @StepScope
    public FlatFileItemReader<LineDTO> itemReader(@Value("#{jobParameters[dest]}") String dest) {

        FlatFileItemReader<LineDTO> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(new FileSystemResource(dest));
        flatFileItemReader.setName("CSV-Reader");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(lineMapper());
        return flatFileItemReader;
    }
    
    @Bean
	public org.springframework.validation.Validator validator() {
		// see https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#validation-beanvalidation-spring
		return new org.springframework.validation.beanvalidation.LocalValidatorFactoryBean();
	}

	@Bean
	public Validator<LineDTO> springValidator() {
		SpringValidator<LineDTO> springValidator = new SpringValidator<>();
		springValidator.setValidator(validator());
		return springValidator;
	}

	@Bean
	public ItemProcessor<LineDTO, LineDTO> itemProcessor() {
		ValidatingItemProcessor<LineDTO> validatingItemProcessor = new ValidatingItemProcessor<>(springValidator());
		validatingItemProcessor.setFilter(true);
		return validatingItemProcessor;
	}

    @Bean
    public LineMapper<LineDTO> lineMapper() {
        final DefaultLineMapper<LineDTO> defaultLineMapper = new DefaultLineMapper<>();

        final DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        defaultLineMapper.setLineTokenizer(lineTokenizer);

        final LineDTOFieldSetMapper fieldSetMapper = new LineDTOFieldSetMapper();
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return defaultLineMapper;
    }
    
    private static class LineDTOFieldSetMapper implements FieldSetMapper<LineDTO> {
        public LineDTO mapFieldSet(FieldSet fieldSet) {
            final LineDTO lineDTO = new LineDTO();
            lineDTO.setEmpId(fieldSet.readInt(0));
            lineDTO.setFirstName(fieldSet.readRawString(1));
            lineDTO.setLastName(fieldSet.readRawString(2));
            lineDTO.setDeptId(fieldSet.readRawString(3));
            lineDTO.setSalaryDate(fieldSet.readRawString(4));
            lineDTO.setSalary(fieldSet.readRawString(5));
            lineDTO.setRawLine(String.join(",", fieldSet.getValues()));

            return lineDTO;
        }
    }

}

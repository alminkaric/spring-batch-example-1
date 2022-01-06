package com.techprimers.springbatchexample1.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.validator.SpringValidator;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.Validator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

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
                   ItemWriter<LineDTO> itemWriter
    ) {

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

        DefaultLineMapper<LineDTO> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("emp_id", "first_name", "last_name", "dept_id", "salary_date", "salary");

        BeanWrapperFieldSetMapper<LineDTO> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(LineDTO.class);

        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return defaultLineMapper;
    }

}

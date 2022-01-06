package com.techprimers.springbatchexample1;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.techprimers.springbatchexample1.controller.LoadController;

@SpringBootApplication
public class SpringBatchExample1Application {
	private static final Logger logger = LogManager.getLogger();

	public static void main(String[] args) {
		logger.log(Level.INFO, () -> "Going to run spring application with arguments: " + String.join(" ", args));
		final ConfigurableApplicationContext context = SpringApplication.run(SpringBatchExample1Application.class, args);

		final String csvPath = readCSVPathFromArgs(args);
		try {
			final LoadController loadController = context.getBean(LoadController.class);
			final BatchStatus status = loadController.load(csvPath);
			logger.log(Level.INFO, () -> "Parsing is finished with status=" + status);
		} catch (JobParametersInvalidException 
				| JobExecutionAlreadyRunningException 
				| JobRestartException
				| JobInstanceAlreadyCompleteException e) {
			logger.log(Level.ERROR, () -> "Error while parsing " + csvPath, e);
		}
		
	}
	
	private static String readCSVPathFromArgs(String[] args) {
		final String csvPathString = "--csvPath=";
		for (String arg : args) {
			if (arg.contains(csvPathString)) {
				return arg.replace(csvPathString, "");
			}
		}
		
		throw new IllegalArgumentException("Couldn't find argument 'csvPath', stopping...");
	}
	
}

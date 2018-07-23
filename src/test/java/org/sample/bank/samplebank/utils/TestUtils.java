package org.sample.bank.samplebank.utils;

import java.io.IOException;

import org.h2.jdbcx.JdbcDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;

public class TestUtils {
	
	private static final Logger log = LoggerFactory.getLogger(TestUtils.class);
	
	private static JdbcDataSource dataSource;
	
	public static JdbcDataSource initDatabase() {
		dataSource = new JdbcDataSource();
		YamlPropertySourceLoader yamlLoader = new YamlPropertySourceLoader();
		PropertySource<?> yamlSource = null;
		
		try {
			yamlSource = yamlLoader.load("application", new ClassPathResource("application-test.yaml"), null);
		}catch (IOException ioe) {
			throw new RuntimeException("Failed to load property file", ioe);
		}
		
		dataSource.setURL(
				(String) yamlSource.getProperty("spring.application.datasource.url"));
		dataSource.setUser(
				(String) yamlSource.getProperty("spring.application.datasource.username"));
		dataSource.setPassword(
				(String) yamlSource.getProperty("spring.application.datasource.password"));
		log.info("Setting datasource with following url : {} ", dataSource.getUrl());
		
		return dataSource;
	}

}

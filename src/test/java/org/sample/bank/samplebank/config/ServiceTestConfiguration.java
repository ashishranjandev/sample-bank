package org.sample.bank.samplebank.config;

import javax.sql.DataSource;

import org.sample.bank.samplebank.utils.TestUtils;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class ServiceTestConfiguration {
	
	@Bean
	@Primary
	public DataSource dataSource() {
		return TestUtils.initDatabase();
	}
}

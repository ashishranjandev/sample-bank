package org.sample.bank.samplebank;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sample.bank.samplebank.config.ServiceTestConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {ServiceTestConfiguration.class, SampleBankApplication.class})
public class SampleBankApplicationTests {

	@Test
	public void contextLoads() {
		
	}

}

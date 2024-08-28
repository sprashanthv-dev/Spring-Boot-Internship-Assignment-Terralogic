package com.sprashanthv.nodeanalysis;


import com.sprashanthv.nodeanalysis.config.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(TestConfig.class)
class NodeanalysisApplicationTests {

	@Test
	void contextLoads() {
	}

}

package com.example.baeldung_tutorials;

import com.example.baeldung_tutorials.config.EnabledLocalTest;
import com.example.baeldung_tutorials.config.MyTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import({MyTestConfiguration.class})
@SpringBootTest
class BaeldungTutorialsApplicationTests {

	@Test
	void contextLoads() {
	}

}

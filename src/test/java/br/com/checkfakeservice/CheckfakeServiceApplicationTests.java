package br.com.checkfakeservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(profiles = "local")
class CheckfakeServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}

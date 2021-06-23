package app;

import app.utils.AES256;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.SecureRandom;
import java.util.UUID;

@SpringBootTest
class ApiServerApplicationTests {

	@Test
	void contextLoads() {

		String en = new AES256().encrypt(UUID.randomUUID().toString().replaceAll("-",""));

	}

}

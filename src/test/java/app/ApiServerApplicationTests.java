package app;

import app.utility.JWT;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Date;


@SpringBootTest
class ApiServerApplicationTests {

	@Value("${jasypt.encryptor.password}")
	private String key;

	@Test
	void contextLoads() {

	}

}

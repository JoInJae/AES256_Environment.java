package app;

import app.utility.JWT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;


@SpringBootTest
class ApiServerApplicationTests {

	@Autowired
	private JWT jwt;

	@Test
	void contextLoads() {

		Date after = new Date(System.currentTimeMillis() + 1000L);
		Date now = new Date();
		System.out.println(after.before(now));
	}

}

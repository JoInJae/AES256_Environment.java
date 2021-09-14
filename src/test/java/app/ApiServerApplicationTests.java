package app;

import app.utility.JWT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Date;


@SpringBootTest
class ApiServerApplicationTests {

	@Autowired
	private JWT jwt;

	@Test
	void contextLoads() {

		LocalDate now = LocalDate.now();

		System.out.println(now.getDayOfWeek());
	}

}

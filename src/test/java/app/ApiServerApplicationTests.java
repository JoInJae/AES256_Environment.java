package app;

import app.data.entity.type.Token;
import app.utility.JWT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ApiServerApplicationTests {

	@Autowired
	private JWT jwt;

	@Test
	void contextLoads() {
		System.out.println(Token.valueOf(Token.ACCESS.name()));
	}

}

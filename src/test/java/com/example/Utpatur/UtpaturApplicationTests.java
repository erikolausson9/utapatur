package com.example.Utpatur;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

@SpringBootTest
class UtpaturApplicationTests {

	@Autowired
	dbRepository repository;

	@Test
	void contextLoads() {
	}

	@Test
	void testSQLServler() throws SQLException {
		Assertions.assertTrue(repository.testDB());
	}

}

package com.example.Utpatur;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

@SpringBootTest
class UtpaturApplicationTests {

	@Autowired
    DbRepository repository;

	@Autowired
	MemberRepository memberRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testSQLServler() throws SQLException {
		Assertions.assertTrue(repository.testDB());
	}

	@Test
	void testAddRoute(){
		CreateNewRoute testRoute = new CreateNewRoute();
		testRoute.setRouteName("testRoute");
		testRoute.setDifficulty("easy");
		testRoute.setLength(100);
		repository.addRoute(testRoute);
		Route responseRoute = repository.getRoute(repository.getLastRouteID());
		Assertions.assertEquals(testRoute.getRouteName(), responseRoute.getRouteName());
		Assertions.assertEquals(testRoute.getLength(), responseRoute.getLength());
		Assertions.assertEquals(testRoute.getDifficulty(), responseRoute.getDifficulty());
	}

	@Test
	void testGetMemberByName(){
		Member member = memberRepository.getMemberByMemberName("Josse");
		int id = member.getMemberId();
		Assertions.assertEquals(1, id);
	}
}

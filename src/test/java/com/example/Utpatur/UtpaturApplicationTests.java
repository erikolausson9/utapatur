package com.example.Utpatur;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class UtpaturApplicationTests {

	@Autowired
    DbRepository dbRepository;

	@Autowired
	MemberRepository memberRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testSQLServler() throws SQLException {
		Assertions.assertTrue(dbRepository.testDB());
	}

	@Test
	void testAddRoute(){
		CreateNewRoute testRoute = new CreateNewRoute();
		testRoute.setRouteName("testRoute");
		testRoute.setDifficulty("easy");
		testRoute.setLength(100);
		dbRepository.addRoute(testRoute);
		Route responseRoute = dbRepository.getRoute(dbRepository.getLastRouteID());
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

	@Test
	void testGetRoutesByMemberId() {
		List<Route> routes = memberRepository.getRoutesByMemberId(35);
		Assertions.assertEquals(0, routes.size());
	}

	@Test
	void testDeleteRouteByRouteId()  {
		dbRepository.deleteRoute(65);
		List<Route> routes = memberRepository.getRoutesByMemberId(30);
		Assertions.assertEquals(4, routes.size());
	}
}

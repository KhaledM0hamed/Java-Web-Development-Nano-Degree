package com.example.testing;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TestingApplicationTests {

	private List<Integer> testList;
	@BeforeEach
	public void beforeEach(){
		testList = new ArrayList<>();
		testList.add(1);
		testList.add(2);
		testList.add(3);
	}
	@AfterEach
	public void afterEach(){
		testList = null;
	}
	@Test
	void testAddZero() {
		int a = 10;
		int b = 0;
		int c = a + b;
		assertEquals(a, c);
	}

	@Test
	void testDivideZero() {
		int a = 10;
		int b = 0;
		assertThrows(ArithmeticException.class, () -> {
			int c = a / b;
		});
	}

	@Test
	void testListContains() {
		assertTrue(testList.contains(1));
		testList.remove(0);
		assertFalse(testList.contains(1));
	}

}

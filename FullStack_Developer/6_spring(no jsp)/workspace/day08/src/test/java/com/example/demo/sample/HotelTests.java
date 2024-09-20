package com.example.demo.sample;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HotelTests {
	@Autowired
	private Hotel hotel;
	
	@Test
	@DisplayName("테스트 이름")
	public void testExist() {
//		System.out.println(hotel.getChef());
		//assert~~ : ~~이면 통과
//		assertNull(hotel.getChef());
		assertNotNull(hotel.getChef());
		System.out.println(hotel);
	}
}













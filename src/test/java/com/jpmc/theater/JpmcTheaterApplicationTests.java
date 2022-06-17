package com.jpmc.theater;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JpmcTheaterApplicationTests {

	@Autowired
	JpmcTheaterApplication bootApplication;
	
	@Test
	void testRun() { 
		String[] args = new String[0];
		try {
			bootApplication.run(args);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

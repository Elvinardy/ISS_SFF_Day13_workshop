package nus.iss.workshop_day13.Workshop_day13_Springboot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import nus.iss.workshop_day13.model.ContactModel;

@SpringBootTest
class WorkshopDay13SpringbootApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void testContact() throws Exception {
		ContactModel c = new ContactModel();
		c.setName("Vin"); 
		c.setEmail("vin@email.com");
		c.setPhoneNumber(1234567);
		// assert equals to the setter value
		assertEquals(c.getEmail(), "vin@email.com");
	}

}

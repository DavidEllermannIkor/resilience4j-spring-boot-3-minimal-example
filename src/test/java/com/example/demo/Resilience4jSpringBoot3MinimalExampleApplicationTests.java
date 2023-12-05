package com.example.demo;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@AutoConfigureMockMvc
@SpringBootTest(webEnvironment=WebEnvironment.DEFINED_PORT)
class Resilience4jSpringBoot3MinimalExampleApplicationTests {
	
  @Autowired
  private MockMvc mvc;

	@Test
	void whenCalledEndpoint_WithCircuitBreaker_ThenSecondEndpointIsCalledAndOkReturned() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/test/start"))
			.andExpect(status().isOk());
	}

}

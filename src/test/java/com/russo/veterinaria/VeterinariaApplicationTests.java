package com.russo.veterinaria;


import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VeterinariaApplicationTests {

	@Autowired
	TestRestTemplate restTemplate;

	@Test
	void ShouldReturnAListOfPets(){
		ResponseEntity<String> response = restTemplate.getForEntity("/pets",String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	void ShouldReturnAPet(){
		ResponseEntity<String> response = restTemplate.getForEntity("/pets/1",String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		System.out.println(JsonPath.parse(response.getBody()));
		DocumentContext documentContext = JsonPath.parse(response.getBody());
		Number id = documentContext.read("$.id");
		assertThat(id).isEqualTo(1);
	}
}

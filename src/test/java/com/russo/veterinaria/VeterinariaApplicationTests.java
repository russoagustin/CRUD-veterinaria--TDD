package com.russo.veterinaria;


import ch.qos.logback.core.rolling.helper.PeriodicityType;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.russo.veterinaria.model.Pet;
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

		String name = documentContext.read("$.name");
		assertThat(name).isEqualTo("Firulais");
	}

	@Test
	void shouldCreateANewPet(){
		Pet newPet = new Pet(null,"luna","terrier","dog","black and white");
		ResponseEntity<Void> response = restTemplate.postForEntity("/pets",newPet,Void.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
	}
}

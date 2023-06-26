package br.com.erudio.controller.cors.withjson

import br.com.erudio.TestConfigs
import br.com.erudio.integrationtests.AbstractIntegrationTest
import io.restassured.RestAssured.given
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class SwaggerIntegrationsTests() : AbstractIntegrationTest() {

	@Test
	fun shouldDisplaySwaggerUIPage() {
		val content = given()
			.basePath("/swagger-ui/index.html")
			.port(TestConfigs.SERVER_PORT)
			.`when`()
			.get()
			.then()
			.statusCode(200)
			.extract()
			.body()
			.asString()
		assertTrue(content.contains("Swagger ui"))

	}

}

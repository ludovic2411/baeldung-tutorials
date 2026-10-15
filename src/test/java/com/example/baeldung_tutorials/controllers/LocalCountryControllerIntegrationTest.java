package com.example.baeldung_tutorials.controllers;

import com.example.baeldung_tutorials.config.MyTestConfiguration;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.EnabledIf;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

/**
 * Test class with restassured to perform integrations tests locally
 * https://www.baeldung.com/rest-assured-tutorial
 * https://www.baeldung.com/rest-assured-header-cookie-parameter
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import({MyTestConfiguration.class})
@EnabledIf(
        expression = "#{!environment.acceptsProfiles('ci')}",
        loadContext = true
)
public class LocalCountryControllerIntegrationTest {

    private final static String BASE_URL = "api/country";
    private final static Header ACCEPT_JSON_HEADER = new Header("Accept","application/json");

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
    }

    @Test
    void testGetAll() {
        given()
                .pathParam("page",0)
                .header(ACCEPT_JSON_HEADER)
                //.baseUri(BASE_URL) Only works when host is fully defined
                .log()
                .all()
                .when()
                .get(BASE_URL+"/all/{page}")
                .then().statusCode(HttpStatus.OK.value())
                .assertThat().body("",hasSize(5));
    }

    @Test
    void testGetAllWithMoreItems() {
        given()
                .pathParam("page",0)
                .queryParam("items",10)
                .header(ACCEPT_JSON_HEADER)
                //.baseUri(BASE_URL) Only works when host is fully defined
                .log()
                .all()
                .when()
                .get(BASE_URL+"/all/{page}")
                .then().statusCode(HttpStatus.OK.value())
                .assertThat().body("",hasSize(10));
    }

    @Test
    void testGetCountryByCode() {
        given()
                .header(ACCEPT_JSON_HEADER)
                .queryParam("code","CZE")
                //.baseUri(BASE_URL) Only works when host is fully defined
                .log()
                .all()
                .when()
                .get(BASE_URL)
                .then().statusCode(HttpStatus.OK.value())
                .assertThat().body("name",equalTo("Czech Republic"));
    }

    @Test
    void testGetCountryByName() {
        given()
                .header(ACCEPT_JSON_HEADER)
                .queryParam("name","finland")
                //.baseUri(BASE_URL) Only works when host is fully defined
                .log()
                .all()
                .when()
                .get(BASE_URL)
                .then().statusCode(HttpStatus.OK.value())
                .assertThat().body("code",equalTo("FIN"));
    }
}

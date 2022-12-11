package Day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


public class SingleSpartanTest {

    @BeforeAll
    public static void setUp() {

        baseURI = "http://54.236.150.168:8000";
        basePath = "/api";

    }

    @AfterAll
    public static void tearDown(){

        reset();
    }

    @DisplayName("Testing GET /spartan/{id}")
    @Test
    public void testSpartan(){

        given()
                .accept(ContentType.JSON)
                .pathParam("id", 2).
        when()
                .get("/spartans/{id}").prettyPeek().
        then()
                .statusCode(is(200));

        System.out.println("-----------------------------------------------------------------------------------");

        given()
                .accept(ContentType.JSON).
        when()
                .get("/spartans/{id}", 2)
                .prettyPeek().
        then()
                .statusCode(is(200))
                .contentType(ContentType.JSON);

    }


    //--------------------------------------------------------------------------------------

    @DisplayName("Testing GET /spartan/{id} Payload")
    @Test
    public void testSpartanPayload(){
        given()
                .accept(ContentType.JSON).
                when()
                .get("/spartans/{id}", 2)
                .prettyPeek().
                then()
                .statusCode(is(200))
                .contentType(ContentType.JSON)
                .body("id", is(2))
                .body("name", is("Anton"))
                .body("gender", equalToIgnoringCase("male"))
                .body("phone", is(equalTo(1231231233)));

    }



}

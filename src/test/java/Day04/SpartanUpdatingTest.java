package Day04;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class SpartanUpdatingTest {
    @BeforeAll
    public static void setUp() {

        baseURI = "http://54.236.150.168:7000";
        basePath = "/api";

    }

    @AfterAll
    public static void tearDown() {

        reset();
    }

    @DisplayName("PUT one data TEST with basic Auth")
    @Test

    public void testPUTdataAuth() {

        String newSpartan = "{\n" +
                "        \"name\": \"Engineer\",\n" +
                "        \"gender\": \"Male\",\n" +
                "        \"phone\": 1234567890\n" +
                "    }";

        given()
                .pathParam("id", 254)
                .log().all()
                .auth().basic("admin", "admin")
                .body(newSpartan)
                .contentType(ContentType.JSON).
        when()
                .put("/spartans/{id}").
        then()
                .statusCode(204)
                .log().all()
                .header("Date", is(notNullValue()))
        ;

    }


    @DisplayName("PATCH one data TEST with basic Auth")
    @Test

    public void testPATCHdataAuth() {

        String newSpartan =
                "{ \"name\": \"Engineer2\" }";

        given()
                .pathParam("id", 253)
                .log().all()
                .auth().basic("admin", "admin")
                .body(newSpartan)
                .contentType(ContentType.JSON).
                when()
                .patch("/spartans/{id}").
                then()
                .statusCode(204)
                .log().all()
                .header("Date", is(notNullValue()))
        ;

       Response response =  given()
                         .pathParam("id", 253)
                         .log().all()
                         .auth().basic("admin", "admin").
                when()
                          .get("/spartans/{id}");

       String id = response.jsonPath().getString("id");

        System.out.println("id = " + id);


    }
    @DisplayName("DELETE one data TEST with basic Auth")
    @Test

    public void testDELETEdataAuth() {

        given()
                .pathParam("id", 253)
                .auth().basic("admin", "admin").

        when()
                .delete("/spartans/{id}").

        then()
                .statusCode(204);



    }



}

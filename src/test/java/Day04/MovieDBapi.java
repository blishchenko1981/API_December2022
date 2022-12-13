package Day04;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class MovieDBapi {

    // http://www.omdbapi.com/?s=One day&apikey=a3fabd87&y=2011


    @BeforeAll
    public static void setUp() {

        baseURI = "http://www.omdbapi.com";

    }

    @AfterAll
    public static void tearDown() {
        reset();
    }


    @DisplayName("Test Search Movie")
    @Test
    public void testMovie() {

        given()
                .queryParam("apikey", "a3fabd87")
                .queryParam("t", "The Orville").

                when()
                .get().prettyPeek().


                then()
                .statusCode(is(200))
                .contentType(ContentType.JSON)
                .body("Title", is("The Orville"))
                .body("Ratings[0].Source", is("Internet Movie Database"))
        ;


    }

    @DisplayName("Getting the log of request and response")
    @Test
    public void testSendingRequestAndGetLOG(){

        given()

                .queryParam("apikey", "a3fabd87")
                .queryParam("t", "John Wick").log().all().

        when()
                .get().
        then()
                .log().all()
                .statusCode(200)
                .body("Plot", containsString("ex-hit-man"))
                .body("Ratings[2].Source", is("Metacritic"))
        ;







    }
}

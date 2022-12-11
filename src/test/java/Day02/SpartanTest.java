package Day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;


import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


public class SpartanTest {

//http://54.236.150.168:8000/api/spartans

    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI = "http://54.236.150.168:8000";
        RestAssured.basePath = "/api";

    }

    @AfterAll
    public static void tearDown(){
        RestAssured.reset();
    }




    @DisplayName("Testing /api/spartans endpoint")
    @Test
    public void testGetAllSpartans() {

        String url = "http://54.236.150.168:8000/api/spartans";

        // send get request to above endpoint
        // save response and assert status code, ContentType

        Response response = get(url);

        response.prettyPrint();

        assertThat(response.statusCode(), is(200));
        assertThat(response.contentType(), equalTo(ContentType.JSON.toString()));
    }


    @DisplayName("Testing /api/spartans endpoint XML Response")
    @Test
    public void testGetAllSpartansXMLFormat() {

        /**
         * given
         *      -- RequestSpecification
         *      used to provide additional information about request
         *      header, query params, path variable, authentication, authorization, BODY(PAYLOAD)
         *      logging, cookie
         * when
         *      -- This is where you actually send the request
         *         GET, POST, PUT, DELETE goes with URL
         *         we get response object after sending the request
         * then
         *      -- ValidatableResponse
         *      validate StatusCode, header, payload, contentType, responseTime
         *
         */
    //-------------------------------------------------------------------------
        String url = "/spartans";  // will contain baseURI + basePath + "/spartans";  !!!!!!!!!!!!!!!!!!!!!!!!!

//-------------------------------------------------------------------------

        given()
                .header("Accept", "application/xml").
        when()
                .get("/spartans").
        then()
                .statusCode(200)
                .and()
                .header("Content-Type", "application/xml");
// -----------------------------------------------------------------------------

        given()
                .accept(ContentType.XML).
        when()
                .get("/spartans").
        then()
                .assertThat()
                .statusCode(is(200))
                .and()
                .contentType(ContentType.XML)
        ;





    }


}

package Day05;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ConfigReader;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AssertCollectionInChain {

    @BeforeAll
    public static void setUp() {

        baseURI = ConfigReader.read("spartan.baseURI");
        basePath = "/api";

    }

    @AfterAll
    public static void tearDown() {

        reset();
    }


    @DisplayName("Testing GET /api with basic Auth")
    @Test

    public void testSearchAndExtractData(){

        // search for nameContains : li, gender = Female
        // extract jsonPath object to get list of all results
        // check the size of result
        // verify all names from result contains "li"
        given()
                .auth().basic("admin", "admin")
                .queryParam("nameContains", "li")
                .queryParam("gender", "Female").
        when()
                .get("/spartans/search").
        then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("numberOfElements", equalTo(11))
                .body("content", hasSize(11))
                .body("content.name", everyItem(containsString("li")))
                .body("content.gender", everyItem(is("Female")))
                ;


    }

}










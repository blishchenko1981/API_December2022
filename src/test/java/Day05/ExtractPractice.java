package Day05;

import io.restassured.path.json.JsonPath;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ExtractPractice {

    @BeforeAll
    public static void setUp() {

        baseURI = "http://54.236.150.168:7000";
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
        JsonPath jp =
        given()
                .auth().basic("admin", "admin")
                .queryParam("nameContains", "li")
                .queryParam("gender", "Female").
        when()
                .get("/spartans/search").
        then()
           //     .log().body()
                .assertThat()
                .statusCode(is(200))
              .extract()
                .jsonPath()
        ;


       List<String> names =  jp.getList("content.name");

        System.out.println("names = " + names);

        int numOfElements = jp.getInt("numberOfElements");
        System.out.println("numOfElements = " + numOfElements);

        assertThat(numOfElements, equalTo(names.size())  );

         // using hamcrest matcher collection support for asserting the list size

        assertThat(names, hasSize(numOfElements));


    }

}










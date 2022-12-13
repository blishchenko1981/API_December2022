package Day04;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

public class LibraryAppTest {

    @BeforeAll
    public static void setUp() {

        //https://library2.cybertekschool.com/login.html

        baseURI = "https://library2.cybertekschool.com";
        basePath = "/rest/v1";

    }

    @AfterAll
    public static void tearDown() {

        reset();
    }


    @DisplayName("Test POST/login to Library")
    @Test
    public void testLogin() {


        String token = given()
                .contentType(ContentType.URLENC)
                .formParam("email", "librarian69@library")
                .formParam("password", "KNPXrm3S")
                .log().all().

                when()
                .post("/login").

                then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("token", is(not(emptyString())))
                .extract()
                .body().jsonPath().getString("token");

        System.out.println("token = " + token);

// Token:
//   "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjp7ImlkIjoiNDQ0MSIsImZ1bGxfbmF
//   tZSI6ImFsaSBkYWVpIiwiZW1haWwiOiJsaWJyYXJpYW42OUBsaWJyYXJ5IiwidXNlcl9ncm91cF9p
//   ZCI6IjIifSwiaWF0IjoxNjcwOTYyMzI1LCJleHAiOjE2NzM1NTQzMjV9.he6pcrF4fzukHO2rzq6g
//   no_2GQsGplAIRc94lO9XkJE",

    }


    //method to get token for library
    public String libraryTokenToLogin() {


        return given()
                .contentType(ContentType.URLENC)
                .formParam("email", "librarian69@library")
                .formParam("password", "KNPXrm3S").

                when()
                .post("/login").

                then()
                .extract()
                .body().jsonPath().getString("token");


    }


    @DisplayName("Test GET /dashboard_stats   Library")
    @Test
    public void testDashboard_stats() {

        given()
                .header("x-library-token", libraryTokenToLogin()).
        when()
                .get("/dashboard_stats").
        then()
                .log().all()
                .statusCode(200);
    }
}
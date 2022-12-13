package Day04;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;



//http://54.236.150.168:7000/api/spartans
public class SpartanAddingUpdatingTest {
    @BeforeAll
    public static void setUp() {

        baseURI = "http://54.236.150.168:7000";
        basePath = "/api";

    }

    @AfterAll
    public static void tearDown(){

        reset();
    }

    @DisplayName("Get one data TEST with basic Auth")
    @Test

    public void testAdd1data(){


        given()
                .auth().basic("admin", "admin")
                        .log().all().

        when()
                .get("/spartans/2").


        then()
                .log().body()
                .statusCode(200);






    }
}

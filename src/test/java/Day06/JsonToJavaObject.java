package Day06;

import POJO.Spartan;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ConfigReader;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class JsonToJavaObject {
    @BeforeAll
    public static void setUp() {

        baseURI = ConfigReader.read("spartan.baseURI");
        basePath = "/api";
        //  payloadMap = SpartanUtil.RandomSpartanPOSTPayload();


    }

    @AfterAll
    public static void tearDown() {

        reset();
    }


    @DisplayName("Get one data from RESPONSE and save it as a Java Object")
    @Test
    public void getOneSpartanAndSaveResponseAsObject() {

        Response response =
        given()
                .pathParam("id", 308)
                .auth().basic("admin", "admin").

                when()
                .get("/spartans/{id}")
                .prettyPeek();

        //get JSON path object

        JsonPath jp = response.jsonPath();

        Map<String, Object> responseMap = jp.getMap("");

        System.out.println("responseMap = " + responseMap);


    }

}

package Day03;


import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class JsonPathIntro {


    @BeforeAll
    public static void setUp() {

        baseURI = "http://54.236.150.168:8000";
        basePath = "/api";

    }

    @AfterAll
    public static void tearDown(){

        reset();
    }

    @DisplayName("Extracting data out of Spartan Json Object")
    @Test
    public void test1SpartanPayload(){

      Response resp =  given().pathParam("id", 2).when().get("/spartans/{id}");

      resp.prettyPeek();

      String name = resp.body().jsonPath().getString("name");
      System.out.println("name = " + name);

      JsonPath jp = resp.jsonPath();
      int myId = jp.getInt("id");
      String myGender = jp.getString("gender");
      long phone = jp.getLong("phone");

        System.out.println("myId = " + myId);
        System.out.println("myName = " + myGender);
        System.out.println("myPhone = " + phone);

    }



}

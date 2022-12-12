package Day03;


import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

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


    @DisplayName("Extracting data from Json Array Response")
    @Test
    public void getAllSpartansExtractData(){

     //   Response response = get("/spartans");

        JsonPath jp = get("/spartans").jsonPath();

        System.out.println("jp.getString(\"name[0]\") = " + jp.getString("name[0]"));
        System.out.println("jp.getString(\"gender[7]\") = " + jp.getString("gender[7]"));


        List<String> names = jp.getList("name");

        System.out.println("names = " + names);

        List<Integer> phones = get("/spartans").jsonPath().getList("phone");
        System.out.println("phones = " + phones);


    }



    @DisplayName("Testing /spartans/search and extracting data")
    @Test
    public void testSearch(){
      JsonPath jp =  given()
                            .queryParam("nameContains", "to")
                            .queryParam("gender", "Male").
                     when()
                             .get("/spartans/search")
                             .jsonPath();


        System.out.println("jp.getString(\"name\") = " + jp.getString("content.name"));

        System.out.println("First guy name with 'to' is " + jp.getString("content[0].name") );

        System.out.println("jp.getString(\"content\") = " + jp.getString("content"));

        jp.prettyPeek();

        System.out.println("jp.getInt(\"totalElement\") = " + jp.getInt("totalElement"));

    }



}

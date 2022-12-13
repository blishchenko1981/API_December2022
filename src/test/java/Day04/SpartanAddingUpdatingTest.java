package Day04;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

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
    public static void tearDown() {

        reset();
    }

    @DisplayName("Get one data TEST with basic Auth")
    @Test

    public void testGETdataAuth() {


        given()
                .auth().basic("admin", "admin")
                .log().all().

                when()
                .get("/spartans/2").


                then()
                .log().body()
                .statusCode(200);

    }


    @DisplayName("POST one data TEST with basic Auth")
    @Test

    public void testPOSTdataAuth() {

        String newSpartan = "{\n" +
                "        \"name\": \"Vitaly\",\n" +
                "        \"gender\": \"Male\",\n" +
                "        \"phone\": 1234567890\n" +
                "    }";


        given()
                .log().all()
                .auth().basic("admin", "admin")
                .contentType(ContentType.JSON)
                .body(newSpartan).


                when()
                .post("/spartans").


                then()
                .log().all()
                .statusCode(is(201))
                .body("success", is("A Spartan is Born!"))
                .body("data.name", is("Vitaly"))
                .body("data.gender", is("Male"))
        ;


    }


    @DisplayName("POST one data TEST with basic Auth as a MAP Object")
    @Test

    public void testPOSTdataAuthwithMap() {

        Map<String, Object> mapPayload = new LinkedHashMap<>();


        mapPayload.put("name", "Tester");
        mapPayload.put("gender", "Male");
        mapPayload.put("phone", 1234543201);

        System.out.println("mapPayload = " + mapPayload);

        given()
                .auth().basic("admin", "admin")
                .log().all()
                .contentType(ContentType.JSON).
                body(mapPayload).
                when()
                .post("/spartans").
                then()
                .log().all()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("data.name", is("Tester"))

        ;
    }


    @DisplayName("POST one data TEST with basic Auth as an External File")
    @Test

    public void testPOSTdataAuthwithExternalFile() {

        // Create a file called sigleSpartan.json rigth under root directory with content
        //    {
        //      "name": "Tester",
        //      "gender": "Male",
        //      "phone": 1234543201
        //     }



        File externalFile = new File("singleSpartan.json");

        given()
                .auth().basic("admin", "admin")
                .log().all()
                .contentType(ContentType.JSON).
                body(externalFile).
                when()
                .post("/spartans").
                then()
                .log().all()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("data.name", is("Automation"))

        ;

    }

}
















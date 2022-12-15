package Day05;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import utility.ConfigReader;
import utility.SpartanUtil;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@TestMethodOrder(MethodOrderer.DisplayName.class)
public class Spartan_E2E_HappyPath {


    private static Map<String, Object> payloadMap;
    private static int newID;


    @BeforeAll
    public static void setUp() {

        baseURI = ConfigReader.read("spartan.baseURI");
        basePath = "/api";
        payloadMap = SpartanUtil.RandomSpartanPOSTPayload();


    }

    @AfterAll
    public static void tearDown() {

        reset();
    }


    @DisplayName("1. Testing POST /api/spartans Endpoint")
    @Test
    public void testAddData() {

        newID =
                given()
                        .log().body()
                        .auth().basic(ConfigReader.read("spartan.admin.username"), ConfigReader.read("spartan.admin.password"))
                        .contentType(ContentType.JSON)
                        .body(payloadMap).
                when()
                        .post("/spartans").
                then()
                        .log().all()
                        .assertThat()
                        .statusCode(201)
                        .contentType(ContentType.JSON)
                        .body("data.name", is(payloadMap.get("name")))
                        .body("data.gender", equalTo(payloadMap.get("gender")))
                        .body("data.phone", is(payloadMap.get("phone")))
                     .extract()
                        .jsonPath()
                        .getInt("data.id")
        ;

        System.out.println("newID = " + newID);


    }


    @DisplayName("2. Testing GET /api/spartans{id} Endpoint")
    @Test
    public void testGETData() {

        given()

                .auth().basic("admin", "admin")
                .pathParam("id", newID).

        when()
                .get("/spartans/{id}").
        then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("name", is(payloadMap.get("name")))
                .body("gender", is(payloadMap.get("gender")))
                .body("phone", is(payloadMap.get("phone")));

    }

    @DisplayName("3. Testing PUT /api/spartans{id} Endpoint")
    @Test
    public void testUPDATE_PUT_Data() {

        payloadMap = SpartanUtil.RandomSpartanPOSTPayload();

        given()

                .auth().basic("admin", "admin")
                .pathParam("id", newID)
                .contentType(ContentType.JSON)
                .body(payloadMap).// updated payload for PUT request

        when()
                .put("/spartans/{id}").
        then()
                .log().all()
                .assertThat()
                .statusCode(204)
                .body(emptyString())
                ;


        // in order to make sure the Spartan was updated send another GET request

        given()

                .auth().basic("admin", "admin")
                .pathParam("id", newID).

                when()
                .get("/spartans/{id}").
                then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("name", is(payloadMap.get("name")))
                .body("gender", is(payloadMap.get("gender")))
                .body("phone", is(payloadMap.get("phone")));


    }

    @DisplayName("4. Testing DELETE /api/spartans/{id} Endpoint")
    @Test
    public void testDELETEData() {
        given()

                .auth().basic("admin", "admin")
                .pathParam("id", newID).
                when()
                .delete("/spartans/{id}").

                then()
                .statusCode(204);

        // make sure the Spartan was deleted send another get and assert status 404

        given()

                .auth().basic("admin", "admin")
                .pathParam("id", newID).

                when()
                .get("/spartans/{id}").
                then()
                .log().all()
                .assertThat()
                .statusCode(404)

        ;
    }
}

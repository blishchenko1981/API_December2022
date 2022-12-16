package Day07;

import POJO.Region;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class HR_ORDS_Test {

    // http://54.236.150.168:1000/ords/hr/regions/3

    @BeforeAll
    public static void setUp() {


        baseURI = "http://54.236.150.168:1000";
        basePath = "/ords/hr/";

    }

    @AfterAll
    public static void tearDown() {

        reset();
    }


    @Test
    public void testConnection() {

        get("/regions/3").prettyPeek();

    }


    @DisplayName("Test /regions/{region_id}")
    @Test
    public void testThirdRegionIsAsia() {

        given()
                .pathParam("region_id", 3)
                .log().all().


                when()
                .get("regions/{region_id}").


                then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("region_name", is("Asia"));

    }

    @DisplayName("Save GET /regions/region_id response as POJO")
    @Test
    public void testSingleRegionToPOJO() {

        Response response = given()
                .pathParam("region_id", 3)
                .log().all().

                when()
                .get("regions/{region_id}");

        JsonPath jp = response.jsonPath();

        Region r3 = jp.getObject("", Region.class);
        System.out.println("r3 = " + r3);

       Region  r4 =  response.as(Region.class);


    }

    @DisplayName("Save GET /regions/region_id response as List of  POJO")
    @Test
    public void testSingleRegionToListOfPOJO() {

        Response response = get("/regions");
        JsonPath jp = response.jsonPath();

        List<Region> allRegions = jp.getList("items", Region.class);

                allRegions.forEach(System.out::println);
    }


}
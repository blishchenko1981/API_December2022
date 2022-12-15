package Day06;

import POJO.Spartan;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ConfigReader;
import utility.SpartanUtil;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class PostWithCustomObject {


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


    @DisplayName("Add one data with POJO as a payload")
    @Test
    public void testAddData() {

     //   Spartan sp1 = new Spartan("POJOname", "Male", 1234567876);

        Spartan sp1 = SpartanUtil.getRandomSpartanPOJO();

        System.out.println("sp1 = " + sp1);


        given()
                .auth().basic("admin", "admin")
                .contentType(ContentType.JSON)
                .body(sp1)
                        .log().body().

                when()
                .post("/spartans").


                then()
                .statusCode(201)
                .log().all()
                .body("data.name", is(containsString((sp1.getName()))))
                .body("data.gender", is(sp1.getGender()))
                .body("data.", is(sp1.getName()))
                .body("success", is(containsString( " is Born")))

        ;

    }
}

package Day07;
import POJO.Spartan;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utility.ConfigReader;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;



public class PatchOneSpartanTest {

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



    @Test
    public void testPathOneDataPartialUpdateMAP(){

        Map<String , Object> patchBodyMap = new LinkedHashMap<>();
        patchBodyMap.put("name", "UpdatedName");

        given()
                .auth().basic("admin", "admin")
                .log().all()
                .pathParam("id", 310)
                .contentType(ContentType.JSON)
                .body(patchBodyMap).
        when()
                .patch("/spartans/{id}").
        then()
                .log().all()
                .statusCode(204)
;
    }


    @Test
    public void testPatchOneDataPartialUpdatePOJO() {

        Spartan sp = new Spartan();
        sp.setName("UpdatedName2");

        given()
                .auth().basic("admin", "admin")
                .log().all()
                .pathParam("id", 310)
                .contentType(ContentType.JSON)
                .body( sp).
                when()
                .patch("/spartans/{id}").
                then()
                .log().all()
                .statusCode(204)
        ;

    }
    }

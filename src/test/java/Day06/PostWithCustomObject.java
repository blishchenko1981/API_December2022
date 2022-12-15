package Day06;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ConfigReader;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;


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


    @DisplayName("")
    @Test
    public void testAddData() {

    }
}

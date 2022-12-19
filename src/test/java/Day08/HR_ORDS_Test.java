package Day08;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public class HR_ORDS_Test {

    @BeforeAll
    public static void setUp() {


        baseURI = "http://54.236.150.168:1000";
        basePath = "/ords/hr/";

    }

    @AfterAll
    public static void tearDown() {

        reset();
    }

}

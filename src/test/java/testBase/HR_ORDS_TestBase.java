package testBase;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import utility.ConfigReader;
import utility.DB_Util;

import static io.restassured.RestAssured.*;

public class HR_ORDS_TestBase {

    @BeforeAll
    public static void setUp() {


        baseURI = ConfigReader.read("ords.baseURL");
        basePath = ConfigReader.read("ords.basePath");

        // create Data Base connection

        DB_Util.createConnection(ConfigReader.read("hr.database.url"),
                ConfigReader.read("hr.database.username"),
                ConfigReader.read("hr.database.password"));

    }

    @AfterAll
    public static void tearDown() {

        reset();

        // Destroy DB connection here
        DB_Util.destroy();
    }

}

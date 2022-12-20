package Day08;

import POJO.Country;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import testBase.HR_ORDS_TestBase;

import java.util.List;

import static io.restassured.RestAssured.*;

public class HR_ORDS_Test extends HR_ORDS_TestBase {



    @DisplayName("Test GET /countries/ {country_id} to POJO")
    @Test
    public void testCountryResponseToPOJO(){

        Response response = given()
                .pathParam("country_id", "AR").
                when()
                .get("countries/{country_id}").prettyPeek();


        Country argent = response.as(Country.class);

        System.out.println("argent = " + argent);

        Country ar1 = response.jsonPath().getObject("", Country.class);

        System.out.println("ar1 = " + ar1);


    }

    @DisplayName("Test GET /countries to  list of POJO")
    @Test
    public void testCountryResponseToListOfPOJO() {
        Response response = get("/countries");

        List<Country> countryList = response.jsonPath().getList("items");

        System.out.println("countryList = " + countryList);

    }


}

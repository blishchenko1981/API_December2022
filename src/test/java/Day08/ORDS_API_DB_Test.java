package Day08;

import POJO.Region;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import testBase.HR_ORDS_TestBase;
import utility.DB_Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ORDS_API_DB_Test extends HR_ORDS_TestBase {

    @DisplayName("Testing the connection with query")
    @Test
    public void testDB_Connection() {

        DB_Util.runQuery("select * from regions");
        DB_Util.displayAllData();
    }


    @DisplayName("Testing GET / regions/{region_id} Data match database")
    @Test
    public void testRegionDataFromResponseMatchDB_Data() {

        int myID = 3;
        Response response = given()
                .pathParam("region_id", myID).
                when()
                .get("/regions/{region_id}").prettyPeek().
                then()
                .log().body()
                .statusCode(200)
                .extract().response();

        Region r3 = response.as(Region.class);
        System.out.println("r3 = " + r3);

        DB_Util.runQuery("select * from regions where region_id = " + myID);
        Map<String, String> expectedResultMap = DB_Util.getRowMap(1);

        System.out.println("expectedResult from DB " + expectedResultMap);

        assertThat(r3.getRegion_id() + "", is(expectedResultMap.get("REGION_ID")));
    }

    @DisplayName("Testing GET / regions/{region_id} Data match database with both Maps")
    @Test
    public void testRegionDataFromResponseMatchDB_Data_BothMaps() {

        int myID = 3;
        Response response = given()
                .pathParam("region_id", myID).
                when()
                .get("/regions/{region_id}").prettyPeek().
                then()
                .log().body()
                .statusCode(200)
                .extract().response();

        Map<String, String> resultFromAPI = response.jsonPath().getMap("");

        System.out.println("resultFromAPI = " + resultFromAPI);

        resultFromAPI.remove("links");
        System.out.println("resultFromAPI = " + resultFromAPI);


        DB_Util.runQuery("select * from regions where region_id = " + myID);
        Map<String, String> expectedResultMapFromDB = DB_Util.getRowMap(1);
        System.out.println("expectedResultMapFromDB = " + expectedResultMapFromDB);

        assertThat(expectedResultMapFromDB.get("region_name"), equalTo(resultFromAPI.get("REGION_NAME")));
        assertThat(expectedResultMapFromDB.get("region_id"), equalTo(resultFromAPI.get("REGION_ID")));

    }

    @DisplayName("Testing GET / regions/{region_id} Data match database with JSONPath")
    @Test
    public void testRegionDataFromResponseMatchDB_Data_JsonPath() {

        int regID = 3;
        Response response
                = given()
                .pathParam("region_id", regID).
                when()
                .get("/regions/{region_id}").
                then()
                .log().all()
                .statusCode(200)
                .extract().response();

        String regionID_api = response.jsonPath().getString("region_id");
        String regionName_api = response.jsonPath().getString("region_name");

        DB_Util.runQuery("select * from regions where region_id = " + regID);
        String regionName_DB = DB_Util.getCellValue(1, "REGION_NAME");
        String regionID_DB = DB_Util.getCellValue(1, "REGION_ID");

        assertThat(regionID_api, equalTo(regionID_DB));
        assertThat(regionName_api, equalTo(regionName_DB));

    }


    @Test
    public void orderArrayAsc() {
        int[] nums = {1,3,4,5, -1 ,0,2,-100, -200, 100 , 200  , 20000};


        for (int i = 0; i < nums.length -1; i++) {


            for (int j = i+1; j < nums.length; j++) {

                int temp = nums[i];
                if(nums[j]< nums[i]){

                    nums[i] = nums[j];
                    nums[j] = temp;

                }

            }


        }




        System.out.println("Arrays.toString(nums) = " + Arrays.toString(nums));




        for (int i = 0 ; i< nums.length-1; i++){


            for (int j = i+1; j < nums.length; j++) {
                int temp = nums[i];
                if(nums[i]>nums[j]) {
                    nums[i] = nums[j];
                    nums[j] = temp;

                }
            }

        }

        System.out.println("nums = " + Arrays.toString(nums));


    }
}
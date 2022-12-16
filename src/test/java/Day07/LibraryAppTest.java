package Day07;

import POJO.BookCategory;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


@TestMethodOrder(MethodOrderer.DisplayName.class)
public class LibraryAppTest {

    @BeforeAll
    public static void setUp() {

        //https://library2.cybertekschool.com/rest/v1

        baseURI = "https://library2.cybertekschool.com";
        basePath = "/rest/v1";

    }

    @AfterAll
    public static void tearDown() {

        reset();
    }


    @DisplayName("1.Test POST/login to Library")
    @Test
    public void testLogin() {


        String token = given()
                .contentType(ContentType.URLENC)
                .formParam("email", "librarian69@library")
                .formParam("password", "KNPXrm3S")
                .log().all().

                when()
                .post("/login").

                then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("token", is(not(emptyString())))
                .extract()
                .body().jsonPath().getString("token");

        System.out.println("token = " + token);

// Token:
//   "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjp7ImlkIjoiNDQ0MSIsImZ1bGxfbmF
//   tZSI6ImFsaSBkYWVpIiwiZW1haWwiOiJsaWJyYXJpYW42OUBsaWJyYXJ5IiwidXNlcl9ncm91cF9p
//   ZCI6IjIifSwiaWF0IjoxNjcwOTYyMzI1LCJleHAiOjE2NzM1NTQzMjV9.he6pcrF4fzukHO2rzq6g
//   no_2GQsGplAIRc94lO9XkJE",

    }


    //method to get token for library
    public String libraryTokenToLogin() {


        return given()
                .contentType(ContentType.URLENC)
                .formParam("email", "librarian69@library")
                .formParam("password", "KNPXrm3S").

                when()
                .post("/login").

                then()
                .extract()
                .body().jsonPath().getString("token");


    }


    @DisplayName("2.Test GET /dashboard_stats   Library")
    @Test
    public void testDashboard_stats() {

        given()
                .header("x-library-token", libraryTokenToLogin()).
        when()
                .get("/dashboard_stats").
        then()
                .log().all()
                .statusCode(200);


    }


    @DisplayName("3.Save the result of GET Dashboard Stat as Map Object")
    @Test
    public void testGetDashBoardAsMap(){

//        {
//            "book_count": "23525",
//                "borrowed_books": "4461",
//                "users": "7401"
//        }

        JsonPath jp =
        given()
                .log().all()
                .header("x-library-token", libraryTokenToLogin()).
        when()
                .get("/dashboard_stats")
                .jsonPath();

        // Get response as a Map

        Map<String, Object> responseAsMap = jp.getMap("");

        System.out.println("responseAsMap = " + responseAsMap);

    }

    @DisplayName("4.GET all books Categories as POJO")
    @Test
    public void testGETbookCategories(){

        JsonPath jp =
        given()
                .log().all()
                .header("x-library-token", libraryTokenToLogin()).
        when()
                .get("/get_book_categories").prettyPeek()
                .jsonPath();

        List<BookCategory> allCategory = jp.getList("", BookCategory.class);
        allCategory.forEach(System.out::println);

        // use jp to get number 5 item from response

        BookCategory bCat5 = new BookCategory();

        bCat5 = jp.getObject("[4]", BookCategory.class);

        System.out.println("bCat5 = " + bCat5);

    }

}
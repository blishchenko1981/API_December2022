package Day03;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;


public class TestGithubRestAPI {

    // https://api.github.com/users/blishchenko1981

    @DisplayName("Get info from github")
    @Test

    public void gitHubTest(){

       Response resp =  get("https://api.github.com/users/blishchenko1981");
       resp.prettyPeek();

       given().pathParam("username", "blishchenko1981").

       when().get("https://api.github.com/users/{username}").


       then().
               assertThat().
               statusCode(is(200)).
               contentType(ContentType.JSON).
               header("Server", is("GitHub.com")).
               header("content-encoding", is("gzip")).
               body("login", is("blishchenko1981"))
       ;


    }

}

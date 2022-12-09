package Day01;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


public class RestAssuredIntro {

//http://54.236.150.168:8000/api/spartans
    //   http://54.236.150.168:8000/api/hello


    @DisplayName("Spartan/api/Hello Test")
    @Test
    public void testHello() {
        Response response = get("http://54.236.150.168:8000/api/hello");

        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println("response.body() = " + response.body().print());

        assertThat(response.statusCode(), is(equalTo(200)));

        String expectedPayload = "Hello from Sparta";
        String textPayload = response.prettyPrint();

        System.out.println("textPayload = " + textPayload);
        assertThat(textPayload, is(expectedPayload));

        // get header called contentType from response !!! 3 ways to do the same:

        //1
        System.out.println("response.getHeader(\"Content-Type\") = " + response.getHeader("Content-Type"));
        //2
        System.out.println("response.contentType() = " + response.contentType());
        //3
        System.out.println("response.getContentType() = " + response.getContentType());

        assertThat(response.contentType(), is("text/plain;charset=UTF-8"));

        assertThat(response.contentType(), startsWith("text"));

        // ENUM fro Content Type
        assertThat(response.contentType(), is(not(ContentType.JSON)));

        assertThat(response.contentType(), startsWith(ContentType.TEXT.toString()));

    }

}

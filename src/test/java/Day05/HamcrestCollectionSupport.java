package Day05;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class HamcrestCollectionSupport {

    @Test
    public void testList(){

        List<Integer> numList = Arrays.asList(4,5,6,67,54,322,44);

        assertThat(numList, hasSize(7));

        assertThat(numList, hasItem(44));

        assertThat(numList, hasItems(4,5));

        assertThat(numList, everyItem(greaterThan(3)));

        List<String> names = Arrays.asList("Vitalii", "Irina", "Artem", "Milana");

        assertThat(names, hasSize(4));

        assertThat(names, hasItems("Vitalii"));

        // check every name has "a"

        assertThat(names, everyItem(containsStringIgnoringCase("a")));

        // how to add OR , AND in matchers

        assertThat("Vitalii", allOf(startsWith("Vi"), containsString( "tal")));   //allOf == AND

        assertThat("Vitalii Blishchenko", anyOf( startsWith("Vit"), endsWith("lii")    ));  // anyOf == OR





    }


}








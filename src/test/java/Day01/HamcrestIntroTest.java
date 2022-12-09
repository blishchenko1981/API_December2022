package Day01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class HamcrestIntroTest {
    // start

    @DisplayName("Test 1+3=4")
    @Test
    public void test1(){

        assertThat(1+3 , is(4));
        assertThat(1+3, equalTo(4));

        //add some error message if it fails

     //   assertThat("Wrong Result!!!", 1+3, equalTo(5));

        assertThat(1+3, not(5));
        assertThat(1+3, is(not(5)));
        assertThat(1+3, is(lessThan(10)));
        assertThat(1+3, is(greaterThan(2)));
    }




}

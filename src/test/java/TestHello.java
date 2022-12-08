import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestHello {

@Test
    public void test() {

    int num = 4;
    Assertions.assertEquals(num, 3+1);
}


@Test
    public void test2(){

    String hi = "Hello";
    assertEquals(hi, "Hello");


}

}

package Day05;

import org.junit.jupiter.api.*;


//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@TestMethodOrder(MethodOrderer.Random.class)
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestOrderingInJunit5 {


    @Order(4)
    @Test
    public void testA(){

        System.out.println("test A");
    }


    @Order(3)
    @Test
    public void testC(){

        System.out.println("test C");
    }

    @Order(2)
    @Test
    public void testB(){

        System.out.println("test B");
    }

    @Order(1)
    @Test
    public void testD(){

        System.out.println("test D");
    }
}

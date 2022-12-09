package Day01;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Day 1 Hello Test")
public class TestHello {
// JUnit 5 annotations

    @BeforeAll
    public static void setUp(){

        System.out.println("@BeforeAll block");
    }

    @AfterAll
    public static void tearDown(){
        System.out.println("@AfterAll  is running");
    }

    @BeforeEach
    public void testSetting(){
        System.out.println("Before EACH");
    }

    @AfterEach
    public void testClosing(){
        System.out.println("After EACH");
    }
@DisplayName("Test num == 3+1")
@Test
    public void test() {

    System.out.println("Test1 is running");
    int num = 4;
    Assertions.assertEquals(num, 3+1);
}

@Disabled
@DisplayName("Test hi.equals(Hello)")
@Test
    public void test2(){

    System.out.println("Test2 is running");
    String hi = "Hello";
    assertEquals(hi, "Hello");


}

}

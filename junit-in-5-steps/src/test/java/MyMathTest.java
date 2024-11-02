import microteam.junit.MyMath;
import org.junit.*;

import static org.junit.Assert.*;

public class MyMathTest {

    MyMath myMath = new MyMath();

    @Before
    public void before() {
        System.out.println("Before");
    }

    @After
    public void after() {
        System.out.println("After");
    }

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Before Class");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("After Class");
    }


    @Test
    public void sum_with3numbers() {
        //MyMath myMath = new MyMath();
        System.out.println("Test1");
        int result = MyMath.sum(new int[] {1,2,3});
        assertEquals(6, result);
       // System.out.println(result);
    }

    @Test
    public void sum_with1number() {
        //MyMath myMath = new MyMath();
        System.out.println("Test2");
        int result = MyMath.sum(new int[] {3});
        assertEquals(3, result);
        // System.out.println(result);
    }

}

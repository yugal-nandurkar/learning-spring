import org.junit.Test;
import static org.junit.Assert.*;

import static org.junit.Assert.assertEquals;

public class AssertTest {

    @Test
    public void test(){
    assertEquals(1,1);
    assertTrue(true);
    //assertTrue(false);
    assertArrayEquals(new int[]{1},new int[]{1});
    }
}

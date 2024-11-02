package microteam.mockito.mockitoin5steps;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SomeBusinessStubTest {

    @Test
    public void testFindTheGreatestFromAllData() {
        SomeBusinessImpl business = new SomeBusinessImpl(new DataServiceStub());
        int result = business.findTheGreatestFromAllData();
        assertEquals(24, result);
        // fail("Not yet implemented");
    }
}

class DataServiceStub implements DataService {

    /**
     * @return
     */
    @Override
    public int[] retrieveAllData() {
        return new int[] {24, 6, 15};
    }
}

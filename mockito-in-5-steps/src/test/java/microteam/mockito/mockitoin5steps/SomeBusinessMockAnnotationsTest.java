package microteam.mockito.mockitoin5steps;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SomeBusinessMockAnnotationsTest {

    @Mock
    DataService dataServiceMock;

    @InjectMocks
    SomeBusinessImpl business;

    @Test
    public void testFindTheGreatestFromAllData() {
       //DataService dataServiceMock = mock(DataService.class);

        when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {24, 15, 3});

        //SomeBusinessImpl business = new SomeBusinessImpl(dataServiceMock);
        //int result = business.findTheGreatestFromAllData();
        assertEquals(24, business.findTheGreatestFromAllData());
        // fail("Not yet implemented");
    }

    @Test
    public void testFindTheGreatestFromAllData_NoValue() {
        //DataService dataServiceMock = mock(DataService.class);

        when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {});

        //SomeBusinessImpl business = new SomeBusinessImpl(dataServiceMock);
        //int result = business.findTheGreatestFromAllData();
        assertEquals(Integer.MIN_VALUE, business.findTheGreatestFromAllData());
        // fail("Not yet implemented");
    }

}
package microteam.services;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

//@Component//Step25.2
@Repository //Step 37.3
public class MySQLDataService implements DataService {

    @Override
    public int[] retrieveData() {
        //Step 23.3
        return new int[] {1, 2, 3, 4, 5, 6};
    }
}

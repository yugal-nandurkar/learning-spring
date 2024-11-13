package microteam.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

//@Component//Step25.3
@Repository //Step 37.2
@Primary
public class MongoDBDataService implements DataService {

    @Override
    public int[] retrieveData() {
        //Step 23.2
        return new int[] {11, 22, 33, 44, 55, 66};
    }
}

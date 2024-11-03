package microteam.springaspectop.venture;

import microteam.springaspectop.data.DAOB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Business {

    @Autowired
    private DAOB daob;
    public String calculateSomething() {
        //Business Logic
        return daob.retrieveSomething();
    }
}

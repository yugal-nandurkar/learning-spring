package microteam.springaspectop.venture;

import microteam.springaspectop.data.DAOE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Enterprise {

    @Autowired
    private DAOE daoe;
    public String calculateSomething() {
        //Enterprise Logic
        return daoe.retrieveSomething();
    }
}

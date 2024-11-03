package microteam.springaspectop.data;

import microteam.springaspectop.aspect.TrackTime;
import org.springframework.stereotype.Repository;

@Repository
public class DAOB {
    @TrackTime
    public String retrieveSomething() {
        return "DAOB";
    }
}

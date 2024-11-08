package webapp;

public class UserValidationService {

    public boolean validateUser(String username, String password) {
        if(password.equals("itstitst"))
            return true;
        return false;
    }
}

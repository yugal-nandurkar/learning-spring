package microteam.generic;

//Step 5
public class HelloBean {
    private String message;

    public HelloBean(String helloBean) {

        this.message = message; // Step 6
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "HelloBean{" +
                "message='" + message + '\'' +
                '}';
    }
}

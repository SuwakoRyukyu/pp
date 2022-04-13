import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class HelloWorld {
 
    private String message;
 
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
     
}
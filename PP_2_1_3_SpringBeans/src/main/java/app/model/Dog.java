package app.model;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class Dog extends Animal {

    @Override
    public String toString() {
        return "I am a dog";
    }
}

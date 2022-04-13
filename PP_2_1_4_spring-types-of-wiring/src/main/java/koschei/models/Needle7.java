package koschei.models;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Needle7 {

    @Autowired
    private Deth8 death;

    public void setDeath(Deth8 death) {
        this.death = death;
}

    @Override
    public String toString() {
        return ", смерть Кощея на игле :( " + "";
    }
}

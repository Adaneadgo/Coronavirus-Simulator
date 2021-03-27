package Population;

import Virus.IVirus;

public class Healthy extends Person {

    public Healthy()

    public Person vaccinate(){
        return new Vaccinated(this, 0);
    }

    
}

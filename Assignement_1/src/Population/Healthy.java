package Population;

import Virus.IVirus;

public class Healthy extends Person {

    public Person vaccinate(){
        return new Vaccinated(this, 0);
    }

    
}

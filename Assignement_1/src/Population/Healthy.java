package Population;

import Virus.IVirus;

public class Healthy extends Person {

    public Person vaccinate(){
        return null;
    }

    @Override
    public double contagionProbability() {
        return 0;
    }

    @Override
    public Person contagion(IVirus Virus) {
        return null;
    }
    
}

package Population;

import Virus.IVirus;

public class Vaccinated extends Person {

    private long vaccinationTime;
    


    @Override
    public double contagionProbability() {
        return 0;
    }

    @Override
    public Person contagion(IVirus Virus) {
        return null;
    }


    
}

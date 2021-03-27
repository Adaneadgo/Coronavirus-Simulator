package Population;

import Virus.IVirus;

public class Convalescent extends Person{


    private IVirus virus;

    @Override
    public double contagionProbability() {return 0.2;}

    @Override
    public Person contagion(IVirus Virus) {
        return null;
    }
    
}

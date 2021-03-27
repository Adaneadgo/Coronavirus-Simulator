package Population;

import Virus.IVirus;

public class Sick extends Person {
    
    private long contagiousTime;
    private IVirus virus;

    @Override
    public double contagionProbability() {
        return 0;
    }

    @Override
    public Person contagion(IVirus Virus) {
        return null;
    }
    
    public Person recover() {
        return null;
    }

    public boolean tryToDie(){
        return false;
    }
}

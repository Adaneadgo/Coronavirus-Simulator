package Population;

import Virus.IVirus;

public class Vaccinated extends Person {

    private long vaccinationTime;

    public Vaccinated(Person p, long t){
        super(p);
        vaccinationTime = t;
    }
    
    @Override
    public double contagionProbability() {
        long t = vaccinationTime;

        if( t < 21)
        return Math.min(1, 0.56 + 0.15* Math.sqrt(21 - t));

        else
        return Math.max(0.05, 1.05/(t - 14));
    }



    
}

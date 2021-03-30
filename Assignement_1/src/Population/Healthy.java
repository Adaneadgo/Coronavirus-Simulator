package Population;

import Country.Settlement;
import Location.Location;

public class Healthy extends Person {

    //Ctors
    public Healthy(){}
    public Healthy(int age, Location location, Settlement settlement){ super(age, location, settlement);}
     
    // toString & equals
    @Override
    public String toString() { return super.toString();}
    public boolean equals(Sick other) { return super.equals(other); }

    // Methods
    @Override
    public double contagionProbability() { return 1; }
    public Person vaccinate(){ return new Vaccinated(this);}
    
}

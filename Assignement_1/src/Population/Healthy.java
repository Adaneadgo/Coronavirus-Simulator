package Population;

import Country.Settlement;
import Location.Location;

public class Healthy extends Person {

    /**
     * represent healthy person
     */

    private final static double m_probability = 1;

    //Ctors
    public Healthy(){}
    public Healthy(Person person) {super(person);}
    public Healthy(int age, Location location, Settlement settlement){ super(age, location, settlement);}
     
    // toString & equals
    @Override
    public String toString() { return "Condition: Healthy, " + super.toString();}
    public boolean equals(Sick other) { return super.equals(other); }

    // Methods
    @Override
    public double contagionProbability() { return m_probability; }
    public Person vaccinate(){ return new Vaccinated(this);}
    
}

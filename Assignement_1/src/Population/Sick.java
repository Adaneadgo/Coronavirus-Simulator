package Population;

import Simulation.Clock;
import Virus.IVirus;

public class Sick extends Person {
    
    private long contagiousTime;
    private IVirus virus;
    private static final double probability = 1;

    //Ctors
    public Sick(){}
    public Sick(Person person, IVirus virus){ super(person); this.virus = virus; contagiousTime = Clock.now(); }

    //Getters
    public IVirus getVirus() { return virus; }
    public long getContagiousTime() { return contagiousTime; }
    
    // tostring & equals
    @Override
    public String toString() { return "Condition: Sick, " + super.toString() + ", contagiousTime: " + contagiousTime + ", virus: " + virus;}
    public boolean equals(Sick other) { return super.equals(other) && virus == other.virus && contagiousTime == other.contagiousTime;}

    //Methods
    @Override
    public double contagionProbability() {return probability;}
    public Person recover() { return new Vaccinated(this); }
    public boolean tryToDie(){ return virus.tryToKill(this); }

    //Execption 
    public Sick contagion(IVirus virus){ throw new RuntimeException("try contagion sick person!"); }


}

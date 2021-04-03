package Population;

import Simulation.Clock;
import Virus.IVirus;

public class Sick extends Person {
    
    private long m_contagiousTime;
    private IVirus m_virus;
    private static double m_probability = 1;

    //Ctors
    public Sick(){}
    public Sick(Person person, IVirus virus){ super(person); m_virus = virus; m_contagiousTime = Clock.now(); }

    //Getters
    public IVirus getVirus() { return m_virus; }
    public long getContagiousTime() { return m_contagiousTime; }
    
    // tostring & equals
    @Override
    public String toString() { return "Sick, " + super.toString() + ", contagiousTime: " + m_contagiousTime + ", virus: " + m_virus ;}
    public boolean equals(Sick other) { return super.equals(other) && m_virus == other.m_virus && m_contagiousTime == other.m_contagiousTime;}

    //Methods
    @Override
    public double contagionProbability() {return m_probability;}
    public Person recover() { return new Vaccinated(this); }
    public boolean tryToDie(){ return m_virus.tryToKill(this); }
}

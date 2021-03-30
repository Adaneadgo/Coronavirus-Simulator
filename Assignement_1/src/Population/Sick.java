package Population;

import Virus.IVirus;

public class Sick extends Person {
    
    private long m_contagiousTime;
    private IVirus m_virus;
    private static double m_probability = 1;

    //Ctors
    public Sick(){}
    public Sick(Person person, IVirus virus){ super(person); m_virus = virus; }

    //Getters
    public IVirus get_Virus() { return m_virus; }
    public long get_ContagiousTime() { return m_contagiousTime; }
    
    // tostring & equals
    @Override
    public String toString() { return super.toString() + ", contagiousTime: " + m_contagiousTime + ", virus: " + m_virus ;}
    public boolean equals(Sick other) { return super.equals(other) && m_virus == other.m_virus && m_contagiousTime == other.m_contagiousTime;}

    //Methods
    @Override
    public double contagionProbability() {return m_probability;}
    public Person recover() { return new Vaccinated(this); }
    public boolean tryToDie(){return false; }
}

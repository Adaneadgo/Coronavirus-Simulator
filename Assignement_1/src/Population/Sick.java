package Population;

import Virus.IVirus;

public class Sick extends Person {
    
    private long m_contagiousTime;
    private IVirus m_virus;


    public Sick(){}
    public Sick(Person person, IVirus virus){ super(person); m_virus = virus; }

    public IVirus get_Virus() { return m_virus; }
    public long get_ContagiousTime() { return m_contagiousTime; }
    
    public boolean equals(Sick other) { return super.equals(other) && m_virus == other.m_virus && m_contagiousTime == other.m_contagiousTime;}
    
    @Override
    public String toString() { return super.toString() + ", contagiousTime: " + m_contagiousTime + ", virus: " + m_virus ;}

    @Override
    public double contagionProbability() {return 1;}
    
    public Person recover() { return new Vaccinated(this); }
    public boolean tryToDie(){return false; }
}

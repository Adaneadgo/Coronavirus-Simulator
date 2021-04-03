package Population;

import Simulation.Clock;

public class Vaccinated extends Person {

    private long m_vaccinationTime;

    //Ctors
    public Vaccinated(){}
    public Vaccinated(Person person){ super(person); m_vaccinationTime = Clock.now(); }
    
    //Getters
    public long getVaccinationTime() { return m_vaccinationTime; }

    //tostring & equals
    @Override
    public String toString() { return "Condition: Vaccinated, " + super.toString() + ", vaccinationTime: " + m_vaccinationTime;}
    public boolean equals(Vaccinated other) { return super.equals(other) &&  m_vaccinationTime == other.m_vaccinationTime;}


    //Methods
    @Override
    public double contagionProbability() {
        
        long t = Clock.now() - m_vaccinationTime;

        if( t < 21)
            return Math.min(1, 0.56 + 0.15* Math.sqrt(21 - t));
        else
            return Math.max(0.05, 1.05/(t - 14));
    }
    



    
}

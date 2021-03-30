package Population;

import Virus.IVirus;

public class Convalescent extends Person{

    private IVirus m_virus;
    private static double m_probability = 0.2;

    //Ctors
    public Convalescent(){}
    public Convalescent(IVirus virus){ m_virus = virus;}

    //Getters
    public IVirus get_Virus() { return m_virus; }

    // tostring & equals
    @Override
    public String toString() { return super.toString() + ", Virus: " + m_virus;}
    public boolean equals(Convalescent other) { return super.equals(other) && m_virus == other.m_virus; }

    //Methods
    @Override
    public double contagionProbability() {return m_probability;}
 
    
}

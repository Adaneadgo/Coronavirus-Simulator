package Population;

import Virus.IVirus;

public class Convalescent extends Person{

    private IVirus m_virus;

    public Convalescent(){}
    public Convalescent(IVirus virus){ m_virus = virus;}

    public IVirus get_Virus() { return m_virus; }

    @Override
    public String toString() { return super.toString() + ", Virus: " + m_virus;}
  
    public boolean equals(Convalescent other) { return super.equals(other) && m_virus == other.m_virus; }

    @Override
    public double contagionProbability() {return 0.2;}
 
    
}

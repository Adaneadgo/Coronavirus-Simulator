//Adane Adgo 315721969
//Elie Bracha 204795900
package Population;

import Virus.IVirus;

public class Convalescent extends Person{

    /**
     * represent ex-sick person
     */

    private IVirus m_virus;
    private final static double m_probability = 0.2;

    //Ctors
    public Convalescent(){}
    public Convalescent(Sick sick){
        super(sick);
        m_virus = sick.getVirus();
    }
    public Convalescent(IVirus virus){ m_virus = virus;}

    //Getters
    public IVirus getVirus() { return m_virus; }

    // tostring & equals
    @Override
    public String toString() { return "Condition: Convalescent, " + super.toString() + ", Virus: " + m_virus;}
    public boolean equals(Convalescent other) { return super.equals(other) && m_virus == other.m_virus; }

    //Methods
    @Override
    public double contagionProbability() {return m_probability;}
 
    
}

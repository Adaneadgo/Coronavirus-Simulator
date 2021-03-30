package Virus;
import Population.Person;

public class BritishVariant implements IVirus{

    private static final double m_contProb = 0.7;

    private static final double m_dieProbUpTo18 = 0.01;
    private static final double m_dieProbOver18 = 0.1;

    @Override
    public String toString() { return "British Variant"; }


    @Override
    public double contagionProbability(Person person) { return m_contProb * person.contagionProbability();}
    
    @Override
    public boolean tryToContagion(Person person1, Person person2) {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public boolean tryToKill(Person person) {
        // TODO Auto-generated method stub
        return false;
    }


}

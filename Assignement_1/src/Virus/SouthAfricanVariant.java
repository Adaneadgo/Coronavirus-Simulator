package Virus;
import Population.Person;

public class SouthAfricanVariant implements IVirus{

    
    private static final double m_contProbUpTo18 = 0.2;
    private static final double m_contProbOver18 = 0.7;

    private static final double m_dieProbUpTo18 = 0.01;
    private static final double m_dieProbOver18 = 0.1;

    @Override
    public String toString() {return "South African Variant";}


    @Override
    public double contagionProbability(Person person) {

        int personAge = person.get_Age();
        double personContProb = person.contagionProbability();

        if(personAge <= 18)
            return m_contProbUpTo18 * personContProb;

        else
            return m_contProbOver18 * personContProb;
    }

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

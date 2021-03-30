package Virus;


import javax.lang.model.util.ElementScanner6;

import Population.Person;


public class ChineseVariant implements IVirus{

    private static final double m_contProbUpTo18 = 0.2;
    private static final double m_contProb18to55 = 0.5;
    private static final double m_contProbOver55 = 0.7;

    private static final double m_dieProbUpTo18 = 0.01;
    private static final double m_dieProb18to55 = 0.05;
    private static final double m_dieProbOver55 = 0.1;

    @Override
    public double contagionProbability(Person person) {

        int personAge = person.get_Age();
        double personContProb = person.contagionProbability();

        if (personAge < 18)
            return m_contProbUpTo18 * personContProb;

        else if (personAge <= 55)
            return m_contProb18to55 * personContProb;

        else
            return m_contProbOver55 * personContProb;
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

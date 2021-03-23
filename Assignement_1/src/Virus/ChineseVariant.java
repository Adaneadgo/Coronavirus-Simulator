package Virus;
import Population.Person;

public class ChineseVariant implements IVirus{
    @Override
    public double contagionProbability(Person p) {
        return 0;
    }

    @Override
    public boolean tryToContagion(Person p1, Person p2) {
        return false;
    }

    @Override
    public boolean tryToKill(Person p) {
        return false;
    }
}

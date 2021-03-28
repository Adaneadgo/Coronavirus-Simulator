package Virus;


import Population.Person;

public class ChineseVariant implements IVirus{


    @Override
    public double contagionProbability(Person p) {

        int age = p.get_Age();
        
        if (age < 18)
        return 0.2;

        else if (age >= 18 && age <= 55)
        return 0.5;

        else
        return 0.7;
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

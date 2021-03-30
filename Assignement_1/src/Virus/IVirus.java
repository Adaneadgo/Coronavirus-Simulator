package Virus;

import Population.Person;

public interface IVirus {

    public double contagionProbability(Person person);
    public boolean tryToContagion(Person person1, Person person2);
    public boolean tryToKill(Person person);
}

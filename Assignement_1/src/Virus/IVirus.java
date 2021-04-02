package Virus;


import Population.Person;
import Population.Sick;




public interface IVirus {

    public double contagionProbability(Person person);
    public boolean tryToContagion(Person person1, Person person2);
    public boolean tryToKill(Person sick);

    public double variantContagionProbability(Person person);
    public double variantDeathProbability(Sick sick);

}

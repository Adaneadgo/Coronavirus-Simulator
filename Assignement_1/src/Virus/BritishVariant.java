package Virus;

import java.util.Random;

import Population.Person;
import Population.Sick;
import Simulation.Clock;


public class BritishVariant implements IVirus{

    @Override
    public String toString() { return "British Variant"; }

    @Override
    public double contagionProbability(Person person) { return variantContagionProbability(person) * person.contagionProbability();}
    @Override
    public boolean tryToContagion(Person person1, Person person2) {
       
        return false;
    }
    @Override
    public boolean tryToKill(Sick sick) {

        long t = Clock.now() - sick.get_ContagiousTime();
        double q = variantDeathProbability(sick);
        double p = Math.max(0, q - 0.01 * q * Math.pow(t, 2));
        Random rand = new Random();

        return rand.nextInt(100) < p * 100;
    }

    @Override
    public double variantContagionProbability(Person person) {return 0.7;}
    @Override
    public double variantDeathProbability(Sick sick) {

        int age = sick.get_Age();

        if(age < 18)
            return 0.01;
        else
            return 0.1;
    }

    

}

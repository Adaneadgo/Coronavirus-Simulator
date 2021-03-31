package Virus;

import java.util.Random;

import Population.Person;
import Population.Sick;
import Simulation.Clock;


public class BritishVariant implements IVirus{

    @Override
    public String toString() { return "British Variant"; }

    //methods
    @Override
    public double contagionProbability(Person person) { return variantContagionProbability(person) * person.contagionProbability();}
    @Override
    public boolean tryToContagion(Person person1, Person person2) {

        double d = Math.sqrt(Math.pow(person2.getPoint().getX() - person1.getPoint().getX() , 2) + Math.pow(person2.getPoint().getY() - person1.getPoint().getY(), 2));
        double p = Math.min(1, 0.14 * Math.exp(2 - 0.25 * d));
        Random rand = new Random();

        return rand.nextInt(100) < p * 100;
    }
    @Override
    public boolean tryToKill(Sick sick) {

        long t = Clock.now() - sick.getContagiousTime();
        double q = variantDeathProbability(sick);
        double p = Math.max(0, q - 0.01 * q * Math.pow(t, 2));
        Random rand = new Random();

        return rand.nextInt(100) < p * 100;
    }

    //auxiliary
    @Override
    public double variantContagionProbability(Person person) {return 0.7;}
    @Override
    public double variantDeathProbability(Sick sick) {

        int age = sick.getAge();

        if(age < 18)
            return 0.01;
        else
            return 0.1;
    }

    

}

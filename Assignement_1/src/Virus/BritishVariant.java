 package Virus;

import java.util.Random;

import Population.Person;
import Population.Sick;
import Simulation.Clock;


public class BritishVariant implements IVirus{

    @Override
    public String toString() { return "British Variant"; }

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

package Virus;

import Population.Person;
import Population.Sick;


public class SouthAfricanVariant implements IVirus{

    @Override
    public String toString() {return "South African Variant";}

    //auxiliary
    @Override
    public double variantContagionProbability(Person person) {

        int age = person.getAge();

        if(age < 18)
            return 0.6;
        else
            return 0.5;
    }
    @Override
    public double variantDeathProbability(Sick sick) {

        int age = sick.getAge();

        if(age < 18)
            return 0.05;
        else
            return 0.08;
    }

}

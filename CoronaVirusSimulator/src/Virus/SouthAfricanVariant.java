//Adane Adgo 315721969
//Elie Bracha 204795900

package Virus;

import Population.Person;
import Population.Sick;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class SouthAfricanVariant implements IVirus{

    private static SouthAfricanVariant instance;

    public static SouthAfricanVariant getInstance(){
        if(instance == null)
            instance =new SouthAfricanVariant();
        return instance;
    }

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

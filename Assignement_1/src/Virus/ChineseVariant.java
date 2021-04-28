package Virus;


import Population.Person;
import Population.Sick;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ChineseVariant implements IVirus{

    private List<IVirus> mutations;

    public ChineseVariant(){
        mutations = new ArrayList<IVirus>();
        mutations.add(this);
    }

    @Override
    public String toString() { return "Chinese Variant"; }

    //auxiliary
    @Override
    public double variantContagionProbability(Person person) {

        int age = person.getAge();

        if(age < 18)
            return 0.2;

        else if (age <= 55)
            return 0.5;
        
        else 
            return 0.7;
    }
    @Override
    public double variantDeathProbability(Sick sick) {
        
        int age = sick.getAge();

        if(age < 18)
            return 0.01;

        else if (age <= 55)
            return 0.05;
        
        else 
            return 0.1;
    }

    @Override
    public IVirus mutant() {
        Random rand = new Random();
        int index = rand.nextInt(mutations.size());
        return mutations.get(index);
    }


}

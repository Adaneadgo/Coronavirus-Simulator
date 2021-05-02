//Adane Adgo 315721969
//Elie Bracha 204795900
package Virus;


import Population.Person;
import Population.Sick;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ChineseVariant implements IVirus{

    private static ChineseVariant instance;
    private List<IVirus> mutations;


    private ChineseVariant(){
        mutations = new ArrayList<IVirus>();
        mutations.add(this);
    }

    public static ChineseVariant getInstance(){
        if(instance == null)
            instance  =new ChineseVariant();
        return instance;
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

    public void addMutation(IVirus virus) {
        if(this.containMutation(virus))
            return;
        this.mutations.add(virus);
    }

    public void removeMutation(IVirus virus) {
        if(this.mutations.contains(virus))
            mutations.remove(virus);
    }

    public boolean containMutation(IVirus virus){
        return mutations.contains(virus);
    }

    @Override
    public IVirus mutant() {
        if(mutations.size() == 0)
            return null;

        Random rand = new Random();

        if(mutations.size() == 1)
            return mutations.get(0);

        int index = rand.nextInt(mutations.size() - 1);
        return mutations.get(index);
    }



}

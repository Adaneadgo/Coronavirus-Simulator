package Virus;

import Population.Person;
import Population.Sick;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class SouthAfricanVariant implements IVirus{

    private static SouthAfricanVariant instance;
    private List<IVirus> mutations;


    private SouthAfricanVariant(){
        mutations = new ArrayList<IVirus>();
        mutations.add(this);
    }

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


    public void addMutation(IVirus virus) {
        if(this.containMutation(virus))
            return;
        this.mutations.add(virus);
    }

    public void removeMutation(IVirus virus) {
        if (this.mutations.contains(virus))
            mutations.remove(virus);
    }

    public boolean containMutation(IVirus virus){
        return mutations.contains(virus);
    }

    @Override
    public IVirus mutant() {
        if(mutations.size() == 0)
            return null;
        else {
            Random rand = new Random();
            int index = rand.nextInt(mutations.size());
            return mutations.get(index);
        }
    }

}

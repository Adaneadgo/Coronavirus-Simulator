 package Virus;


import Population.Person;
import Population.Sick;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


 public class BritishVariant implements IVirus{

     private static BritishVariant instance;
     private List<IVirus> mutations;


     private BritishVariant(){
         mutations = new ArrayList<IVirus>();
         mutations.add(this);
     }

     public static BritishVariant getInstance(){
         if(instance == null)
             instance  = new BritishVariant();
         return instance;
     }


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

         Random rand = new Random();
         int index = rand.nextInt(mutations.size());
         return mutations.get(index);
     }

    
}

//Adane Adgo 315721969
//Elie Bracha 204795900
package Virus;


import Population.Person;
import Population.Sick;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


 public class BritishVariant implements IVirus{

     private static BritishVariant instance;



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


}

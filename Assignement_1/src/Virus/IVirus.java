//Adane Adgo 315721969
//Elie Bracha 204795900
package Virus;


import java.util.List;
import java.util.Random;

import Population.Person;
import Population.Sick;
import Simulation.Clock;


public interface IVirus {

    /**
     virus interface for the use of  ChineseVariant, BritishVariant, SouthAfricanVariant
     */



    default public double contagionProbability(Person person)
    {
        /**
        A method that calculates the probability that the transferred person will be infected. The probability of the virus is multiplied by the probability coefficient of
        The person being transferred.
         */
        return variantContagionProbability(person) * person.contagionProbability();
    }
    
    default public boolean tryToContagion(Person person1, Person person2) {

        /*
        person1 is Sick and the method checks their health condition, and if the other is healthy then he is infected.
         */
        if(this.mutant() == null)
            return false;

        if ( (person1 instanceof Sick) && !(person2 instanceof Sick)) {

             if (Clock.daysPass(((Sick) person1).getContagiousTime()) < 5)
                 return false;

            double d = Math.sqrt(Math.pow(person2.getPoint().getX() - person1.getPoint().getX() , 2) + Math.pow(person2.getPoint().getY() - person1.getPoint().getY(), 2));
            double p = Math.min(1, 0.14 * Math.exp(2 - 0.25 * d));
            Random rand = new Random();

            return rand.nextInt(100) < p * 100;
        }

        else {
            return false;
        }


    }
   
    default public boolean tryToKill(Sick sick) {
        /**
         A method that calculates the probability that the person being transferred
         will die from the disease, according to which returns whether the person was killed or not.
         */
        long t = Clock.daysPass(sick.getContagiousTime());
        double p = variantDeathProbability(sick);
        double P = Math.max(0, p - 0.01 * p * Math.pow(t, 2));
        Random rand = new Random();

        return rand.nextInt(100) < P * 100;
    }

    // auxiliary
    public double variantContagionProbability(Person person);
    public double variantDeathProbability(Sick sick);

    public IVirus mutant();
    public void addMutation(IVirus virus);
    public void removeMutation(IVirus virus);
    public boolean containMutation(IVirus virus);

}

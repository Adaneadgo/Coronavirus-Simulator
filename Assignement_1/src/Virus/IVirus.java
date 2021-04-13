package Virus;


import java.util.Random;

import Population.Person;
import Population.Sick;
import Simulation.Clock;




public interface IVirus {

 
    default public double contagionProbability(Person person) {return variantContagionProbability(person) * person.contagionProbability();}
    
    default public boolean tryToContagion(Person person1, Person person2) {

        if (person1 instanceof Sick) {
            double d = Math.sqrt(Math.pow(person2.getPoint().getX() - person1.getPoint().getX() , 2) + Math.pow(person2.getPoint().getY() - person1.getPoint().getY(), 2));
            double p = Math.min(1, 0.14 * Math.exp(2 - 0.25 * d));
            Random rand = new Random();

            return rand.nextInt(100) < p * 100;
        }

        else
            return false;


    }
   
    default public boolean tryToKill(Sick sick) {
        
        long t = Clock.now() - sick.getContagiousTime();
        double p = variantDeathProbability(sick);
        double P = Math.max(0, p - 0.01 * p * Math.pow(t, 2));
        Random rand = new Random();

        return rand.nextInt(100) < P * 100;
    }


    public double variantContagionProbability(Person person);
    public double variantDeathProbability(Sick sick);

}

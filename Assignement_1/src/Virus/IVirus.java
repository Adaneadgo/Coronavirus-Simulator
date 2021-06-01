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

    public double contagionProbability(Person person);
    public boolean tryToContagion(Person person1, Person person2);
    public boolean tryToKill(Sick sick);

    // auxiliary
    public double variantContagionProbability(Person person);
    public double variantDeathProbability(Sick sick);

    public IVirus getRandomMutation();
    public void addMutation(IVirus virus);
    public void removeMutation(IVirus virus);
    public boolean containMutation(IVirus virus);

}

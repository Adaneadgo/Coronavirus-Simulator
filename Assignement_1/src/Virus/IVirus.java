package Virus;

import Location.*;
import Virus.*;
import Country.*;
import Population.*;
import IO.*;


public interface IVirus {

    public double contagionProbability(Person person);
    public boolean tryToContagion(Person person1, Person person2);
    public boolean tryToKill(Sick sick);

    public double variantContagionProbability(Person person);
    public double variantDeathProbability(Sick sick);

}

package Simulation;

import Location.*;
import Virus.*;

import java.util.Random;

import Country.*;
import Population.*;
import IO.*;


public class Main {
    public static void main(String[] args) throws Exception {

        SimulationFile SF = new SimulationFile("C:\\Users\\adane\\Desktop\\myCodesGit\\Collage\\AdvancedOOP\\Assigment1_OOP_Java\\Assignement_1\\src\\Simulation\\Test.txt");

        // Step 1
        SF.loadSimulation();
        //Step 2
        SF.initialSimulation();
        //

        System.out.println(SF);





    }


}

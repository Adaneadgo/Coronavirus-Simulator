package Simulation;

import IO.SimulationFile;



public class Main {
    public static void main(String[] args) throws Exception {

        SimulationFile mySimulation = new SimulationFile("inputFile.txt");
        mySimulation.loadSimulation(); // Step 1
        mySimulation.initialSimulation(); // Step 2
        mySimulation.simulateSimulation(); // Step 3
        System.out.println(mySimulation); 

    

    }


}

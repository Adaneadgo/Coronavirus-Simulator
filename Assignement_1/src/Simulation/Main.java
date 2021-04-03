package Simulation;

import IO.SimulationFile;


public class Main {
    public static void main(String[] args) throws Exception {

        SimulationFile mySimulation = new SimulationFile("inputFile.txt");
        mySimulation.loadSimulation();
        mySimulation.initialSimulation();
        System.out.println(mySimulation);

    }


}

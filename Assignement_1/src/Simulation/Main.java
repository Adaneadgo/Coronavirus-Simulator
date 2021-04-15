package Simulation;

import IO.SimulationFile;

import java.awt.*;
import java.io.File;


public class Main {
    public static void main(String[] args) throws Exception {
        File mSimulation = loadFileFunc();
        SimulationFile mySimulation = new SimulationFile(mSimulation);
        mySimulation.loadSimulation(); // Step 1
        mySimulation.initialSimulation(); // Step 2
        for(int i = 0; i< 5;i++)
            mySimulation.simulateSimulation();
        //mySimulation.simulateSimulation(); // Step 3
        System.out.println(mySimulation); 
        System.out.println(Clock.now());
        
    

    }
    private static File loadFileFunc() {
        FileDialog fd = new FileDialog((Frame) null, "Please choose a file:", FileDialog.LOAD);
        fd.setVisible(true);

        if (fd.getFile() == null)
            return null;
        File f = new File(fd.getDirectory(), fd.getFile());
        System.out.println(f.getPath());
        return f;
    }



}

//Adane Adgo 315721969
//Elie Bracha 204795900

package Simulation;

import Country.RamzorColor;
import IO.SimulationFile;
import IO.StatisticsFile;
import UI.MainWin;

import java.awt.*;
import java.io.File;


public class Main {
    public static void main(String[] args) throws Exception {

        File file = loadFileFunc();
        SimulationFile mySimulation = new SimulationFile(file);

        mySimulation.loadSimulation();    // Step 1
        mySimulation.initialSimulation(); // Step 2
        for(int i = 0; i< 1000 ;i++)      // step 3
            mySimulation.simulateSimulation();

        System.out.println(mySimulation);

        StatisticsFile s = new StatisticsFile(mySimulation);
        s.updateCSV();

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

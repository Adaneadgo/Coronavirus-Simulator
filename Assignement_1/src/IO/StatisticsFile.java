package IO;

import Country.Settlement;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class StatisticsFile {

    SimulationFile simulationFile;

    public StatisticsFile(SimulationFile simulationFile) {
        this.simulationFile = simulationFile;
    }

    public void CreatCsvFile(String path) throws FileNotFoundException {

        Settlement [] settlements = simulationFile.getM_map().getM_settlements();
        PrintWriter pw = new PrintWriter(path + ".csv");

        for(Settlement settlement: settlements) {

            StringBuilder row = new StringBuilder();

            for(String item: settlement.getStatistics()){
                row.append(item +",");
            }
            row.append("\n");
            pw.write(row.toString());

        }

        pw.close();

    }

}







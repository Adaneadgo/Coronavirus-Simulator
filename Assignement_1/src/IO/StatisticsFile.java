package IO;

import Country.Settlement;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class StatisticsFile {

    private final SimulationFile simulationFile;
    PrintWriter pw;

    public StatisticsFile(SimulationFile simulationFile){
        this.simulationFile = simulationFile;
    }

    public void updateCSV() throws FileNotFoundException {

        Settlement [] settlements = simulationFile.getM_map().getM_settlements();
        pw = new PrintWriter("statistics.csv");
        StringBuilder data = new StringBuilder();

        data.append("Name,Number of People,Color");
        for(Settlement settlement: settlements)
            data.append(settlement.getStatisticsCSV()+ "\n");

        pw.write(data.toString());
        pw.close();


    }
}

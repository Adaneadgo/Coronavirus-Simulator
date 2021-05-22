//Adane Adgo 315721969
//Elie Bracha 204795900
package IO;

import Country.Map;
import Country.Settlement;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class StatisticsFile {
    /**
     * used by statistic window
     * create and export the csv file!
     */


    private final SimulationFile simulationFile;

    public StatisticsFile(SimulationFile simulationFile) {
        this.simulationFile = simulationFile;
    }

    public void CreatCsvFile(String path) throws FileNotFoundException {

        /**
         *create and export the csv file!
         */

        Settlement [] settlements = simulationFile.getMap().getSettlements();
        PrintWriter pw = new PrintWriter(path + ".csv");

        String columns = "Name,Type,Color,Area,Occupancy,Number of People,Coefficient," +
                "Percentage of infected,Number of vaccines,number of deaths\n";
        pw.write(columns);

        for(Settlement settlement: settlements) {

            StringBuilder row = new StringBuilder();

            for(String item: settlement.getCsvStats()){
                row.append(item).append(",");
            }
            row.append("\n");
            pw.write(row.toString());

        }

        pw.close();

    }

}







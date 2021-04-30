package IO;

import Country.Map;
import Country.Settlement;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class StatisticsFile {

    private final Map map;

    public StatisticsFile(Map map) {
        this.map = map;
    }

    public void CreatCsvFile(String path) throws FileNotFoundException {

        Settlement [] settlements = map.getSettlements();
        PrintWriter pw = new PrintWriter(path + ".csv");

        String columns = "Name,Type,Color,Area,Area per Person,Amount of vaccines,Coefficient," +
                "Number of People,Number of Sicks People,Percentage of infected,number of deaths\n";
        pw.write(columns);

        for(Settlement settlement: settlements) {

            StringBuilder row = new StringBuilder();

            for(String item: settlement.getStatistics()){
                row.append(item).append(",");
            }
            row.append("\n");
            pw.write(row.toString());

        }

        pw.close();

    }

}







//Adane Adgo 315721969
//Elie Bracha 204795900
package IO;

import Country.Map;
import Country.Settlement;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.io.*;

public class StatisticsFile {
    /**
     * used by statistic window
     * create and export the csv file!
     */


    public StatisticsFile(JTable table, String path) throws IOException {


        TableModel model = table.getModel();
        int rows = table.getModel().getRowCount();
        int cols = table.getModel().getColumnCount();
        FileWriter csv = new FileWriter(new File(path + ".csv"));

        for (int i = 0; i < cols; i++)
            csv.write(model.getColumnName(i) + ",");
        csv.write("\n");


        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                csv.write(model.getValueAt(i, j).toString() + ",");
            }
            csv.write("\n");
        }
        csv.close();
        
    }


}







//Adane Adgo 315721969
//Elie Bracha 204795900
package UI;

import Country.Map;
import Country.Settlement;

import javax.swing.*;
import javax.swing.table.*;
import java.util.ArrayList;
import java.util.List;

public class StatisticsTable extends JTable {
    /**
     * Statistic Table for Statistic Window
     */

    private final TableRowSorter<TableModel> rowSorter;

    private static class Model extends AbstractTableModel{
        private final Settlement [] settlements;
        private final String[] columnNames = {"Name","Type","Color","Area","Occupancy","Number of People"
                ,"Coefficient","Percentage of infected","Number of vaccines","number of deaths"};

        public Model(Settlement[] data){ this.settlements = data;}
        @Override
        public int getRowCount() { return settlements.length; }
        @Override
        public int getColumnCount() { return 10; }

        @Override
        public String getColumnName(int column) { return columnNames[column]; }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Settlement settlement = settlements[rowIndex];
            switch (columnIndex){
                case 0: return settlement.getName();
                case 1: return settlement.getType();
                case 2: return settlement.getRamzorColor().toString();
                case 3: return settlement.getArea();
                case 4: return settlement.getOccupancy();
                case 5: return settlement.getTotalNumber();
                case 6: return settlement.getCoefficient();
                case 7: return settlement.getSicksRatio();
                case 8: return settlement.getVaccinesNumber();
                case 9: return settlement.getDeathsNumber();

            }
            return null;
        }
    }

    StatisticsTable(Settlement[] settlements) {
        this.setModel(new Model(settlements));

        rowSorter = new TableRowSorter<>(this.getModel());
        this.setRowSorter(rowSorter);
        this.getTableHeader().setReorderingAllowed(false);


    }


    public void rowSortByColumn(String text, TableColumn col){
        /**
         the table sorter by regex
         */
        if(col == null){
            rowSorter.setRowFilter(RowFilter.regexFilter(""));
            return;
        }
        assert col != null;
        rowSorter.setRowFilter(RowFilter.regexFilter(text,0,col.getModelIndex()));
    }



    @Override
    public boolean isCellEditable(int row, int column){
        return false;
    }
}

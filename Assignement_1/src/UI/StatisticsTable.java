package UI;

import Country.Map;
import Country.Settlement;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.util.ArrayList;
import java.util.List;

public class StatisticsTable extends JTable {
    /**
     * Statistic Table for Statistic Window
     */
    private final Map map;
    private final TableRowSorter<TableModel> rowSorter;

    StatisticsTable(Map map, String[][] Data, String[] col) {
        super(Data,col);
        this.map = map;


        rowSorter = new TableRowSorter<>(this.getModel());
        this.setRowSorter(rowSorter);
        this.getTableHeader().setReorderingAllowed(false);


    }

    public void reloadData(){
        /**
         * Update the table data
         */

        Settlement[] settlements = this.map.getSettlements();

        List<String[]> newData = new ArrayList<String[]>();
        for(Settlement settlement: settlements){
            newData.add(settlement.getStatistics());
        }

        for(int i =0; i<this.getRowCount(); i++){
            String[] newRow = newData.get(i);
            for(int j = 0; j<this.getColumnCount(); j++){
                this.getModel().setValueAt(newRow[j],i,j);
            }
        }
        this.repaint();
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

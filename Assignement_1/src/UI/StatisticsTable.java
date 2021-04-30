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
        rowSorter.setRowFilter(RowFilter.regexFilter(text));
    }



    @Override
    public boolean isCellEditable(int row, int column){
        return false;
    }
}

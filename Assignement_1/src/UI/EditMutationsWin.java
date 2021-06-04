//Adane Adgo 315721969
//Elie Bracha 204795900
package UI;

import Virus.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EditMutationsWin extends JDialog {
    /**
     * All variants mutations possibilities
     */

    private final RowedTableScroll table;

    //Ctor
    EditMutationsWin(JFrame parent) {
        super(parent, "Edit Mutations",true);

        this.table = TableMaker();

        this.getContentPane().add(this.table);
        this.pack();
        this.setVisible(true);

    }

    private RowedTableScroll TableMaker() {
        /**
         * Created the table that we allowed us to edit the variants
         */

        VirusManager virusManager = VirusManager.getInstance();
        String[] variantsNames = {"ChineseVariant", "BritishVariant", "SouthAfricanVariant"};

        DefaultTableModel model = new DefaultTableModel(variantsNames, 0) {
            @Override
            public Class<Boolean> getColumnClass(int columnIndex) {
                return Boolean.class;
            }
        };

        for(Boolean[] row: virusManager.getTable())
            model.addRow(row);


        JTable table = new JTable(model);
        table.addMouseListener(new MouseListener() {

            private void myMouseAction(){
                int i = table.getSelectedRow();
                int j = table.getSelectedColumn();

                if((Boolean) table.getValueAt(i,j))
                    virusManager.addMutation(i,j);
                else
                    virusManager.removeMutation(i,j);

            }
            @Override
            public void mouseClicked(MouseEvent e) { myMouseAction(); }

            @Override
            public void mousePressed(MouseEvent e){}

            @Override
            public void mouseReleased(MouseEvent e){}

            @Override
            public void mouseEntered(MouseEvent e){}

            @Override
            public void mouseExited(MouseEvent e){}
        });

        table.setPreferredScrollableViewportSize(new Dimension(500, 50));
        table.setFillsViewportHeight(true);
        return new RowedTableScroll(table, variantsNames);
    }
}

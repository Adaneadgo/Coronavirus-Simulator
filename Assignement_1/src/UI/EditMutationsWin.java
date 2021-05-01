package UI;

import Virus.BritishVariant;
import Virus.ChineseVariant;
import Virus.IVirus;
import Virus.SouthAfricanVariant;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EditMutationsWin extends JDialog {

    private final IVirus [] Variants;
    private final RowedTableScroll table;

    EditMutationsWin(JFrame parent) {
        super(parent, "EditMutationsWin",true);

        this.Variants = new IVirus[]{ChineseVariant.getInstance(), BritishVariant.getInstance(),SouthAfricanVariant.getInstance()};
        this.table = TableMaker();


        this.getContentPane().add(TableMaker());
        this.pack();
        this.setVisible(true);

    }

    private RowedTableScroll TableMaker() {

        String[] variantsNames = {"ChineseVariant", "BritishVariant", "SouthAfricanVariant"};
        IVirus[] variants  = new IVirus[]{ChineseVariant.getInstance(), BritishVariant.getInstance(), SouthAfricanVariant.getInstance()};

        String[][] data = new String[3][3];

        DefaultTableModel model = new DefaultTableModel(variantsNames, 0) {
            @Override
            public Class getColumnClass(int columnIndex) {
                return Boolean.class;
            }
        };
        for(int i = 0; i < 3 ; i++)
            model.addRow(new Boolean[]{variants[i].containMutation(variants[0]), variants[i].containMutation(variants[1]), variants[i].containMutation(variants[2])});

        JTable table = new JTable(model);
        table.addMouseListener(new MouseListener() {

            private void mouseAction(){
                int i = table.getSelectedRow();
                int j = table.getSelectedColumn();
                if((Boolean) table.getValueAt(i,j))
                    variants[i].addMutation(variants[j]);
                else
                    variants[i].removeMutation(variants[j]);
                System.out.print( " " + i + "  succeed  " + j + " " + (Boolean) table.getValueAt(i,j) );
            }
            @Override
            public void mouseClicked(MouseEvent e) { mouseAction(); }
            @Override
            public void mousePressed(MouseEvent e) {  }
            @Override
            public void mouseReleased(MouseEvent e) {  }
            @Override
            public void mouseEntered(MouseEvent e) {  }
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        table.setPreferredScrollableViewportSize(new Dimension(1000, 50));
        table.setFillsViewportHeight(true);
        return new RowedTableScroll(table, variantsNames);
    }
}

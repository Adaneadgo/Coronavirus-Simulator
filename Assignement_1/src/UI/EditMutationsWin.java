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
    /**
     * All variants mutations possibilities
     */

    private final IVirus [] Variants;
    private final RowedTableScroll table;

    //Ctor
    EditMutationsWin(JFrame parent) {
        super(parent, "Edit Mutations",true);

        this.Variants = new IVirus[]{ChineseVariant.getInstance(), BritishVariant.getInstance(),SouthAfricanVariant.getInstance()};
        this.table = TableMaker();


        this.getContentPane().add(TableMaker());
        this.pack();
        this.setVisible(true);

    }

    private RowedTableScroll TableMaker() {
        /**
         * Created the table that we allowed us to edit the variants
         */

        String[] variantsNames = {"ChineseVariant", "BritishVariant", "SouthAfricanVariant"};
        IVirus[] variants  = new IVirus[]{ChineseVariant.getInstance(), BritishVariant.getInstance(), SouthAfricanVariant.getInstance()};

        DefaultTableModel model = new DefaultTableModel(variantsNames, 0) {
            @Override
            public Class getColumnClass(int columnIndex) {
                return Boolean.class;
            }
        };


        for (IVirus variant : variants)
            model.addRow(new Boolean[]{variant.containMutation(variants[0]), variant.containMutation(variants[1]), variant.containMutation(variants[2])});

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

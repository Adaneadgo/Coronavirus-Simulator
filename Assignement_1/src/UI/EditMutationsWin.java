package UI;

import Virus.BritishVariant;
import Virus.ChineseVariant;
import Virus.IVirus;
import Virus.SouthAfricanVariant;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class EditMutationsWin {

    IVirus [] Variants;

    EditMutationsWin(JFrame parent) {

        Variants = new IVirus[]{ChineseVariant.getInstance(), BritishVariant.getInstance(),
                SouthAfricanVariant.getInstance()};

        final JDialog frame = new JDialog(parent, "EditMutationsWin",true);
        frame.getContentPane().add(table());
        frame.pack();
        frame.setVisible(true);

    }

    private RowedTableScroll table() {

        String[] variantsNames = {"ChineseVariant", "BritishVariant", "SouthAfricanVariant"};

        String[][] data = new String[3][3];

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                data[i][j] = String.valueOf(Variants[i].containMutation(Variants[j]));
            }
        }


        JTable table = new JTable(data, variantsNames);

        DefaultTableModel model = new DefaultTableModel( )
        {
            @Override
            public Class getColumnClass(int column)
            {
                return  Boolean.class;
            }
        };

        table.setPreferredScrollableViewportSize(new Dimension(500, 50));
        table.setFillsViewportHeight(true);
        return new RowedTableScroll(table, variantsNames);
    }
}

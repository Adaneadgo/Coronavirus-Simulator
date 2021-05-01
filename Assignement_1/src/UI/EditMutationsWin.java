package UI;

import Virus.BritishVariant;
import Virus.ChineseVariant;
import Virus.IVirus;
import Virus.SouthAfricanVariant;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

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

        String[][] data = new String[3][3];

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                data[i][j] = String.valueOf(Variants[i].containMutation(Variants[j]));
            }
        }



        JTable table = new JTable(data, variantsNames);
        table.setPreferredScrollableViewportSize(new Dimension(500, 50));
        table.setFillsViewportHeight(true);
        return new RowedTableScroll(table, variantsNames);
    }
}

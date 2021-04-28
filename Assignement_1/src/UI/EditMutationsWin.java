package UI;

import javax.swing.*;
import java.awt.*;

public class EditMutationsWin {
    EditMutationsWin() {
        JFrame frame = new JFrame(" Edit Mutations");
        frame.setLayout(new BorderLayout());
        frame.add(table(), BorderLayout.CENTER);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


    }

    private RowedTableScroll table() {

        String[] columns = {"A", "B", "C", "D", "p"};
        String[][] data = {{"1", "2", "3", "4", "0"},
                {"6", "7", "8", "9", "1"},
                {"6", "7", "8", "9", "1"},
                {"6", "7", "8", "9", "1"},
                {"6", "7", "8", "9", "1"},
                {"6", "7", "8", "9", "1"},
                {"6", "7", "8", "9", "1"},
                {"6", "7", "8", "9", "1"},
                {"6", "7", "8", "9", "1"}};

        JTable table = new JTable(data, columns);
        table.setPreferredScrollableViewportSize(new Dimension(500, 50));
        table.setFillsViewportHeight(true);
        return new RowedTableScroll(table, new String[]{"ma", "kore", "po", "cosemek"});
    }
}

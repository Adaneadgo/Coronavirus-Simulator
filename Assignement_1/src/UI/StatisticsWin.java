package UI;

import javax.swing.*;
import java.awt.*;

public class StatisticsWin {

    private String[] columns = new String[]{"A","B","C","D","E"};

    StatisticsWin(){
        JFrame frame = new JFrame("Statistics");
        frame.setLayout(new BorderLayout(0,100));

        JPanel upPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,100,0));
        upPanel.add(comboBox());
        upPanel.add(textField());
        frame.add(upPanel, BorderLayout.NORTH);

        frame.add(table(), BorderLayout.CENTER);

        JPanel dwPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,100,0));
        dwPanel.add(save());
        dwPanel.add(addSick());
        dwPanel.add(vaccinate());
        frame.add(dwPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    private JComboBox<String> comboBox(){
        return new JComboBox<String>(columns);
    }

    private JTextField textField(){
        return new JTextField("put your regex here");
    }

    private JScrollPane table(){

        String [] columns = {"A", "B", "C", "D", "E"};

        String [][] data = { {"1", "2", "3", "4", "5"},
                             {"6", "7", "8", "9", "1"},
                             {"6", "7", "8", "9", "1"},
                             {"6", "7", "8", "9", "1"},
                             {"6", "7", "8", "9", "1"},
                             {"6", "7", "8", "9", "1"},
                             {"6", "7", "8", "9", "1"},
                             {"6", "7", "8", "9", "1"},
                             {"6", "7", "8", "9", "1"}};

        JTable table = new JTable(data, columns);
        table.setPreferredScrollableViewportSize(new Dimension(500,50));
        table.setFillsViewportHeight(true);
        return new JScrollPane(table);

    }

    private JButton save(){
        return new JButton("Save");
    }

    private JButton addSick(){
        return new JButton("Add Sick");
    }

    private JButton vaccinate(){
        return new JButton("vaccinate");
    }












}

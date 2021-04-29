package UI;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StatisticsWin {

    private JTable table;
    private TableColumn filteredColumn;


    StatisticsWin() throws FileNotFoundException {
        ReadCSV();

        JFrame frame = new JFrame("Statistics");
        frame.setLayout(new BorderLayout(0,100));

        JPanel upPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,100,0));
        upPanel.add(comboBox());
        upPanel.add(textField());
        frame.add(upPanel, BorderLayout.NORTH);
        frame.add(Table(), BorderLayout.CENTER);

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

        String[] options = new String[]{"Area","Humans/Area","Density","Coefficient"};
        JComboBox<String> comboBox = new JComboBox<String>(options);
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) comboBox.getSelectedItem();
                if(selectedItem == null)
                    return;

                switch (selectedItem){
                    case "Area":
                        columnsFilter(3);
                        break;
                    case "Humans/Area":
                        columnsFilter(4);
                        break;
                    case "Density":
                        columnsFilter(5);
                        break;
                    case "Coefficient":
                        columnsFilter(6);
                        break;
                }

            }
        });

        return comboBox;

    }

    private JTextField textField(){
        return new JTextField("put your regex here");
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

    private List<String[]> ReadCSV() throws FileNotFoundException {

        List<String[]> args = new ArrayList<String[]>();
        Scanner csvReader = new Scanner(new File("statistics.csv"));

        while(csvReader.hasNextLine()) {
            args.add(csvReader.nextLine().split(","));
        }

        return args;
    }

    private JScrollPane Table() throws FileNotFoundException {

        List<String[]> args = ReadCSV();

        String[] columns = args.get(0);
        args.remove(0);
        String[][] data = args.toArray(new String[0][0]);

        table = new JTable(data,columns);
        table.getTableHeader().setReorderingAllowed(false);
        return new JScrollPane(table);
    }

    private void columnsFilter(int index){

        if(filteredColumn != null) {
            table.addColumn(filteredColumn);
            table.moveColumn(table.getColumnCount() -1,filteredColumn.getModelIndex());
        }

        filteredColumn = table.getColumnModel().getColumn(index);
        table.removeColumn(filteredColumn);

    }












}

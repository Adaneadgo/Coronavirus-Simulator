package UI;

import Country.Settlement;
import IO.SimulationFile;
import IO.StatisticsFile;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StatisticsWin {

    private final SimulationFile simulationFile;
    private JFrame frame;
    private JTable table;
    private TableColumn filteredColumn;
    private TableRowSorter rowSorter;

    StatisticsWin(SimulationFile simulationFile) {
        this.simulationFile = simulationFile;

        frame = new JFrame("Statistics");
        frame.setLayout(new BorderLayout(0,100));

        JPanel upPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,100,0));
        upPanel.add(comboBox());
        upPanel.add(textField());
        frame.add(upPanel, BorderLayout.NORTH);
        frame.add(TableMaker(), BorderLayout.CENTER);

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

        JTextField textField = new JTextField("Put Text here");
        textField.getDocument().addDocumentListener(new DocumentListener() {
            private void newText(){
                String text = textField.getText();
                rowSorter.setRowFilter(RowFilter.regexFilter(text));
            }

            @Override
            public void insertUpdate(DocumentEvent e) { newText();}
            @Override
            public void removeUpdate(DocumentEvent e) { newText(); }
            @Override
            public void changedUpdate(DocumentEvent e) { newText(); }
        });

        return textField;
    }



    private JButton save(){
        JButton button = new JButton("Save");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel label = new JLabel();

                    JFileChooser fileChooser = new JFileChooser();
                    int option = fileChooser.showSaveDialog(frame);
                    if(option == JFileChooser.APPROVE_OPTION){
                        File file = fileChooser.getSelectedFile();
                        try {
                            new StatisticsFile(simulationFile).CreatCsvFile(file.toString());
                        } catch (FileNotFoundException fileNotFoundException) {
                            fileNotFoundException.printStackTrace();
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Save Canceled!");
                    }
            }
        });

        return  button;
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

    private JScrollPane TableMaker(){

        Settlement[] settlements = simulationFile.getM_map().getM_settlements();

        String [] columns = new String[]{"Name","Type","Color","Area","Humans/Area","Density",
                "Coefficient", "Number of People","Percentage of infected","number of deaths"};

        List<String[]> data = new ArrayList<String[]>();
        for(Settlement settlement: settlements){
            data.add(settlement.getStatistics());
        }

        table = new JTable(data.toArray(new String[0][0]),columns);
        table.getTableHeader().setReorderingAllowed(false);
        rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                System.out.println(table.getSelectedRow());

            }
        });



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

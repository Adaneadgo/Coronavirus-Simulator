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
import javax.swing.text.TableView;
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
    private final JFrame frame;
    private JTable table;
    private TableColumn selectedColumn = null;
    private int selectedRow;
    private TableRowSorter<TableModel> rowSorter;

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

        String[] options = new String[]{"None","Name","Type","Color"};
        JComboBox<String> comboBox = new JComboBox<String>(options);
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) comboBox.getSelectedItem();
                if(selectedItem == null)
                    return;

                switch (selectedItem){
                    case "Name":
                        selectedColumn = table.getColumnModel().getColumn(0);
                        break;
                    case "Type":
                        selectedColumn = table.getColumnModel().getColumn(1);
                        break;
                    case "Color":
                        selectedColumn = table.getColumnModel().getColumn(2);
                        break;
                    case "None":
                        selectedColumn = null;
                        rowSorter.setRowFilter(RowFilter.regexFilter(""));
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
        JButton button = new JButton("Add Sicks");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(table.getSelectedRow() <= -1) {
                    JOptionPane.showMessageDialog(null, "Select Row!");
                    return;
                }
                    String name = table.getValueAt(table.getSelectedRow(), 0).toString();
                    simulationFile.getM_map().getSettlmentByName(name).setSickPeopleSimulation();
                }

        });
        return  button;
    }

    private JButton vaccinate(){
        return new JButton("vaccinate");
    }


    private JScrollPane TableMaker(){

        Settlement[] settlements = simulationFile.getM_map().getM_settlements();

        String [] columns = new String[]{"Name","Type","Color","Area","Area per Person","Density",
                "Coefficient", "Number of People","Percentage of infected","number of deaths"};

        List<String[]> data = new ArrayList<String[]>();
        for(Settlement settlement: settlements){
            data.add(settlement.getStatistics());
        }

        table = new JTable(data.toArray(new String[0][0]),columns);
        table.getTableHeader().setReorderingAllowed(false);
        rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
               selectedRow = table.getSelectedRow();
            }
        });


        return new JScrollPane(table);
    }














}

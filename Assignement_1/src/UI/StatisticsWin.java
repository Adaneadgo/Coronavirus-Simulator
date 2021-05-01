package UI;

import Country.Map;
import Country.Settlement;
import IO.StatisticsFile;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class StatisticsWin extends JFrame {

    private final Map map;
    private final MapWin mapWin;
    private final StatisticsTable table;
    private TableColumn selectedColumn = null;


    StatisticsWin(Map map,MapWin mapWin, int rowIndex){
        this(map,mapWin);
        table.changeSelection(rowIndex, 0, false, false);


    }
    StatisticsWin(Map map,MapWin mapWin) {
        super("Statistics");
        this.map = map;
        this.mapWin = mapWin;

        this.setLayout(new BorderLayout(0,100));

        JPanel upPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,100,0));
        upPanel.add(comboBox());
        upPanel.add(textField());
        this.add(upPanel, BorderLayout.NORTH);

        this.table = LoadTable();
        this.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel dwPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,100,0));
        dwPanel.add(save());
        dwPanel.add(addSick());
        dwPanel.add(vaccinate());
        this.add(dwPanel, BorderLayout.SOUTH);

        this.pack();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

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
                        table.rowSortByColumn("",null);
                        break;
                }

            }
        });

        return comboBox;

    }

    private JTextField textField(){

        JTextField textField = new JTextField("Put Text here");
        textField.setColumns(20);
        textField.getDocument().addDocumentListener(new DocumentListener() {

            private void newText(){
                String text = textField.getText();
                table.rowSortByColumn(text,selectedColumn);
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
                    int option = fileChooser.showSaveDialog(StatisticsWin.this);
                    if(option == JFileChooser.APPROVE_OPTION){
                        File file = fileChooser.getSelectedFile();
                        try {
                            new StatisticsFile(map).CreatCsvFile(file.toString());
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
                    map.getSettlementByName(name).setSickPeopleSimulation();
                    table.reloadData();
                    mapWin.revalidate();
                }

        });

        return  button;
    }

    private JButton vaccinate(){

        JButton button = new JButton("vaccinate");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table.getSelectedRow() <= -1) {
                    JOptionPane.showMessageDialog(null, "Select Row!");
                    return;
                }

                try{

                    String input = JOptionPane.showInputDialog("put");
                    if(input == null)
                        return;

                    int num = Integer.parseInt(input);
                    if(num <= 0) {
                        JOptionPane.showMessageDialog(null, "non positive number!");
                    }

                    String name = table.getValueAt(table.getSelectedRow(), 0).toString();
                    map.getSettlementByName(name).addVaccines(num);
                    table.reloadData();
                }
                catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "not a number!");
                }


            }
        });

        return button;
    }

    public StatisticsTable LoadTable(){

        String [] columns = new String[]{"Name","Type","Color","Area","Area per Person","Amount of vaccines",
                "Coefficient", "Number of People","Number of Sick People","Percentage of infected","number of deaths"};

        Settlement[] settlements = this.map.getSettlements();

        List<String[]> data = new ArrayList<String[]>();
        for(Settlement settlement: settlements){
            data.add(settlement.getStatistics());
        }

        return new StatisticsTable(map,data.toArray(new String[0][0]),columns);

    }


    public void RefreshTable(){
        table.reloadData();
        table.revalidate();
    }









}

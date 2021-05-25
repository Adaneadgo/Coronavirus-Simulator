//Adane Adgo 315721969
//Elie Bracha 204795900
package UI;

import Country.Map;
import Country.Settlement;
import IO.SimulationFile;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StatisticsWin extends JFrame {
    /**
     * The windows that will displayed all the options as Instructed
     */

    private final SimulationFile simulationFile;
    private final MapWin mapWin;
    private final StatisticsTable table;
    private TableColumn selectedColumn = null;


    //Ctor
    StatisticsWin(SimulationFile simulationFile, MapWin mapWin, int rowIndex) {
        this(simulationFile, mapWin);
        table.changeSelection(rowIndex, 0, false, false);


    }

    //Ctor
    StatisticsWin(SimulationFile simulationFile, MapWin mapWin) {
        super("Statistics");
        this.simulationFile = simulationFile;
        this.mapWin = mapWin;

        this.setLayout(new BorderLayout(0, 100));

        JPanel upPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 0));
        upPanel.add(comboBox());
        upPanel.add(textField());
        this.add(upPanel, BorderLayout.NORTH);

        this.table = new StatisticsTable(simulationFile.getMap().getSettlements());
        this.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel dwPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 0));
        dwPanel.add(save());
        dwPanel.add(addSick());
        dwPanel.add(vaccinate());
        this.add(dwPanel, BorderLayout.SOUTH);

        this.pack();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

    }


    private JComboBox<String> comboBox() {
        /**
         * Create the combo Box for Statistics Windows
         */

        String[] options = new String[]{"None", "Name", "Type", "Color"};
        JComboBox<String> comboBox = new JComboBox<String>(options);
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) comboBox.getSelectedItem();
                if (selectedItem == null)
                    return;

                switch (selectedItem) {
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
                        table.rowSortByColumn("NONE", null);
                        break;
                }

            }
        });

        return comboBox;

    }

    private JTextField textField() {
        /**
         * Created Text Field for Statistics window
         */

        JTextField textField = new JTextField("Put Text here");
        textField.setColumns(20);
        textField.getDocument().addDocumentListener(new DocumentListener() {

            private void newText() {

                if (selectedColumn == null)
                    return;
                String text = textField.getText();
                table.rowSortByColumn(text, selectedColumn);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                newText();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                newText();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                newText();
            }
        });

        return textField;
    }


    private JButton save() {
        /**
         * Create the save button for saving all data into the CVS file
         */
        JButton button = new JButton("Save");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (simulationFile.isON()) {
                    JOptionPane.showMessageDialog(null, "Pause simulation!");
                    return;
                }

                JFileChooser fileChooser = new JFileChooser();
                int option = fileChooser.showSaveDialog(StatisticsWin.this);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();

                    try {
                        new StatisticsFile(table, file.toString());
                    } catch (IOException ignored) { }


                } else {
                    JOptionPane.showMessageDialog(null, "Save Canceled!");
                }
            }
        });

        return button;
    }

    private JButton addSick() {
        /**
         * Create add sick button for statistics window
         */
        JButton button = new JButton("Add Sicks");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (simulationFile.isON())
                    JOptionPane.showMessageDialog(null, "Pause simulation!");


                else if (selectedColumn != null)
                    JOptionPane.showMessageDialog(null, "Set Combobox on none first!");


                else if (table.getSelectedRow() <= -1)
                    JOptionPane.showMessageDialog(null, "Select Row!");


                else {
                    String name = table.getValueAt(table.getSelectedRow(), 0).toString();
                    simulationFile.getMap().getSettlementByName(name).setSickPeopleSimulation();
                    table.revalidate();
                    mapWin.revalidate();
                }
            }

        });

        return button;
    }

    private JButton vaccinate() {
        /**
         * Create vaccinate button for statistics window
         */

        JButton button = new JButton("vaccinate");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (simulationFile.isON())
                    JOptionPane.showMessageDialog(null, "Stop simulation!");


                else if (selectedColumn != null)
                    JOptionPane.showMessageDialog(null, "Set Combobox on none first!");

                else if (table.getSelectedRow() <= -1)
                    JOptionPane.showMessageDialog(null, "Select Row!");


                else {
                        String input = JOptionPane.showInputDialog("put");
                        if (input == null)
                            return;

                        int num = Integer.parseInt(input);

                        if (num <= 0)
                            JOptionPane.showMessageDialog(null, "non positive number!");

                        else {
                            String name = table.getValueAt(table.getSelectedRow(), 0).toString();
                            simulationFile.getMap().getSettlementByName(name).addVaccines(num);
                        }
                }
            }
        });

        return button;
    }

    public StatisticsTable getTable() {
        return table;
    }
}

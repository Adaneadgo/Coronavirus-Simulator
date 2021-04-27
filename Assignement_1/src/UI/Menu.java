package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import IO.SimulationFile;

public class Menu {

    SimulationFile mySimulation;

    public Menu() {
        this.Main_Window();
    }

    public void Main_Window() {

        JFrame frame = new JFrame("Main Window"); // create a frame GUI
        frame.setLayout(new GridBagLayout()); // add our object border into the Frame


        frame.setJMenuBar(Menu_Bar());
        frame.add(Simulation_Speed_Slider());
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


    }

    private JSlider Simulation_Speed_Slider(){
        JSlider jslider = new JSlider();
        return jslider;

    }


    private JMenuBar Menu_Bar(){
        JMenuBar menuBar = new JMenuBar(); // create an Object menubar
        menuBar.add(File_Menu());
        menuBar.add(Simulation_Menu());
        menuBar.add(Help_Menu());
        return menuBar;
    }

    private JMenu File_Menu(){
        JMenu file = new JMenu("File");
        file.add(Load_Item());
        file.add(Statistics_Item());
        file.add(Edit_Mutation_Item());
        file.add(Exit_Item());
        return file;
    }

    private JMenuItem Load_Item() {
        JMenuItem load = new JMenuItem("Load");
        load.addActionListener(new ActionListener() {

            private File loadFileFunc() {
                FileDialog fd = new FileDialog((Frame) null, "Please choose a file:", FileDialog.LOAD);
                fd.setVisible(true);

                if (fd.getFile() == null)
                    return null;
                File f = new File(fd.getDirectory(), fd.getFile());
                System.out.println(f.getPath());
                return f;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                File file = loadFileFunc();
                mySimulation = new SimulationFile(file);
            }
        });
        return load;
    }
    private JMenuItem Statistics_Item(){
        JMenuItem statistics = new JMenuItem("Statistics");
        statistics.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame statistics_frame = new JFrame("Statistics Window");
                JPanel statistics_panel = new JPanel();
                statistics_panel.setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();

                JComboBox comboBox = new JComboBox(new String[]{"Name", "Type", "Size", "Number of people", "Color", "Number of Sick people", "Percent of sick people"});


                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.gridwidth = 2;
                gbc.gridheight = 2;
                statistics_panel.add(comboBox, gbc);
                statistics_panel.setBackground(Color.blue);



                gbc.gridx = 0;
                gbc.gridy = 3;
                gbc.gridwidth = 2;
                gbc.gridheight = 2;
                JButton bt1 = new JButton("button");

                statistics_panel.add(bt1, gbc);

                statistics_frame.add(statistics_panel);







                statistics_frame.pack();
                statistics_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                statistics_frame.setVisible(true);
            }
        });
        return statistics;
    }
    private JMenuItem Edit_Mutation_Item(){
        JMenuItem edit_mutation = new JMenuItem("Edit Mutation");
        return edit_mutation;
    }
    private JMenuItem Exit_Item(){
        JMenuItem exit = new JMenuItem("Exit");
        return exit;
    }

    private JMenu Simulation_Menu(){
        JMenu simulation = new JMenu("Simulation");
        simulation.add(Play_Item());
        simulation.add(Pause_Item());
        simulation.add(Stop_Item());
        simulation.add(Set_Ticks_Per_Day_Item());
        return simulation;
    }

    private JMenuItem Play_Item(){
        JMenuItem play = new JMenuItem("Play");
        return play;
    }
    private JMenuItem Pause_Item(){
        JMenuItem pause = new JMenuItem("Pause");
        return pause;
    }
    private JMenuItem Stop_Item(){
        JMenuItem stop = new JMenuItem("Stop");
        return stop;
    }
    private JMenuItem Set_Ticks_Per_Day_Item(){
        JMenuItem set_ticks_per_day = new JMenuItem("Set Ticks per day");
        return set_ticks_per_day;
    }

    private JMenu Help_Menu(){
        JMenu help_menu = new JMenu("Help");
        help_menu.add(Help_Item());
        help_menu.add(About_Item());
        return help_menu;
    }

    private JMenuItem Help_Item(){
        JMenuItem help = new JMenuItem("Help");
        return help;
    }
    private JMenuItem About_Item(){
        JMenuItem about = new JMenuItem("About");
        return about;
    }




}

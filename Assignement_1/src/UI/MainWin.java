//Adane Adgo 315721969
//Elie Bracha 204795900
package UI;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import IO.SimulationFile;
import Simulation.Clock;

public class MainWin extends JFrame {
    /**
     * Main Window that will operate the all system
     */

    private SimulationFile simulationFile = null;
    private StatisticsWin statisticsWin;
    private MapWin mapWin;
    private boolean Closed = false;

    //Ctor
    public MainWin() {
        this.setName("Main Window");
        this.setLayout(new BorderLayout());

        this.setJMenuBar(Menu_Bar());
        this.add(Simulation_Speed_Slider(), BorderLayout.SOUTH);

        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }


    private JMenuBar Menu_Bar(){
        /**
         * Jswing operation to add all the instruction as needed
         */
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(File_Menu());
        menuBar.add(Simulation_Menu());
        menuBar.add(Help_Menu());
        return menuBar;
    }

    private JMenu File_Menu(){
        /**
         * All options that related to edited the file
         */
        JMenu file = new JMenu("File");
        file.add(Load_Item());
        file.add(Statistics_Item());
        file.add(Edit_Mutation_Item());
        file.add(Exit_Item());
        return file;
    }

    private JMenuItem Load_Item() {
        /**
         * Open Dialog to open file that contain all data
         */
        JMenuItem load = new JMenuItem("Load");
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = loadFileFunc();

                if(file == null) {
                    JOptionPane.showMessageDialog(null, "file not been loaded!");
                    return;
                }
                try {
                    simulationFile = SimulationFile.getInstance(file);
                    simulationFile.loadSimulation();
                    mapWin = new MapWin(simulationFile.getMap());
                    MainWin.this.add(mapWin);
                    mapWin.revalidate();

                } catch (Exception exception) { exception.printStackTrace(); }


            }
        });

        return load;
    }
    private JMenuItem Statistics_Item(){
        /**
         * Open Statistics windows
         */
        JMenuItem statistics = new JMenuItem("Statistics");
        statistics.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(simulationFile == null) {
                    JOptionPane.showMessageDialog(null, "file not been loaded!");
                    return;
                }
                statisticsWin = new StatisticsWin(simulationFile.getMap(),mapWin);
                mapWin.revalidate();
            }

        });
        return statistics;
    }

    private JMenuItem Edit_Mutation_Item(){
        /**
         * Open the edit mutation window
         */
        JMenuItem edit_mutation = new JMenuItem("Edit Mutation");
        edit_mutation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(simulationFile == null) {
                    JOptionPane.showMessageDialog(null, "file not been loaded!");
                    return;
                }
                new EditMutationsWin(MainWin.this);
                mapWin.revalidate();
            }
        });
        return edit_mutation;
    }
    private JMenuItem Exit_Item(){
        /**
         * Open the exit option window
         */
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(MainWin.this, "Are you sure?", "Exit", JOptionPane.YES_NO_OPTION);
                if (confirmed == JOptionPane.YES_OPTION) {

                    if(statisticsWin != null)
                        statisticsWin.dispose();

                    Closed = true;
                    MainWin.this.dispose();

                }
            }
        });
        return exit;
    }

    private JMenu Simulation_Menu(){
        /**
         * Open the option to start pause or stop all the simulations
         */
        JMenu simulation = new JMenu("Simulation");
        simulation.add(Play_Item());
        simulation.add(Pause_Item());
        simulation.add(Stop_Item());
        simulation.add(Set_Ticks_Per_Day_Item());
        return simulation;
    }

    private JMenuItem Play_Item(){
        /**
         * Run the simulation
         */
        JMenuItem play = new JMenuItem("Play");
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (simulationFile == null) {
                    JOptionPane.showMessageDialog(null, "file not been loaded!");
                    return;
                }
                simulationFile.setState(true);
            }

        });
        return play;
    }
    private JMenuItem Pause_Item(){
        /**
         * Pause the simulation
         */
        JMenuItem pause = new JMenuItem("Pause");
        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (simulationFile == null){
                    JOptionPane.showMessageDialog(null, "file not been loaded!");
                    return;
                }

                if(!simulationFile.isON()){
                    JOptionPane.showMessageDialog(null, "play simulation!");
                    return;

                }
                simulationFile.setState(false);

            }
        });
        return pause;
    }
    private JMenuItem Stop_Item(){
        /**
         * Stop the simulation
         */
        JMenuItem stop = new JMenuItem("Stop");
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (simulationFile == null){
                    JOptionPane.showMessageDialog(null, "file not been loaded!");
                    return;
                }
                if(!simulationFile.isON()){
                    JOptionPane.showMessageDialog(null, "play simulation!");
                    return;

                }
                simulationFile.setState(false);
                simulationFile = null;
                MainWin.this.getContentPane().remove(mapWin);
                MainWin.this.repaint();
            }
        });
        return stop;
    }
    private JMenuItem Set_Ticks_Per_Day_Item(){
        /**
         * Set ticks per day for in order to calculate days
         */
        JMenuItem set_ticks_per_day = new JMenuItem("Set Ticks per day");
        set_ticks_per_day.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(simulationFile == null){
                    JOptionPane.showMessageDialog(null, "file not been loaded!");
                    return;
                }

                else
                    jSpinner();
            }
        });
        return set_ticks_per_day;
    }

    private void jSpinner(){
        /**
         * Spinner that used to Set ticks per days
         */
        SpinnerNumberModel sModel = new SpinnerNumberModel(Clock.getTicks_per_day(), 1, 100, 1);
        JSpinner spinner = new JSpinner(sModel);

        int option = JOptionPane.showOptionDialog(null, spinner, "Set Ticks Per Day",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

        if (option == 0) {
            try{
                long ticks = (long)Float.parseFloat(spinner.getValue().toString());
                Clock.setTicks_per_day(ticks);

            }
            catch (Exception exc){
                JOptionPane.showMessageDialog(null, "Error occurred");
            }

        }

    }

    private JMenu Help_Menu(){
        /**
         * Open dialog for Help Menu
         */
        JMenu help_menu = new JMenu("Help");
        help_menu.add(Help_Item());
        help_menu.add(About_Item());
        return help_menu;
    }

    private JMenuItem Help_Item(){
        /**
         * Open Instruction for Help
         */
        JMenuItem help = new JMenuItem("Help");
        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MainWin.this,
                        "================Help Menu================\n" +
                                "\n------------------Main Window------------------\n\n" +
                                "Load: Choose the relevant file\n" +
                                "Edit Mutation: What kind of Variant you want\n" +
                                "Slider: What Speed you want for your Simulation\n" +
                                "Exit: Exit the program\n" +
                                "\n-------------Statistics Window---------------\n\n" +
                                "Combo Box: Choose relevant data you want\n" +
                                "Text Field: Specific Data\n" +
                                "Add sick: Choose city then add sick\n" +
                                "Vaccinate: Amount of vaccin you want to give to the city\n" +
                                "Save: Save all data in CVS file\n" +
                                "\n--------------Simulation Window--------------\n\n" +
                                "Start: Run the Simulation\n" +
                                "Pause: Pause the Simulation\n" +
                                "Stop: Stop the Simulation\n");
            }
        });
        return help;
    }
    private JMenuItem About_Item(){
        /**
         * Set About options
         */
        JMenuItem about = new JMenuItem("About");
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String aboutUs = "<html><br/>==================MADE BY==================<br/>" +
                        "Adane Adgo : 315721969 and Elie Bracha : 204795900 <br/>"+
                        "                At:  2/5/2021<br/>" +
                        "</html>";

                JFrame frame = new JFrame();
                frame.add(new JLabel(aboutUs));
                frame.setSize(new Dimension(100,100));

                frame.pack();
                frame.setVisible(true);


            }
        });
        return about;
    }

    private JSlider Simulation_Speed_Slider(){
        /**
         * Speed Simulation Slider when simulation run
         */
        JSlider slider = new JSlider(0,100,50);
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);


        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Clock.setSleep(slider.getValue()+100);
            }
        });
        return slider;

    }

    private File loadFileFunc() {
        /**
         * Open dialog For loqd the file
         */
        FileDialog fd = new FileDialog((Frame) null, "Please choose a file:", FileDialog.LOAD);
        fd.setVisible(true);

        if (fd.getFile() == null)
            return null;
        return  new File(fd.getDirectory(), fd.getFile());

    }

    public boolean isSimulationLoaded() {
        return simulationFile != null ;
    }

    public SimulationFile getSimulationFile() {
        return simulationFile;
    }

    public void RefreshAll(){
        /**
         * Refresh the statistics Windows
         */
        if(statisticsWin != null){
            statisticsWin.repaint();
            statisticsWin.refreshStatsWin();
        }

        if(mapWin != null)
            mapWin.repaint();

    }

    public boolean isClosed(){
        return Closed;
    }
}

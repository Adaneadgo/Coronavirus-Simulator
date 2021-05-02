package UI;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Dictionary;

import IO.SimulationFile;
import Simulation.Clock;

public class MainWin extends JFrame {

    private SimulationFile simulationFile = null;
    private StatisticsWin statisticsWin;
    private MapWin mapWin;

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
        JMenuBar menuBar = new JMenuBar();
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
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(MainWin.this, "Are you sure?", "Exit", JOptionPane.YES_NO_OPTION);
                if (confirmed == JOptionPane.YES_OPTION)
                    MainWin.this.dispose();
            }
        });
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
        JMenuItem set_ticks_per_day = new JMenuItem("Set Ticks per day");
        set_ticks_per_day.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jSpinner();
            }
        });
        return set_ticks_per_day;
    }

    private void jSpinner(){
        SpinnerNumberModel sModel = new SpinnerNumberModel(Clock.getTicks_per_day(), 1, 100, 1);
        JSpinner spinner = new JSpinner(sModel);

        int option = JOptionPane.showOptionDialog(null, spinner, "Set Ticks Per Day",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

        if (option == 0) {
            try{
                long ticks = (long)Float.parseFloat(spinner.getValue().toString());
                Clock.setTicks_per_day(ticks);
                System.out.println(ticks);

            }
            catch (Exception exc){
                JOptionPane.showMessageDialog(null, "Error occurred");
            }

        }

    }

    private JMenu Help_Menu(){
        JMenu help_menu = new JMenu("Help");
        help_menu.add(Help_Item());
        help_menu.add(About_Item());
        return help_menu;
    }

    private JMenuItem Help_Item(){
        JMenuItem help = new JMenuItem("Help");
        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MainWin.this,
                        "a b c d e f g he efewfw\n" +
                                "ewfewffewfewfewfewfewfewfew\n" +
                                "ewfewfewfewfewfwefewfewfew\n" +
                                "sdfdsfsdfdsfdfdf\n");
            }
        });
        return help;
    }
    private JMenuItem About_Item(){
        JMenuItem about = new JMenuItem("About");
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MainWin.this, "Adane and Elie!");
            }
        });
        return about;
    }

    private JSlider Simulation_Speed_Slider(){
        JSlider slider = new JSlider(0,100,100);
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);


        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Clock.setSleep(slider.getValue() * 10 );
            }
        });
        return slider;

    }

    private File loadFileFunc() {
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
        if(statisticsWin != null){
            statisticsWin.repaint();
            statisticsWin.refreshStatsWin();
        }

        if(mapWin != null)
            mapWin.repaint();

    }
}

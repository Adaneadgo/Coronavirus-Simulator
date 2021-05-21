//Adane Adgo 315721969
//Elie Bracha 204795900
package IO;

import Country.Map;
import Country.Settlement;
import Simulation.Clock;
import Simulation.SimulationRunner;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class SimulationFile {
    /**
     * The simulation file as instructed
     * implemented as singleton
     */

    private final File file;
    private final Map map;
    private boolean state;
    private final ExecutorService executor;
    private final List<SimulationRunner> simulationRunners;


    //Ctor
    public SimulationFile(File file) throws Exception {
        this.file = file;
        state = false;
        String[][] args = readArgsFromFile();
        map = new Map(args);

        Settlement[] settlements = map.getSettlements();
        CyclicBarrier barrier = new CyclicBarrier(settlements.length);
        executor = Executors.newFixedThreadPool(settlements.length);
        simulationRunners = new ArrayList<SimulationRunner>();

        for (Settlement settlement : settlements)
            simulationRunners.add(new SimulationRunner(settlement, barrier));


    }


    //ToString
    @Override
    public String toString() {
        return "\nSimulation File path: " + file + map;
    }

    //Getters and Setters
    public Map getMap() { return map; }
    public void setState(boolean state) {
        this.state = state;
        synchronized (this) {
            this.notify();
        }
    }
    public boolean isON() { return state; }
    public boolean isOFF(){ return !state; }

    //methods
    private void RunSingleSimulation() throws InterruptedException {
        /**
         *run the simulation one time on all settlements
         */
        for (SimulationRunner simulationRunner : simulationRunners)
            executor.submit(simulationRunner);
        Clock.nextTick();
    }
    public void RunSimulations() throws InterruptedException {
        synchronized (this){
            while(true) {
                System.out.println("inside the runner");

                if (this.isOFF())
                    wait();

                else {
                    while (this.isON()) {
                        RunSingleSimulation();
                        System.out.println("working");
                    }
                }
            }
        }

    }
    public Thread newSimulationThread(){
        return new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SimulationFile.this.RunSimulations();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    //auxiliary
    private String[][] readArgsFromFile() throws Exception {
        /**
         * reading the data from the file
         */
        // Create a scanner file in order to read our File
        Scanner sc = new Scanner(file);

        String data;
        List<String[]> args = new ArrayList<String[]>();
        List<String> neighbours = new ArrayList<String>();

        //handle the reading part from the file
        while (sc.hasNextLine()) {
            data = sc.nextLine();

            if (data.charAt(0) == '#')
                neighbours.add(data);

            else {
                data = data.replaceAll(" ", "");
                args.add(data.split(";", 0));
            }

        }
        sc.close();

        args.add(neighbours.toArray(new String[0]));
        return args.toArray(new String[0][0]); // return Array type

    }

}

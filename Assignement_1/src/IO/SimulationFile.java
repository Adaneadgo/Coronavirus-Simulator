//Adane Adgo 315721969
//Elie Bracha 204795900
package IO;

import Country.Map;
import Country.Settlement;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class SimulationFile {
    /**
     * The simulation file as instructed
     * implemented as singleton
     */

    private final File file;
    private  Map map;
    private  boolean  State = false;
    private static SimulationFile instance = null;

    //Ctor
    private SimulationFile(File file) { this.file = file; }

    public static SimulationFile getInstance(File file){
        /**
         * singleton implementation
         */

        if(instance == null)
            instance = new SimulationFile(file);

        return instance;
    }

    public static SimulationFile getInstance() {
        return instance;
    }

    //ToString
    @Override
    public String toString() { return "\nSimulation File path: " + file + map; }

    public Map getMap() { return map; }

    //Methods
    public void loadSimulation() throws Exception { map = new Map(readArgsFromFile());}
    public void initialSimulation(){ map.setSickPeopleSimulation();}
    public void simulateSimulation(){ map.contagionSimulation();}
    // new Simulation
    public void Simulation()  {
        /**
         *run the simulation one time on all settlements
         */
            map.Simulation();
    }

    public void setState(boolean state) {
        /**
         * indicate if simulation is on or not
         */
        this.State = state;
    }

    public boolean isON(){

        if(instance == null)
            return false;

        return this.State;
    }

    //auxiliary
    private String [][] readArgsFromFile() throws Exception{
        /**
         * reading the data from the file
         */
        // Create a scanner file in order to read our File
        Scanner sc = new Scanner(file);

        String data;
        List<String[]> args = new ArrayList<String[]>();
        List<String> neighbours = new ArrayList<String>();

        //handle the reading part from the file
        while(sc.hasNextLine()){
            data = sc.nextLine();

            if(data.charAt(0) == '#')
                neighbours.add(data);

            else{
                data = data.replaceAll(" ", "");
                args.add(data.split(";", 0));
            }

        }
        sc.close();

        args.add(neighbours.toArray(new String[0]));
        return args.toArray(new String[0][0]); // return Array type

    }





    
}

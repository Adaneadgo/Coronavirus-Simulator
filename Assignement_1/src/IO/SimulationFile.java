package IO;

import Country.Map;
import Country.Settlement;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class SimulationFile {

    private final File file;
    private  Map map;
    private  boolean  State = false;
    private static SimulationFile instance = null;

    //Ctor
    private SimulationFile(File file) { this.file = file; }

    public static SimulationFile getInstance(File file){
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
            map.Simulation();
    }

    public void setState(boolean state) {
        this.State = state;
    }

    public boolean isON(){

        if(instance == null)
            return false;

        return this.State;
    }

    //auxiliary
    private String [][] readArgsFromFile() throws Exception{
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

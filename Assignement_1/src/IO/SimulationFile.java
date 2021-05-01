package IO;

import Country.Map;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class SimulationFile {

    private final File file;
    private  Map map;

    //Ctor
    public SimulationFile(File file) { this.file = file; }

    //ToString
    @Override
    public String toString() { return "\nSimulation File path: " + file + map; }

    public Map getMap() { return map; }

    //Methods
    public void loadSimulation() throws Exception { map = new Map(readArgsFromFile());}
    public void initialSimulation(){ map.setSickPeopleSimulation();}
    public void simulateSimulation(){ map.contagionSimulation();}

    // New Simulation
    public void Simulation(){


    }

    private void step1(){


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

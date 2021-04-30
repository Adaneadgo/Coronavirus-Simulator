package IO;

import Country.Map;
import Virus.ChineseVariant;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class SimulationFile {

    private final File m_file;
    private  Map m_map;

    //Ctor
    public SimulationFile(File file) { m_file = file; }

    //ToString
    @Override
    public String toString() { return "\nSimulation File path: " + m_file + m_map; }

    public Map getM_map() { return m_map; }

    //Methods
    public void loadSimulation() throws Exception { m_map = new Map(readArgsFromFile());}
    public void initialSimulation(){ m_map.setSickPeopleSimulation();}
    public void simulateSimulation(){m_map.contagionSimulation();}


    //auxiliary
    private String [][] readArgsFromFile() throws Exception{
        // Create a scanner file in order to read our File
        Scanner sc = new Scanner(m_file);

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

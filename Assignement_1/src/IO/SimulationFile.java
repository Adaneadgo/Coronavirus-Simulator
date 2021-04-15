package IO;

import Country.Map;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SimulationFile {

    private final File m_file;
    private  Map m_map;

    //Ctor
    public SimulationFile(File file) {
        m_file = file;
    }

    //ToString
    @Override
    public String toString() { return "\nSimulation File path: " + m_file + m_map; }

    //Methods
    public void loadSimulation() throws Exception { m_map = new Map(readArgsFromFile());}
    public void initialSimulation(){ m_map.setSickPeopleSimulation();}
    public void simulateSimulation(){m_map.contagionSimulation();}


    //auxiliary
    private String [][] readArgsFromFile() throws Exception{

        Scanner sc = new Scanner(m_file);

        String data;
        List<String[]> args = new ArrayList<String[]>();

        while(sc.hasNextLine()){
            data = sc.nextLine();
            data = data.replaceAll(" ", "");
            args.add(data.split(";", 0));
        }
        sc.close();

        return args.toArray(new String[0][0]);

    }


    
}

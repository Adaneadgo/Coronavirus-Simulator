package IO;

import Country.Map;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SimulationFile {

    private final String m_fileName;
    private  Map m_map;

    //Ctor
    public SimulationFile(String fileName) { m_fileName = fileName; }

    //ToString
    @Override
    public String toString() { return "\nSimulation File Name: " + m_fileName + '\n' + "Map:\n" + m_map; }

    //Methods
    public void loadSimulation() throws Exception {
        String[][] args = readArgsFromFile();
        m_map = new Map(args);
    }

    public void initialSimulation(){
        m_map.setSicks();
    }

    //auxiliary
    private String [][] readArgsFromFile() throws Exception{

        File file = new File(m_fileName);
        Scanner sc = new Scanner(file);

        String data;
        List<String[]> args = new ArrayList<String[]>();

        while(sc.hasNextLine()){
            data = sc.nextLine();
            args.add(data.split(";", 0));
        }
        sc.close();

        return args.toArray(new String[0][0]);

    }


    
}

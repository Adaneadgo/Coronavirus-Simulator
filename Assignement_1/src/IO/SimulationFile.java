package IO;


import Country.*;

import java.io.File;
import java.util.Scanner;

public class SimulationFile {

    private final String m_fileName;
    private final static int argsNum = 7;
    private Map map;

    public SimulationFile(String fileName) throws Exception {
        m_fileName = fileName;
    }

    public boolean loadFile() throws Exception{

        File file = new File(m_fileName);
        Scanner sc = new Scanner(file);
        String data;
        String[] args = new String[argsNum];

        while(sc.hasNextLine()){
            data = sc.nextLine();
            args = data.split(";", 0);


            switch (args[0]){

                case "Moshav":
                    new Moshav();
                    break;

                case "Kibbutz":
                    new Kibbutz();
                    break;

                case "City":
                    new City();
                    break;

            }

        }

        return true;
    }


    
    
}

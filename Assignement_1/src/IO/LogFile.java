package IO;

import Country.Settlement;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogFile {
    private static Logger logger;
    private static FileHandler fileHandler;
    private static LogFile instance;

    private LogFile(String path) throws IOException  {
        path += ".txt";

        File file = new File(path);
        if(!file.exists())
            file.createNewFile();

        fileHandler = new FileHandler(path, true);
        logger = Logger.getLogger("test");
        logger.addHandler(fileHandler);
        SimpleFormatter formatter = new SimpleFormatter();
        fileHandler.setFormatter(formatter);

    }

    public static void initialize(String path) throws IOException {
        if(instance == null)
            instance = new LogFile(path);
    }

    public static LogFile getInstance()  {
       return instance;
    }


    public static boolean isInitialized(){
        return instance != null;
    }



    public synchronized void writeLog(Settlement settlement){

        logger.info("\nName:" + settlement.getName() +"\nNumber of Sicks:" +settlement.getSicksNumber() +
                "\nNumber of Deaths" +settlement.getDeathsNumber() );
    }

    public static void closeLogger(){

        if(instance != null) {
            fileHandler.close();
            logger = null;
            instance = null;
        }
    }

}

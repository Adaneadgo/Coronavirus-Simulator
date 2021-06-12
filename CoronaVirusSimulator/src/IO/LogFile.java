package IO;

import Country.Settlement;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogFile {
    private final String path;
    private Logger logger;
    private FileHandler fileHandler;
    private  static LogFile currentLogFile;

    public LogFile(String path) throws IOException {
        this.path = path;

        File file = new File(this.path);
        if (!file.exists())
            file.createNewFile();

        creatFile();
    }
    public LogFile(Memento memento) throws IOException {
        this.path = memento.getPath();

        File file = new File(this.path, String.valueOf(true));
        creatFile();
    }

    private void creatFile() throws IOException {
        fileHandler = new FileHandler(this.path, true);
        logger = Logger.getLogger("test");
        logger.addHandler(fileHandler);
        SimpleFormatter formatter = new SimpleFormatter();
        fileHandler.setFormatter(formatter);

        LogFile.currentLogFile = this;
    }


    public synchronized void writeLog(Settlement settlement) {

        logger.info("\nName:" + settlement.getName() + "\nNumber of Sicks:" + settlement.getSicksNumber() +
                "\nNumber of Deaths " + settlement.getDeathsNumber());
    }

    public void closeLogger() {
        fileHandler.close();
    }

    public static LogFile getCurrentLogFile(){
        return currentLogFile;
    }

    public String getPath() {
        return path;
    }

}

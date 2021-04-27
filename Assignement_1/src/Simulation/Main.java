//Adane Adgo 315721969
//Elie Bracha 204795900

package Simulation;

import IO.SimulationFile;
import UI.*;
import UI.Menu;

import java.awt.*;
import java.io.File;


public class Main {
    public static void main(String[] args) throws Exception {

        Menu m = new Menu();


    }

    private static File loadFileFunc() {
        FileDialog fd = new FileDialog((Frame) null, "Please choose a file:", FileDialog.LOAD);
        fd.setVisible(true);

        if (fd.getFile() == null)
            return null;
        File f = new File(fd.getDirectory(), fd.getFile());
        System.out.println(f.getPath());
        return f;
    }



}

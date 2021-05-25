//Adane Adgo 315721969
//Elie Bracha 204795900

package Simulation;

import IO.LogFile;
import IO.SimulationFile;
import UI.MainWin;

import java.io.IOException;
import java.util.logging.*;



public class Main {

    static int c = 0;
    public static void main(String[] args) throws InterruptedException, IOException {

        try {
            new MainWin();
        }catch (Exception ignore){}

    }

}

//Adane Adgo 315721969
//Elie Bracha 204795900

package Simulation;

import Country.RamzorColor;
import IO.SimulationFile;
import IO.StatisticsFile;
import UI.MainWin;

import java.awt.*;
import java.io.File;


public class Main {
    public static void main(String[] args) throws InterruptedException {

        MainWin mainWin = new MainWin();
        RunSimulation(mainWin);

    }

    private static void RunSimulation(MainWin mainWin) throws InterruptedException {

        do{
            Thread.sleep(1000);

        }while(!mainWin.isSimulationLoaded());


        SimulationFile simulationFile = mainWin.getSimulationFile();
        while(true){
            Clock.nextTick();

            if(simulationFile.isON()) {
                simulationFile.Simulation();
                mainWin.RefreshAll();
            }

            if(mainWin.isClosed())
                return;

        }

    }

}

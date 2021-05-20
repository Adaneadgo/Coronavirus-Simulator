//Adane Adgo 315721969
//Elie Bracha 204795900

package Simulation;

import IO.SimulationFile;
import UI.MainWin;


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
                simulationFile.RunSimulation();
                mainWin.RefreshAll();
                System.out.println("YES");
            }
            System.out.println("NO");

        }

    }

}

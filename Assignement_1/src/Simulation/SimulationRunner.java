package Simulation;

import Country.Settlement;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class SimulationRunner implements Runnable {
    final private Settlement settlement;
    final private CyclicBarrier barrier;

    public SimulationRunner(Settlement settlement, CyclicBarrier barrier ){
        this.settlement = settlement;
        this.barrier = barrier;
    }

    @Override
    public void run() {

        settlement.runSimulation();

        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

}

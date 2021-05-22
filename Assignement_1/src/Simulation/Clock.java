//Adane Adgo 315721969
//Elie Bracha 204795900
package Simulation;


import IO.SimulationFile;

public class Clock {

    /**
     *Clock repression as described
     */

    private static long now = 0;
    private static long ticks_per_day = 1;
    private static int sleep = 1000;

    public static long now(){ return now;}

    public static void nextTick() throws InterruptedException {
        now++;
        Thread.sleep(sleep);
    };
    public static long daysPass(long past){ return (long)Math.ceil((now - past)/(float)ticks_per_day); }

    public static void setTicks_per_day(long ticks_per_day) { Clock.ticks_per_day = ticks_per_day; }

    public static void setSleep(int sleep) {
        Clock.sleep = sleep;
    }

    public static long getTicks_per_day() {
        return ticks_per_day;
    }

    public static int getSleep() {
        return sleep;
    }
}

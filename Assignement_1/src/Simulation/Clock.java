package Simulation;

public class Clock {

    private static long now = 0;

    public static long now(){ return now;}
    public static void nextTick(){ now++;};
    
}

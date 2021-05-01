package Simulation;


public class Clock {

    private static long now = 0;
    private static long ticks_per_day = 1;

    public static long now(){ return now;}
    public static void nextTick(){ now++;};
    public static long daysPass(long past){ return (long)Math.ceil((now - past)/(float)ticks_per_day); }

    public static void setTicks_per_day(long ticks_per_day) { Clock.ticks_per_day = ticks_per_day; }

    public static long getTicks_per_day() {
        return ticks_per_day;
    }
}

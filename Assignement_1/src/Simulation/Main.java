package Simulation;

import Location.*;
import Virus.*;
import Country.*;
import Population.*;
import IO.*;



public class Main {
    public static void main(String[] args) {
        
        Point p = new Point(10, 10);
        Size s = new Size(100,100);
        Location l = new Location(p, s);
        System.out.println(l);

        

        Person p1 = new Convalescent();
        System.out.println(p1);
 



   
    }
}

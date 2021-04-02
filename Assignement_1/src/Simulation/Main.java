package Simulation;

import Location.*;
import Virus.*;
import Country.*;
import Population.*;
import IO.*;


public class Main {
    public static void main(String[] args) {

        Point po = new Point(1,1);
        Size si = new Size(100,100);
        Location lo = new Location(po, si);


        IVirus vi = new ChineseVariant();

        Settlement se = new Moshav("Tel-Aviv", lo, 100, RamzorColor.Red );

        Person pe = new Healthy(25, lo, se);


        se.AddPerson(pe);
        System.out.println(pe);

    
    }

 
    



  
}

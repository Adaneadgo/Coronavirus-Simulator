package Simulation;

import Location.*;
import Virus.*;

import java.util.Random;

import Country.*;
import Population.*;
import IO.*;


public class Main {
    public static void main(String[] args) {

        Point po = new Point(1,1);
        Size si = new Size(100,100);
        Location lo = new Location(po, si);

        Random r = new Random();
        for(int i=0; i<100; i++){
            System.out.println(r.nextInt(4));
        }











    
    }

 
    



  
}

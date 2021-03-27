import Country.RamzorColor;

import java.util.*;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {

        RamzorColor s = RamzorColor.Green;
        System.out.println(s);

        Random ran = new Random();
        for (int i = 0; i < 10; i ++)
            System.out.println(ran.nextGaussian());


    }
    
}

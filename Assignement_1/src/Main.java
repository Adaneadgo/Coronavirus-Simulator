import Location.*;
import Virus.*;
import Population.*;
import Simulation.*;
import Country.*;
import IO.*;

public class Main {
    public static void main(String[] args) {

        Person p = new Healthy();

        p = p.contagion(new ChineseVariant());

        

        
    }
}

//Adane Adgo 315721969
//Elie Bracha 204795900
package Country;

import Location.Location;

public class Kibbutz extends Settlement {
    /**
     * Represent Settlement of type "Kibbutz"
     */


    //ctors
    public Kibbutz(){}
    public Kibbutz(String name, Location location, int peopleNum, RamzorColor ramzorColor){super(name, location, peopleNum, ramzorColor);}
    
    //toString
    @Override
    public String toString() { return "Kibutz " + super.toString(); }

    //methods
    @Override
    public RamzorColor calculateRamzorGrade() {
        /**
         *Return the color that define how much the city is contagious
         */


        //Calculate the constant for the equation
        double P = this.contagiousPercent();
        coefficient = 0.45 + Math.pow(Math.pow(1.5, coefficient)*(P-0.4),3);


        //Constant for equation
         if(coefficient < 0.4)
            return RamzorColor.Green;

        else if (coefficient < 0.6)
            return RamzorColor.Yellow;

        else if(coefficient < 0.8)
            return RamzorColor.Orange; 
            
        else          
            return RamzorColor.Red;
    }
    
}

//Adane Adgo 315721969
//Elie Bracha 204795900
package Country;

import Location.Location;

public class Moshav extends Settlement {
    /**
     * Represent Settlement of type "Moshav"
     */

    //ctors
    public Moshav(){}
    public Moshav(String name, Location location, int peopleNum, RamzorColor ramzorColor){super(name, location, peopleNum, ramzorColor);}
      
    //tosting
    @Override
    public String toString() { return "Moshav " + super.toString(); }
    
    //method
    @Override
    public RamzorColor calculateRamzorGrade() {
         /**
         * Return the color that define how much the city is contagious
         */


        //Calculate the constant for the equation
        double P = this.contagiousPercent();
        coefficient = 0.3 + 3*Math.pow(Math.pow(1.2, coefficient)*(P-0.35),5);
        if(coefficient < 0.4)
        return RamzorColor.Green;


        //Constant values
        else if (coefficient < 0.6)
            return RamzorColor.Yellow;

        else if(coefficient < 0.8)
            return RamzorColor.Orange; 
        
        else          
            return RamzorColor.Red;
    }
    
}

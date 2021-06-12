//Adane Adgo 315721969
//Elie Bracha 204795900
package Country;

import java.awt.*;

public enum RamzorColor {
    /**
     * Enum of RamzorColor
     */


    //all values refers to a particular color
    Red(1), Green(0.4), Yellow(0.6), Orange(0.8);

    // coefficient
    public double defaultCoefficient;
    private final  Color color;
    private final double probability;
    
    //Ctor
    private RamzorColor(final double num){
        this.defaultCoefficient = num;

        if(num <= 0.4) {
            color = Color.GREEN;
            probability = 1;
        }

        else if(num <= 0.6) {
            color = Color.YELLOW;
            probability = 0.8;
        }
        else if(num <= 0.8) {
            color = Color.ORANGE;
            probability = 0.6;
        }
        else {
            color = Color.RED;
            probability = 0.4;
        }


    }

    @Override
    public String toString() { return this.name();}
    public double getCoefficient() { return defaultCoefficient; }

    public Color getColor() {
        /**
         * Return Color
         */
        return color;
    }

    public double getProbability() {
        /**
         * Return probability
         */
        return probability;
    }
}

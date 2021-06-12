//Adane Adgo 315721969
//Elie Bracha 204795900
package Location;

public class Size {

    /**
     *represent area as height X width
     */

    private int height;
    private int width;

    //Ctors
    public Size(){}
    public Size(int height, int width) { this.height = height; this.width = width; }
    public Size(Size other) {this.height = other.height; this.width = other.width; }

    //Getters
    public int getHeight(){return height;}
    public int getWidth(){return width;}
    public int getArea(){return height*width; }

    // tostring & equals
    @Override
    public String toString() {return height + "X" + width;}
    public boolean equals(Size other) { return height == other.height && width == other.width; }

 
 
}

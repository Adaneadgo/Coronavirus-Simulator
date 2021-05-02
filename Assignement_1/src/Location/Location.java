package Location;

import java.util.Random;

public class Location {

    /**
     * represent location object used by peoples and settlements
     */

    private Point position;
    private Size size;

    //Ctors
    public Location(){}
    public Location(Point point, Size size) { position = new Point(point); this.size = new Size(size); }
    public Location(Location other) { position = new Point(other.position); size = new Size(other.size);}

    //Geters
    public Point getPoint(){ return position;}
    public Size getSize(){return size;}
    public int getArea(){return size.getArea();}

    // toString & equals
    @Override
    public String toString() { return "{ " + position + ", " + size +" }"; }
    public boolean equals(Location other) {return this.position.equals(other.position) && this.size.equals(other.size);}

    //auxiliary
    public Point getRandomPosition(){
        Random rand = new Random();

        int h = size.getHeight();
        int w = size.getWidth();
        int x_p = position.getX();
        int y_p = position.getY();
   
        int x = rand.nextInt(w) + x_p;
        int y = rand.nextInt(h) + (y_p - h);
        return new Point(x,y);
    }
    

}

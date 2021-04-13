package Location;

import java.util.Random;

public class Location {

    private Point m_position;
    private Size m_size;

    //Ctors
    public Location(){}
    public Location(Point point, Size size) { m_position = new Point(point); m_size = new Size(size); }
    public Location(Location other) { m_position = new Point(other.m_position); m_size = new Size(other.m_size);}

    //Geters
    public Point getPoint(){ return m_position;}
    public Size getSize(){return m_size;}

    // toString & equals
    @Override
    public String toString() { return "{ " + m_position + ", " + m_size +" }"; }
    public boolean equals(Location other) {return this.m_position.equals(other.m_position) && this.m_size.equals(other.m_size);}

    //auxiliary
    public Point getRandomPosition(){
        Random rand = new Random();

        int h = m_size.getHeight();
        int w = m_size.getWidth();
        int x_p = m_position.getX();
        int y_p = m_position.getY();
   
        int x = rand.nextInt(w) + x_p;
        int y = rand.nextInt(h) + (y_p - h);
        return new Point(x,y);
    }
    

}

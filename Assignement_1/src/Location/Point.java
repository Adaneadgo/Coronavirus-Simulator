package Location;

public class Point {

    private int m_x;
    private int m_y;

    public Point() {}
    public Point(int x, int y) { this.m_x = x; this.m_y = y; }
    public Point(Point other) { this.m_y = other.m_x; this.m_x = other.m_y;}
    
    public int get_x(){return m_x;}
    public int get_y(){return m_y;}
    
    @Override
    public String toString() { return "(" + m_x + "," + m_y + ")"; }

    public boolean equals(Point other) { return m_x == other.m_x && m_y == other.m_y; }
    

}

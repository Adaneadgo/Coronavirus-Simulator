package Location;

public class Point {

    private int m_x;
    private int m_y;

    public Point() {m_x = 0; m_y = 0;}
    public Point(int x, int y) { this.m_x = x; this.m_y = y; }
    public Point(Point p) { this.m_y = p.m_x; this.m_x = p.m_y;}
    
    public int get_x(){return m_x;}
    public int get_y(){return m_y;}
    
    @Override
    public String toString() { return "(" + m_x + "," + m_y + ")"; }

    public boolean equals(Point p) { return m_x == p.m_x && m_y == p.m_y; }
    

}

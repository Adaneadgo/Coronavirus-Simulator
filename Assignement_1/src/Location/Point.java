package Location;

import java.util.Objects;

public class Point {

    private int m_x;
    private int m_y;

    public Point(int x, int y)
    {
        this.m_x = x;
        this.m_y = y;
    }

    public Point(Point p)
    {
        this.m_y = p.get_Y();
        this.m_x = p.get_X();
    }
    
    public boolean equals(Point p) {
        return m_x == p.get_X() && m_y == p.get_Y();
    }

    @Override
    public String toString() {
        return "Point(X: " + get_X() + " , " + "Y: " + get_Y() + ')';
    }

    public int get_X(){return m_x;}
    public int get_Y(){return m_y;}
}

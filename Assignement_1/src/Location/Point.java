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
        return m_x == p.m_x && m_y == p.m_y;
    }

    @Override
    public String toString() {
        return "Point(X: " + m_x + " , " + "Y: " + m_y + ')';
    }

    public int get_X(){return m_x;}
    public int get_Y(){return m_y;}
}

package Location;


public class Size {

    private int m_width;
    private int m_height;

    public Size() {m_height = 0; m_width = 0;}
    public Size(int width, int height) { this.m_height = height; this.m_width = width; }
    public Size(Size other) { this.m_width = other.m_width; this.m_height = other.m_height; }


    @Override
    public String toString() {return m_height + "X" + m_width;} 

    public boolean equals(Size other) { return m_width == other.m_width && m_height == other.m_height; }

    public int get_Width(){return m_width;}
    public int get_Height(){return m_height;}
}

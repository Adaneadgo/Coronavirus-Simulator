package Location;

import java.util.Objects;

public class Size {

    private int m_width;
    private int m_height;

    public Size(int width, int height)
    {
        this.m_height = height;
        this.m_width = width;
    }

    public Size(Size s)
    {
        this.m_width = s.get_Width();
        this.m_height = s.get_Height();
    }

    public boolean equals(Size s) {
        return m_width == s.m_width && m_height == s.m_height;
    }

    @Override
    public String toString() {
        return "Size(Width: "  + m_width + " , " + "Height: " + m_height + ')';
    }

    public int get_Width(){return m_width;}
    public int get_Height(){return m_height;}
}

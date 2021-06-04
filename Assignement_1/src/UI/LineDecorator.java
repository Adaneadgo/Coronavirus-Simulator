package UI;

import Country.Settlement;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class LineDecorator{
    private Settlement settlement1;
    private Settlement settlement2;
    public LineDecorator(Settlement s1, Settlement s2)
    {
        this.settlement1 = s1;
        this.settlement2 = s2;
    }

    public void DrawLine(Graphics g)
    {
        Color c1 = settlement1.getColorCode();
        Color c2 = settlement2.getColorCode();
        Color newColor = new Color((c1.getRed() + c2.getRed())/ 2, (c1.getGreen() + c2.getGreen()) / 2, (c1.getBlue() +c2.getBlue()) / 2);

        int x1 = settlement1.getLocation().getPoint().getX();
        int y1 = settlement1.getLocation().getPoint().getY();
        int x2 = settlement2.getLocation().getPoint().getX();
        int y2 = settlement2.getLocation().getPoint().getY();

        g.setColor(newColor);
        g.drawLine(x1,y1,x2,y2);

    }
}

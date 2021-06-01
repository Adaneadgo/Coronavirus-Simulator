package UI;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class DecoratorLine2D extends Line2D {
    private final Line2D basic;
    private final Color color;


    DecoratorLine2D(Line2D line2D, Color c1, Color c2){
        basic = line2D;
        color = new Color( (c1.getRGB() + c2.getRGB()) / 2);
    }

    public Color getColor() {
        return color;
    }

    @Override
    public double getX1() {
        return basic.getX1();
    }

    @Override
    public double getY1() {
        return basic.getY1();
    }

    @Override
    public Point2D getP1() {
        return basic.getP1();
    }

    @Override
    public double getX2() {
        return basic.getX2();
    }

    @Override
    public double getY2() {
        return basic.getY2();
    }

    @Override
    public Point2D getP2() {
        return basic.getP2();
    }

    @Override
    public void setLine(double x1, double y1, double x2, double y2) {
        basic.setLine(x1, y1, x2, y2);

    }

    @Override
    public Rectangle2D getBounds2D() {
        return basic.getBounds2D();
    }
}

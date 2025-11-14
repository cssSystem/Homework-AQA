package sys.figure;

import sys.figure.ext.FigureEx;

public class Rectangle extends FigureEx {
    public Rectangle(float a, float b, String color, String colorBorder) {
        super(new float[]{a, b}, color, colorBorder);
    }
}

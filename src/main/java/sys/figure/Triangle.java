package sys.figure;

import sys.figure.ext.FigureEx;

public class Triangle extends FigureEx {

    public Triangle(float a, float b, float c, String color, String colorBorder) {
        super(new float[]{a, b, c}, color, colorBorder);
    }
}

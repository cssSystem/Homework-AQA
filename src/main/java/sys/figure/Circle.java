package sys.figure;

import sys.figure.ext.FigureEx;

public class Circle extends FigureEx {
    public Circle(float r, String color, String colorBorder) {
        super(new float[]{r}, color, colorBorder);
    }
}

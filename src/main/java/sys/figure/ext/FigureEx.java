package sys.figure.ext;

import sys.figure.inter.Figure;

public class FigureEx implements Figure {
    protected float[] sizes;
    protected String color;
    protected String colorBorder;

    @Override
    public float[] getSizes() {
        return sizes;
    }

    public FigureEx(float[] sizes, String color, String colorBorder) {
        this.sizes = sizes;
        this.color = color;
        this.colorBorder = colorBorder;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [" +
                "Периметр=" + this.perimeter() +
                ", Площадь=" + this.area() +
                ", Цвета фона='" + color + '\'' +
                ", Цвета границы='" + colorBorder + '\'' +
                ']';
    }
}

package sys.figure;

import sys.figure.inter.Figure;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Figure> figures = new ArrayList<>();
        figures.add(new Circle(10F, "#fff", "#000"));
        figures.add(new Circle(15.5F, "#ff1", "#0f0"));
        figures.add(new Rectangle(10F, 15F, "#ff2", "#a00"));
        figures.add(new Rectangle(10F, 10F, "#ff3", "#b00"));
        figures.add(new Rectangle(10F, 30.9F, "#ff4", "#000"));
        figures.add(new Triangle(10F, 15F, 20F, "#ff5", "#c00"));
        figures.add(new Triangle(10.1F, 15.3F, 20.5F, "#ff6", "#d00"));
        for (Figure figure : figures) {
            System.out.println(figure);
        }

    }
}

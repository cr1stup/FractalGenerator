package backend.academy.fractalGenerator.transformations;

import backend.academy.fractalGenerator.model.Point;

public class CrossTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double k = Math.sqrt(1 / Math.pow(x * x - y * y, 2));

        double newX = k * x;
        double newY = k * y;

        return new Point(newX, newY);
    }

    @Override
    public String getName() {
        return "Cross transformation";
    }
}

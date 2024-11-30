package backend.academy.fractalGenerator.transformations;

import backend.academy.fractalGenerator.model.Point;

public class DiamondTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double r = Math.sqrt(x * x + y * y);
        double theta = Math.atan(x / y);

        double newX = Math.sin(theta) * Math.cos(r);
        double newY = Math.cos(theta) * Math.sin(r);

        return new Point(newX, newY);
    }

    @Override
    public String getName() {
        return "Diamond transformation";
    }
}

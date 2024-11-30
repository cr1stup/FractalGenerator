package backend.academy.fractalGenerator.transformations;

import backend.academy.fractalGenerator.model.Point;

public class HyperbolicTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double theta = Math.atan(x / y);
        double r = Math.sqrt(x * x + y * y);

        double newX = Math.sin(theta) / r;
        double newY = r * Math.cos(theta);

        return new Point(newX, newY);
    }

    @Override
    public String getName() {
        return "Hyperbolic transformation";
    }
}

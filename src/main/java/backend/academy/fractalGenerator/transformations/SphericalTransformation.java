package backend.academy.fractalGenerator.transformations;

import backend.academy.fractalGenerator.model.Point;

public class SphericalTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double r = Math.sqrt(x * x + y * y);

        double newX = x / (r * r);
        double newY = y / (r * r);

        return new Point(newX, newY);
    }

    @Override
    public String getName() {
        return "Spherical transformation";
    }
}

package backend.academy.fractalGenerator.transformations;

import backend.academy.fractalGenerator.model.Point;

public class DiskTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double r = Math.sqrt(x * x + y * y);

        double newX = 1 / Math.PI * Math.atan(y / x) * Math.sin(Math.PI * r);
        double newY = 1 / Math.PI * Math.atan(y / x) * Math.cos(Math.PI * r);

        return new Point(newX, newY);
    }

    public String getName() {
        return "Disk transformation";
    }
}

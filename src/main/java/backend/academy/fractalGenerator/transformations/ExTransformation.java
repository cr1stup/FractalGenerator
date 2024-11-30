package backend.academy.fractalGenerator.transformations;

import backend.academy.fractalGenerator.model.Point;

public class ExTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double r = Math.sqrt(x * x + y * y);
        double theta = Math.atan(x / y);
        double p0 = Math.sin(theta + r);
        double p1 = Math.cos(theta - r);

        double newX = r * (Math.pow(p0, 3) + Math.pow(p1, 3));
        double newY = r * (Math.pow(p0, 3) - Math.pow(p1, 3));

        return new Point(newX, newY);
    }

    @Override
    public String getName() {
        return "Ex transformation";
    }
}

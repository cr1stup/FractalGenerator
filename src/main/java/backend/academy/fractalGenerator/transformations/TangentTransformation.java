package backend.academy.fractalGenerator.transformations;

import backend.academy.fractalGenerator.model.Point;

public class TangentTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();

        double newX = Math.sin(x) / Math.cos(y);
        double newY = Math.tan(y);

        return new Point(newX, newY);
    }

    @Override
    public String getName() {
        return "Tangent transformation";
    }
}

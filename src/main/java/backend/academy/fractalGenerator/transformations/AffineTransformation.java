package backend.academy.fractalGenerator.transformations;

import backend.academy.fractalGenerator.model.AffineCoefficient;
import backend.academy.fractalGenerator.model.Point;

public record AffineTransformation(AffineCoefficient affineCoefficient) implements Transformation {

    @Override
    public Point apply(Point point) {
        double x = affineCoefficient.a() + point.x() * affineCoefficient.b() + point.y() * affineCoefficient.c();
        double y = affineCoefficient.d() + point.x() * affineCoefficient.e() + point.y() * affineCoefficient.f();

        return new Point(x, y);
    }

    @Override
    public String getName() {
        return "Affine Transformation";
    }
}

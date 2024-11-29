package backend.academy.fractalGenerator.transformations;

import backend.academy.fractalGenerator.model.Point;
import java.util.function.Function;

public interface Transformation extends Function<Point, Point> {
    String getName();
}

package backend.academy.fractalGenerator.utils;

import backend.academy.fractalGenerator.model.Point;
import backend.academy.fractalGenerator.model.Rect;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.concurrent.ThreadLocalRandom;
import lombok.experimental.UtilityClass;

@UtilityClass
@SuppressFBWarnings
public class RectUtils {

    public static Point randomPoint(Rect rect) {
        return new Point(
            rect.x() + ThreadLocalRandom.current().nextDouble() * rect.width(),
            rect.y() + ThreadLocalRandom.current().nextDouble() * rect.height()
        );
    }

    public static Point rotatePoint(Rect rect, Point point, double angle) {
        double centerX = rect.x() + rect.width() / 2;
        double centerY = rect.y() + rect.height() / 2;
        double x = (point.x() - centerX) * Math.cos(angle) - (point.y() - centerY) * Math.sin(angle) + centerX;
        double y = (point.x() - centerX) * Math.sin(angle) + (point.y() - centerY) * Math.cos(angle) + centerY;

        return new Point(x, y);
    }
}

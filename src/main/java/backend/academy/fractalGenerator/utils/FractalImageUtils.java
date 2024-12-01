package backend.academy.fractalGenerator.utils;

import backend.academy.fractalGenerator.model.FractalImage;
import backend.academy.fractalGenerator.model.Pixel;
import backend.academy.fractalGenerator.model.Point;
import backend.academy.fractalGenerator.model.Rect;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FractalImageUtils {

    public static Pixel resolvePixel(Rect rect, Point point, FractalImage image) {
        if (!rect.contains(point)) {
            return null;
        }

        return image.pixel(
            (int) ((point.x() - rect.x()) / rect.width() * image.width()),
            (int) ((point.y() - rect.y()) / rect.height() * image.height())
        );
    }
}

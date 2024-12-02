package backend.academy.fractalGenerator.processor;

import backend.academy.fractalGenerator.model.FractalImage;
import backend.academy.fractalGenerator.model.Pixel;

public class LogGammaCorrection implements ImageProcessor {

    private static final double GAMMA = 2.2;

    @Override
    public void process(FractalImage image) {
        double max = 0.0;

        for (int y = 0; y < image.height(); y++) {
            for (int x = 0; x < image.width(); x++) {
                if (image.pixel(x, y).hitCount() != 0) {
                    image.pixel(x, y).normal(Math.log10(image.pixel(x, y).hitCount()));
                    if (image.pixel(x, y).normal() > max) {
                        max = image.pixel(x, y).normal();
                    }
                }
            }
        }

        for (int y = 0; y < image.height(); y++) {
            for (int x = 0; x < image.width(); x++) {
                Pixel pixel = image.pixel(x, y);
                pixel.normal(image.pixel(x, y).normal() / max);
                pixel.red((int) (pixel.red() * Math.pow(pixel.normal(), (1.0 / GAMMA))));
                pixel.green((int) (pixel.green() * Math.pow(pixel.normal(), (1.0 / GAMMA))));
                pixel.blue((int) (pixel.blue() * Math.pow(pixel.normal(), (1.0 / GAMMA))));
            }
        }
    }
}

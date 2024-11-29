package backend.academy.fractalGenerator.processor;

import backend.academy.fractalGenerator.model.FractalImage;

@FunctionalInterface
public interface ImageProcessor {
    void process(FractalImage image);
}

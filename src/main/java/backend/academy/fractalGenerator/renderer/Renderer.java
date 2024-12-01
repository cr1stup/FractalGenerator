package backend.academy.fractalGenerator.renderer;

import backend.academy.fractalGenerator.model.FractalImage;
import backend.academy.fractalGenerator.model.Rect;

@FunctionalInterface
public interface Renderer {
    FractalImage render(int width, int height, Rect world) throws InterruptedException;
}

package backend.academy.fractalGenerator.model;

import backend.academy.fractalGenerator.processor.ImageProcessor;
import backend.academy.fractalGenerator.renderer.Renderer;
import backend.academy.fractalGenerator.saver.ImageFormat;
import java.nio.file.Path;
import java.util.List;

public record Parameters(
    int width,
    int height,
    Rect area,
    Renderer renderer,
    List<ImageProcessor> processors,
    Path path,
    ImageFormat format
) {}

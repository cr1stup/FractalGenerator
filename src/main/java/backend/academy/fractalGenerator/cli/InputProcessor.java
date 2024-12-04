package backend.academy.fractalGenerator.cli;

import backend.academy.fractalGenerator.model.Parameters;
import backend.academy.fractalGenerator.model.Rect;
import backend.academy.fractalGenerator.processor.ImageProcessor;
import backend.academy.fractalGenerator.processor.LogGammaCorrection;
import backend.academy.fractalGenerator.renderer.Renderer;
import backend.academy.fractalGenerator.saver.ImageFormat;
import backend.academy.fractalGenerator.transformations.CrossTransformation;
import backend.academy.fractalGenerator.transformations.DiamondTransformation;
import backend.academy.fractalGenerator.transformations.DiskTransformation;
import backend.academy.fractalGenerator.transformations.ExTransformation;
import backend.academy.fractalGenerator.transformations.HandkerchiefTransformation;
import backend.academy.fractalGenerator.transformations.HyperbolicTransformation;
import backend.academy.fractalGenerator.transformations.SphericalTransformation;
import backend.academy.fractalGenerator.transformations.TangentTransformation;
import backend.academy.fractalGenerator.transformations.Transformation;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("MagicNumber")
public class InputProcessor {

    private final List<Transformation> allTransformations;
    private final InputTools inputTools;

    public InputProcessor(InputStream inputStream, PrintStream printStream) {
        Charset charset = StandardCharsets.UTF_8;
        inputTools = new InputTools(new Scanner(inputStream, charset), printStream);
        allTransformations = List.of(
            new CrossTransformation(),
            new DiamondTransformation(),
            new DiskTransformation(),
            new ExTransformation(),
            new HandkerchiefTransformation(),
            new HyperbolicTransformation(),
            new SphericalTransformation(),
            new TangentTransformation()
        );
    }

    public Parameters inputParameters() {
        int width = inputTools.input(1, 5000, "width");
        int height = inputTools.input(1, 5000, "height");
        int iterPerSample = inputTools.input(10000, 10000000, "iteration count");
        List<Transformation> variations = inputTools.inputTransformations(allTransformations);
        int affineCount = inputTools.input(1, 200, "affine count");
        int samples = inputTools.input(1, 50, "samples count");
        int symmetry = inputTools.input(1, 20, "symmetry");
        Renderer renderer = inputTools.inputRenderer(affineCount, samples, iterPerSample, symmetry, variations);

        Rect area = new Rect(-1, -1, 2, 2);
        List<ImageProcessor> processors = List.of(new LogGammaCorrection());
        Path path = Path.of("image.png");
        ImageFormat format = ImageFormat.PNG;

        return new Parameters(width, height, area, renderer, processors, path, format);
    }
}

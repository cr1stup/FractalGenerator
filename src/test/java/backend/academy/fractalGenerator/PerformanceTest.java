package backend.academy.fractalGenerator;

import backend.academy.fractalGenerator.model.Parameters;
import backend.academy.fractalGenerator.model.Rect;
import backend.academy.fractalGenerator.processor.ImageProcessor;
import backend.academy.fractalGenerator.processor.LogGammaCorrection;
import backend.academy.fractalGenerator.renderer.MultiThreadRenderer;
import backend.academy.fractalGenerator.renderer.OneThreadRenderer;
import backend.academy.fractalGenerator.renderer.Renderer;
import backend.academy.fractalGenerator.saver.ImageFormat;
import backend.academy.fractalGenerator.transformations.DiskTransformation;
import backend.academy.fractalGenerator.transformations.Transformation;
import java.io.IOException;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PerformanceTest {

    private static Parameters oneThreadParams;
    private static Parameters multiThreadParams;
    private static FractalFlameGenerator generator;

    @BeforeAll
    public static void setUp() {
        int width = 2000;
        int height = 1000;
        int iterPerSample = 100000;
        List<Transformation> variations = List.of(new DiskTransformation());
        int affineCount = 5;
        int samples = 5;
        int symmetry = 5;
        Renderer oneThreadRenderer = new OneThreadRenderer(affineCount, samples, iterPerSample, symmetry, variations);
        Renderer multiThreadRenderer = new MultiThreadRenderer(affineCount, samples, iterPerSample, symmetry, variations);

        Rect area = new Rect(-1, -1, 2, 2);
        List<ImageProcessor> processors = List.of(new LogGammaCorrection());
        Path path = Path.of("image.png");
        ImageFormat format = ImageFormat.PNG;

        oneThreadParams = new Parameters(width, height, area, oneThreadRenderer, processors, path, format);
        multiThreadParams = new Parameters(width, height, area, multiThreadRenderer, processors, path, format);
        generator = new FractalFlameGenerator();
    }

    @Test
    @DisplayName("multiThread renderer faster than oneThread")
    public void testRendersSpeed() throws IOException, InterruptedException {
        Instant startOneThread = Instant.now();
        generator.generate(oneThreadParams);
        Instant endOneThread = Instant.now();
        Instant startMultiThread = Instant.now();
        generator.generate(multiThreadParams);
        Instant endMultiThread = Instant.now();

        long timeOneThread = Duration.between(startOneThread, endOneThread).toMillis();
        long timeMultiThread = Duration.between(startMultiThread, endMultiThread).toMillis();

        Assertions.assertTrue(timeOneThread > timeMultiThread);
    }
}

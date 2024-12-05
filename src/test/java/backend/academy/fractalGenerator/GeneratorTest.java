package backend.academy.fractalGenerator;

import backend.academy.fractalGenerator.model.Parameters;
import backend.academy.fractalGenerator.model.Rect;
import backend.academy.fractalGenerator.processor.ImageProcessor;
import backend.academy.fractalGenerator.processor.LogGammaCorrection;
import backend.academy.fractalGenerator.renderer.MultiThreadRenderer;
import backend.academy.fractalGenerator.renderer.OneThreadRenderer;
import backend.academy.fractalGenerator.saver.ImageFormat;
import backend.academy.fractalGenerator.transformations.DiskTransformation;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;
import backend.academy.fractalGenerator.transformations.ExTransformation;
import backend.academy.fractalGenerator.transformations.HandkerchiefTransformation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class GeneratorTest {

    private final FractalFlameGenerator generator = new FractalFlameGenerator();

    @ParameterizedTest
    @MethodSource("provideDataForGeneratorTest")
    @DisplayName("generator works correctly on different parameters")
    public void testGenerator(Parameters parameters) {
        Assertions.assertDoesNotThrow(() -> generator.generate(parameters));
    }

    private static Stream<Arguments> provideDataForGeneratorTest() {
        Rect area = new Rect(-1, -1, 2, 2);
        List<ImageProcessor> processors = List.of(new LogGammaCorrection());
        Path path = Path.of("image.png");
        ImageFormat format = ImageFormat.PNG;

        return Stream.of(
            Arguments.of(new Parameters(
                2000,
                1000,
                area,
                new MultiThreadRenderer(5, 5, 100000, 5,
                                        List.of(new DiskTransformation())),
                processors,
                path,
                format
            )),

            Arguments.of(new Parameters(
                500,
                800,
                area,
                new MultiThreadRenderer(5, 20, 100000, 2,
                                        List.of(new HandkerchiefTransformation())),
                processors,
                path,
                format
            )),

            Arguments.of(new Parameters(
                500,
                500,
                area,
                new OneThreadRenderer(20, 2, 100000, 10,
                                        List.of(new ExTransformation())),
                processors,
                path,
                format
            ))
        );
    }
}

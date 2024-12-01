package backend.academy.fractalGenerator.renderer;

import backend.academy.fractalGenerator.model.FractalImage;
import backend.academy.fractalGenerator.model.Rect;
import backend.academy.fractalGenerator.transformations.AffineTransformation;
import backend.academy.fractalGenerator.transformations.Transformation;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultiThreadRenderer extends BaseRenderer {

    public MultiThreadRenderer(
            int affineCount,
            int samples,
            int iterPerSample,
            int symmetry,
            List<Transformation> variations
    ) {
        super(affineCount, samples, iterPerSample, symmetry, variations);
    }

    @Override
    public void renderAllImage(FractalImage image, Rect world, List<AffineTransformation> affineTransformations)
                                                                                        throws InterruptedException {
        var executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for (int i = 0; i < samples; i++) {
            executorService.execute(() -> renderOneSample(image, world, affineTransformations));
        }

        executorService.shutdown();
        executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
    }
}

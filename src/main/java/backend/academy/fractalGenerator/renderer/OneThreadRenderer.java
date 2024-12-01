package backend.academy.fractalGenerator.renderer;

import backend.academy.fractalGenerator.model.FractalImage;
import backend.academy.fractalGenerator.model.Rect;
import backend.academy.fractalGenerator.transformations.AffineTransformation;
import backend.academy.fractalGenerator.transformations.Transformation;
import java.util.List;

public class OneThreadRenderer extends BaseRenderer {

    public OneThreadRenderer(
        int affineCount,
        int samples,
        int iterPerSample,
        int symmetry,
        List<Transformation> variations
    ) {
        super(affineCount, samples, iterPerSample, symmetry, variations);
    }

    @Override
    public void renderAllImage(FractalImage image, Rect world, List<AffineTransformation> affineTransformations) {
        for (int i = 0; i < samples; i++) {
            renderOneSample(image, world, affineTransformations);
        }
    }
}

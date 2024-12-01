package backend.academy.fractalGenerator.renderer;

import backend.academy.fractalGenerator.model.AffineCoefficient;
import backend.academy.fractalGenerator.model.FractalImage;
import backend.academy.fractalGenerator.model.Rect;
import backend.academy.fractalGenerator.transformations.AffineTransformation;
import backend.academy.fractalGenerator.transformations.Transformation;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@SuppressFBWarnings
public abstract class BaseRenderer implements Renderer {

    protected final int affineCount;
    protected final int samples;
    protected final int iterPerSample;
    protected final int symmetry;
    protected final List<Transformation> transformations;
    private static final int INIT_STEPS = -20;

    @Override
    public FractalImage render(int width, int height, Rect world) {
        FractalImage image = FractalImage.create(width, height);
        List<AffineTransformation> affineTransformations = generateAffineTransformations();
        renderAllImage(image, world, affineTransformations);

        return image;
    }

    protected abstract void renderAllImage(
        FractalImage image,
        Rect world,
        List<AffineTransformation> affineTransformations
    );

    private List<AffineTransformation> generateAffineTransformations() {
        List<AffineTransformation> affineTransformations = new ArrayList<>(affineCount);

        for (int i = 0; i < affineCount; i++) {
            AffineTransformation transformation = new AffineTransformation(
                AffineCoefficient.generateRandom(ThreadLocalRandom.current())
            );
            affineTransformations.add(transformation);
        }

        return affineTransformations;
    }
}

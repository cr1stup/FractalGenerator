package backend.academy.fractalGenerator.renderer;

import backend.academy.fractalGenerator.model.AffineCoefficient;
import backend.academy.fractalGenerator.model.FractalImage;
import backend.academy.fractalGenerator.model.Pixel;
import backend.academy.fractalGenerator.model.Point;
import backend.academy.fractalGenerator.model.Rect;
import backend.academy.fractalGenerator.transformations.AffineTransformation;
import backend.academy.fractalGenerator.transformations.Transformation;
import backend.academy.fractalGenerator.utils.FractalImageUtils;
import backend.academy.fractalGenerator.utils.ListUtils;
import backend.academy.fractalGenerator.utils.RectUtils;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.awt.Color;
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
    public FractalImage render(int width, int height, Rect world) throws InterruptedException {
        FractalImage image = FractalImage.create(width, height);
        List<AffineTransformation> affineTransformations = generateAffineTransformations();
        renderAllImage(image, world, affineTransformations);

        return image;
    }

    protected abstract void renderAllImage(
        FractalImage image,
        Rect world,
        List<AffineTransformation> affineTransformations
    ) throws InterruptedException;

    protected void renderOneSample(FractalImage image, Rect world, List<AffineTransformation> affineTransformations) {
        Point currentPoint = RectUtils.randomPoint(world);

        for (int step = INIT_STEPS; step < iterPerSample; step++) {
            AffineTransformation affine = ListUtils.random(affineTransformations);
            Transformation transformation = ListUtils.random(transformations);
            currentPoint = affine.apply(currentPoint);
            currentPoint = transformation.apply(currentPoint);

            if (step > 0) {
                double theta = 0.0;
                for (int piece = 0; piece < symmetry; theta += Math.PI * 2 / symmetry, piece++) {
                    Point point = RectUtils.rotatePoint(world, currentPoint, theta);
                    processPoint(world, image, point, affine);
                }
            }
        }
    }

    private void processPoint(Rect world, FractalImage image, Point point, AffineTransformation affine) {
        if (!world.contains(point)) {
            return;
        }

        Pixel pixel = FractalImageUtils.resolvePixel(world, point, image);

        if (pixel != null) {
            synchronized (pixel) {
                Color color = affine.affineCoefficient().color();
                pixel.saturateHitCount(color);
            }
        }
    }

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

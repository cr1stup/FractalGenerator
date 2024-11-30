package backend.academy.fractalGenerator.renderer;

import backend.academy.fractalGenerator.model.FractalImage;
import backend.academy.fractalGenerator.model.Rect;
import backend.academy.fractalGenerator.transformations.Transformation;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class BaseRenderer implements Renderer {

    protected final int affineCount;
    protected final int samples;
    protected final int iterPerSample;
    protected final int symmetry;
    protected final List<Transformation> transformations;
    private static final int INIT_STEPS = -20;

    @Override
    public FractalImage render(int width, int height, Rect world) {
        return null;
    }
}

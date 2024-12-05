package backend.academy.fractalGenerator;

import backend.academy.fractalGenerator.model.Point;
import backend.academy.fractalGenerator.transformations.CrossTransformation;
import backend.academy.fractalGenerator.transformations.DiamondTransformation;
import backend.academy.fractalGenerator.transformations.DiskTransformation;
import backend.academy.fractalGenerator.transformations.ExTransformation;
import backend.academy.fractalGenerator.transformations.HandkerchiefTransformation;
import backend.academy.fractalGenerator.transformations.HyperbolicTransformation;
import backend.academy.fractalGenerator.transformations.SphericalTransformation;
import backend.academy.fractalGenerator.transformations.TangentTransformation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TransformationsTest {

    @Test
    @DisplayName("CrossTransformation works correctly")
    public void testCrossTransformation() {
        CrossTransformation transformation = new CrossTransformation();
        Point point = new Point(5, 3);

        Point newPoint = transformation.apply(point);

        Assertions.assertEquals(new Point(0.3125, 0.1875), newPoint);
    }

    @Test
    @DisplayName("DiamondTransformation works correctly")
    public void testDiamondTransformation() {
        DiamondTransformation transformation = new DiamondTransformation();
        Point point = new Point(1, 1);

        Point newPoint = transformation.apply(point);

        Assertions.assertEquals(new Point(0.11026884405188132, 0.6984559986366085), newPoint);
    }

    @Test
    @DisplayName("DiskTransformation works correctly")
    public void testDiskTransformation() {
        DiskTransformation transformation = new DiskTransformation();
        Point point = new Point(1, 1);

        Point newPoint = transformation.apply(point);

        Assertions.assertEquals(new Point(-0.24097563321246931, -0.06656383551035391), newPoint);
    }

    @Test
    @DisplayName("ExTransformation works correctly")
    public void testExTransformation() {
        ExTransformation transformation = new ExTransformation();
        Point point = new Point(1, 5);

        Point newPoint = transformation.apply(point);

        Assertions.assertEquals(new Point(-2.9266248712347447, -2.994503890847441), newPoint);
    }

    @Test
    @DisplayName("HandkerchiefTransformation works correctly")
    public void testHandkerchiefTransformation() {
        HandkerchiefTransformation transformation = new HandkerchiefTransformation();
        Point point = new Point(1, 5);

        Point newPoint = transformation.apply(point);

        Assertions.assertEquals(new Point(-4.253854383986873, 0.9591642230187197), newPoint);
    }

    @Test
    @DisplayName("HyperbolicTransformation works correctly")
    public void testHyperbolicTransformation() {
        HyperbolicTransformation transformation = new HyperbolicTransformation();
        Point point = new Point(1, 10);

        Point newPoint = transformation.apply(point);

        Assertions.assertEquals(new Point(0.009900990099009903, 10.0), newPoint);
    }

    @Test
    @DisplayName("SphericalTransformation works correctly")
    public void testSphericalTransformation() {
        SphericalTransformation transformation = new SphericalTransformation();
        Point point = new Point(4, 3);

        Point newPoint = transformation.apply(point);

        Assertions.assertEquals(new Point(0.16, 0.12), newPoint);
    }

    @Test
    @DisplayName("TangentTransformation works correctly")
    public void testTangentTransformation() {
        TangentTransformation transformation = new TangentTransformation();
        Point point = new Point(1, 1);

        Point newPoint = transformation.apply(point);

        Assertions.assertEquals(new Point(1.557407724654902, 1.5574077246549023), newPoint);
    }
}

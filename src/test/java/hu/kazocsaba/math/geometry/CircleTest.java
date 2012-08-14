package hu.kazocsaba.math.geometry;

import hu.kazocsaba.math.matrix.MatrixFactory;
import hu.kazocsaba.math.matrix.Vector3;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kazó Csaba
 */
public class CircleTest {
	@Test
	public void testCircumferencePoints() {
		testCircumferencePoints(Circle3.create(MatrixFactory.createVector(1, 2, 3), MatrixFactory.createVector(1, 0, 0), 2));
		testCircumferencePoints(Circle3.create(MatrixFactory.createVector(1, 2, 3), MatrixFactory.createVector(0, 1, 0), 2));
		testCircumferencePoints(Circle3.create(MatrixFactory.createVector(1, 2, 3), MatrixFactory.createVector(0, 0, 1), 2));
		testCircumferencePoints(Circle3.create(MatrixFactory.createVector(1, 2, 3), MatrixFactory.createVector(1, 1, 1), 2));
		testCircumferencePoints(Circle3.create(MatrixFactory.createVector(8.54998, 38.008198, 50.079457), MatrixFactory.createVector(-0.104916, -0.780287, 0.616558), 1));
	}

	private void testCircumferencePoints(Circle3 circle) {
		Plane3 plane=circle.getPlane();
		
		Vector3 zeroPoint=circle.getPointAt(0);
		
		assertEquals(0, plane.distance(zeroPoint), 1e-8);
		assertEquals(circle.getRadius(), circle.getCenter().error(zeroPoint), 1e-8);
		
		for (int angle=10; angle<=360; angle+=10) {
			double rad=Math.toRadians(angle);
			Vector3 p=circle.getPointAt(rad);
			assertEquals(0, plane.distance(p), 1e-8);
			assertEquals(circle.getRadius(), circle.getCenter().error(p), 1e-8);
			assertEquals(p.minus(circle.getCenter()).dot(zeroPoint.minus(circle.getCenter())), circle.getRadius()*circle.getRadius()*Math.cos(rad), 1e-8);
		}
	}
}

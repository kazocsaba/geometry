package hu.kazocsaba.math.geometry;

import hu.kazocsaba.math.matrix.MatrixFactory;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kazó Csaba
 */
public class PlaneTest {
	@Test
	public void testDistance() {
		Plane3 plane=Plane3.create(MatrixFactory.createVector(1, 1, 1), MatrixFactory.createVector(1, 0, 0));
		assertEquals(1, plane.distance(MatrixFactory.createVector(2, 2, 1)), 1e-8);
		assertEquals(1, plane.distance(MatrixFactory.createVector(0, 2, 1)), 1e-8);
	}
	@Test
	public void testLineIntersect() {
		Plane3 plane=Plane3.create(MatrixFactory.createVector(1, 1, 1), MatrixFactory.createVector(1, 0, 0));
		Line3 line=Line3.createFromTwoPoints(MatrixFactory.createVector(5, 3, 6), MatrixFactory.createVector(1, 20, 30));
		assertEquals(0, plane.intersect(line).minus(MatrixFactory.createVector(1, 20, 30)).norm(), 1e-8);
		
		line=Line3.createFromDir(MatrixFactory.createVector(5, 4, 3), MatrixFactory.createVector(1, 0, 0));
		assertEquals(0, plane.intersect(line).minus(MatrixFactory.createVector(1, 4, 3)).norm(), 1e-8);
	}
}

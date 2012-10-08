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
	@Test
	public void testPlaneIntersect() {
		Plane3 p1=Plane3.create(MatrixFactory.createVector(.5, .5, 3), MatrixFactory.createVector(1, 1, 0));
		Plane3 p2=Plane3.create(MatrixFactory.createVector(-4, 2, 8), MatrixFactory.createVector(0, 0, 1));
		
		Line3 line=p1.intersect(p2);
		assertEquals(1, Math.abs(line.getUnitDir().dot(MatrixFactory.createVector(1, -1, 0).normalized())), 1e-8);
		assertEquals(0, line.distance(MatrixFactory.createVector(1, 0, 8)), 1e-8);
	}
	
	@Test(expected=DegenerateCaseException.class)
	public void testParallelPlaneIntersect() {
		Plane3 p1=Plane3.create(MatrixFactory.createVector(3, 4, -5), MatrixFactory.createVector(4, 0, 0));
		Plane3 p2=Plane3.create(MatrixFactory.createVector(42, -231, 7), MatrixFactory.createVector(-1, 0, 0));
		p1.intersect(p2);
	}
}

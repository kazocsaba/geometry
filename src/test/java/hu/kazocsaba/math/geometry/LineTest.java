package hu.kazocsaba.math.geometry;

import hu.kazocsaba.math.matrix.Matrix3;
import hu.kazocsaba.math.matrix.MatrixFactory;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kazó Csaba
 */
public class LineTest {
	
	@Test
	public void testDistance() {
		Line3 line=Line3.createFromDir(MatrixFactory.createVector(1, 1, 1), MatrixFactory.createVector(1, 0, 0));
		assertEquals(1, line.distance(MatrixFactory.createVector(2, 2, 1)), 1e-8);
	}
	
	@Test
	public void testProjection() {
		Line3 line=Line3.createFromDir(MatrixFactory.createVector(0, 0, 0), MatrixFactory.createVector(0, 3, 0));
		assertEquals(1, line.getClosestT(MatrixFactory.createVector(2, 3, -6)), 1e-8);
	}
	
	@Test
	public void testRotation() {
		Line3 line=Line3.createFromDir(MatrixFactory.createVector(0, 0, 0), MatrixFactory.createVector(0, 0, 1));
		Matrix3 rot=line.getRotation(Math.PI/2);
		assertEquals(0, rot.mul(MatrixFactory.createVector(1, 1, 0)).minus(MatrixFactory.createVector(-1, 1, 0)).norm(), 1e-8);
	}
	
	@Test
	public void testLineLineDistance() {
		Line3 l1=Line3.createFromDir(MatrixFactory.createVector(1, 1, 1), MatrixFactory.createVector(2, 0, 0));
		Line3 l2=Line3.createFromDir(MatrixFactory.createVector(0, 0, 0), MatrixFactory.createVector(1, 4, 0));
		assertEquals(1, l1.distance(l2), 1e-8);
		assertEquals(1, l2.distance(l1), 1e-8);
		Line3 l3=Line3.createFromDir(MatrixFactory.createVector(0, 0, 0), MatrixFactory.createVector(4, 4, 4));
		assertEquals(0, l1.distance(l3), 1e-8);
		assertEquals(0, l3.distance(l1), 1e-8);
		
		assertEquals(0, l1.distance(l1), 1e-8);
		assertEquals(0, l2.distance(l2), 1e-8);
		assertEquals(0, l3.distance(l3), 1e-8);
	}
}

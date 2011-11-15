package hu.kazocsaba.math.geometry;

import hu.kazocsaba.math.matrix.MatrixFactory;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kazó Csaba
 */
public class BoxTest {
	private static final double EPS=1e-8;
	
	public BoxTest() {
	}

	@Test
	public void testLineIntersect() {
		Box2 box=new Box2(MatrixFactory.createVector(0, 0), 1, 1);
		Line2 line=Line2.createFromDir(MatrixFactory.createVector(-1, 0), MatrixFactory.createVector(.5, .5));
		
		assertNull(box.intersect(line));
		
		line=Line2.createFromDir(MatrixFactory.createVector(-1, 1), MatrixFactory.createVector(.5, 0));
		
		Segment2 isection=box.intersect(line);
		assertNotNull(isection);
		assertTrue((isection.getP1().error(box.getTopLeft())<EPS && isection.getP2().error(box.getTopRight())<EPS) || ((isection.getP2().error(box.getTopLeft())<EPS && isection.getP1().error(box.getTopRight())<EPS)));
	}
}

package hu.kazocsaba.math.geometry;

import hu.kazocsaba.math.matrix.Vector3;
import hu.kazocsaba.math.matrix.immutable.ImmutableVector3;

/**
 * A line segment in 3D space.
 * @author Kazó Csaba
 */
public final class Segment3 extends Segment {

	/**
	 * Creates a new line segment between two points.
	 * @param p1 the starting point of the segment
	 * @param p2 the end point of the segment
	 */
	public Segment3(Vector3 p1, Vector3 p2) {
		super(p1, p2);
	}

	@Override
	public ImmutableVector3 getP1() {
		return (ImmutableVector3) super.getP1();
	}

	@Override
	public ImmutableVector3 getP2() {
		return (ImmutableVector3) super.getP2();
	}

	@Override
	public Vector3 getPointAt(double t) {
		return (Vector3) super.getPointAt(t);
	}
	
}

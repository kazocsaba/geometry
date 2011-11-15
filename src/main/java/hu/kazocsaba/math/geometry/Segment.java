package hu.kazocsaba.math.geometry;

import hu.kazocsaba.math.matrix.Vector;
import hu.kazocsaba.math.matrix.immutable.ImmutableMatrixFactory;
import hu.kazocsaba.math.matrix.immutable.ImmutableVector;

/**
 * A finite line segment. It is specified with its end points.
 * @author Kazó Csaba
 */
public class Segment {
	protected static final double EPS=1e-8;
	
	private final ImmutableVector p1, p2;
	private final ImmutableVector dir;

	Segment(Vector p1, Vector p2) {
		this.p1 = ImmutableMatrixFactory.copy(p1);
		this.p2 = ImmutableMatrixFactory.copy(p2);
		dir = ImmutableMatrixFactory.copy(this.p2.minus(this.p1));
		if (dir.norm()<EPS) throw new IllegalArgumentException("The points are too close to each other");
	}

	/**
	 * Returns the starting point of this segment.
	 * @return the starting point
	 */
	public ImmutableVector getP1() {return p1;}

	/**
	 * Returns the end point of this segment.
	 * @return the end point
	 */
	public ImmutableVector getP2() {return p2;}
	
	/**
	 * Returns the point on this line corresponding to the parameter value. The parameter {@code t} is linear,
	 * its value is 0 at {@code p1} and 1 at {@code p2}. If it is less than 0 or greater than 1, then the returned
	 * point will be outside this segment.
	 * @param t the parameter value
	 * @return {@code p1+(p2-p1)*t}
	 */
	public Vector getPointAt(double t) {
		return p1.plus(dir.times(t));
	}
}

package hu.kazocsaba.math.geometry;

import hu.kazocsaba.math.matrix.Vector2;
import hu.kazocsaba.math.matrix.immutable.ImmutableVector2;

/**
 * A line segment in 2D space.
 * @author Kazó Csaba
 */
public final class Segment2 extends Segment {

	/**
	 * Creates a new line segment between two points.
	 * @param p1 the starting point of the segment
	 * @param p2 the end point of the segment
	 * @throws IllegalArgumentException if the points are too close to each other
	 */
	public Segment2(Vector2 p1, Vector2 p2) {
		super(p1, p2);
	}

	@Override
	public ImmutableVector2 getP1() {
		return (ImmutableVector2) super.getP1();
	}

	@Override
	public ImmutableVector2 getP2() {
		return (ImmutableVector2) super.getP2();
	}

	@Override
	public Vector2 getPointAt(double t) {
		return (Vector2) super.getPointAt(t);
	}
	
	/**
	 * Returns the perpendicular bisector of this segment.
	 * @return the perpendicular bisector
	 */
	public Line2 getPerpendicularBisector() {
		return Line2.createFromNormal(getP1().plus(getP2()).times(.5), getP2().minus(getP1()));
	}
}

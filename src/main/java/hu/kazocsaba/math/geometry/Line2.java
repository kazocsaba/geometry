package hu.kazocsaba.math.geometry;

import hu.kazocsaba.math.matrix.Vector2;
import hu.kazocsaba.math.matrix.immutable.ImmutableMatrixFactory;
import hu.kazocsaba.math.matrix.immutable.ImmutableVector2;

/**
 * A line in 2D space. It is specified by a point P and a direction D through the formula
 * {@code P + t*D} where the {@code t} parameter is used to specify the points of the line.
 * D is not necessarily unit length.
 * @author Kazó Csaba
 */
public class Line2 extends Line {
	
	Line2(Vector2 point, Vector2 dir) {
		super(point, dir);
	}
	@Override
	public ImmutableVector2 getDir() {
		return (ImmutableVector2) super.getDir();
	}

	@Override
	public ImmutableVector2 getUnitDir() {
		return (ImmutableVector2) super.getUnitDir();
	}

	@Override
	public ImmutableVector2 getPoint() {
		return (ImmutableVector2) super.getPoint();
	}

	@Override
	public Vector2 getPointAt(double t) {
		return (Vector2) super.getPointAt(t);
	}

	@Override
	public Vector2 getPointWhereCoord(int coord, double value) {
		return (Vector2) super.getPointWhereCoord(coord, value);
	}
	
	/**
	 * Creates a new line from a point and a direction.
	 * @param point a point on the line
	 * @param dir the direction of the line
	 * @return the new line
	 * @throws IllegalArgumentException if the length of the direction vector is too small
	 */
	public static Line2 createFromDir(Vector2 point, Vector2 dir) {
		return new Line2(point, dir);
	}
	/**
	 * Creates a new line from a point and a normal direction.
	 * @param point a point on the line
	 * @param normal the normal direction of the line
	 * @return the new line
	 * @throws IllegalArgumentException if the length of the direction vector is too small
	 */
	public static Line2 createFromNormal(Vector2 point, Vector2 normal) {
		return new Line2(point, ImmutableMatrixFactory.createVector(normal.getY(), -normal.getX()));
	}
	/**
	 * Creates a new line which passes through two points. The direction vector of the line will be {@code p2-p1}.
	 * This means that {@code p1} will be the point for parameter value {@code t = 0}, and {@code p2} will be at
	 * {@code t = 1}.
	 * @param p1 the first point
	 * @param p2 the second point
	 * @return the new line
	 * @throws IllegalArgumentException if the distance between the points is too small
	 */
	public static Line2 createFromTwoPoints(Vector2 p1, Vector2 p2) {
		return new Line2(p1, p2.minus(p1));
	}
	
	/**
	 * Returns the y coordinate of the point which lies on this line and has the specified x coordinate.
	 * @param x the x coordinate
	 * @return the corresponding y coordinate
	 */
	public double getYforX(double x) {
		return getPoint().getY()+getDir().getY()/getDir().getX()*(x-getPoint().getX());
	}
	
	/**
	 * Returns the x coordinate of the point which lies on this line and has the specified y coordinate.
	 * @param y the y coordinate
	 * @return the corresponding x coordinate
	 */
	public double getXforY(double y) {
		return getPoint().getX()+getDir().getX()/getDir().getY()*(y-getPoint().getY());
	}
}

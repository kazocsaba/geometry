package hu.kazocsaba.math.geometry;

import hu.kazocsaba.math.matrix.Vector;
import hu.kazocsaba.math.matrix.immutable.ImmutableMatrixFactory;
import hu.kazocsaba.math.matrix.immutable.ImmutableVector;

/**
 * An infinite plane. It is specified with a point and a normal vector.
 * The normal is not necessarily unit length.
 * @author Kazó Csaba
 */
public class Plane {
	protected static final double EPS=1e-8;

	private final ImmutableVector point;
	private final ImmutableVector normal;
	private final ImmutableVector unitNormal;
	
	Plane(Vector point, Vector normal) {
		this.point=ImmutableMatrixFactory.copy(point);
		this.normal=ImmutableMatrixFactory.copy(normal);
		double length=this.normal.norm();
		if (length<EPS) throw new IllegalArgumentException("Badly specified plane (normal vector too small)");
		this.unitNormal=ImmutableMatrixFactory.copy(this.normal.times(1/length));
	}
	
	/**
	 * Returns the point specifying the plane.
	 * @return the point specifying the plane
	 */
	public ImmutableVector getPoint() {return point;}
	/**
	 * Returns the normal of the plane. The normal will not necessarily be of unit length.
	 * @return the normal vector
	 */
	public ImmutableVector getNormal() {return normal;}
	/**
	 * Returns the unit normal of the plane.
	 * @return the unit normal vector
	 */
	public ImmutableVector getUnitNormal() {return unitNormal;}
	/**
	 * Returns the distance between this line and a point.
	 * @return the distance between this line and the point
	 */
	public double distance(Vector p) {
		return Math.abs(unitNormal.dot(point.minus(p)));
	}

	@Override
	public String toString() {
		return String.format("Plane[%s, n=%s]",getPoint(), getNormal());
	}
	
}

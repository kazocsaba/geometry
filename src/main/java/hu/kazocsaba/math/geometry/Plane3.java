package hu.kazocsaba.math.geometry;

import hu.kazocsaba.math.matrix.Vector3;
import hu.kazocsaba.math.matrix.immutable.ImmutableVector3;

/**
 * An infinite plane in 3D space. It is specified with a point and a normal vector.
 * The normal is not necessarily unit length.
 * @author Kazó Csaba
 */
public class Plane3 extends Plane {
	Plane3(Vector3 point, Vector3 normal) {
		super(point, normal);
	}

	@Override
	public ImmutableVector3 getPoint() {
		return (ImmutableVector3) super.getPoint();
	}

	@Override
	public ImmutableVector3 getNormal() {
		return (ImmutableVector3) super.getNormal();
	}

	@Override
	public ImmutableVector3 getUnitNormal() {
		return (ImmutableVector3) super.getUnitNormal();
	}
	
	/**
	 * Creates a new plane from a point and a normal vector. The normal does not need to be of unit length.
	 * @param point a point of the plane
	 * @param normal a vector perpendicular to the plane
	 * @return the plane defined by the arguments
	 * @throws IllegalArgumentException if the normal vector is too small
	 */
	public static Plane3 create(Vector3 point, Vector3 normal) {
		return new Plane3(point, normal);
	}
	
	/**
	 * Returns the intersection of this plane and a line.
	 * @param line the line to intersect with
	 * @return the intersection point
	 * @throws DegenerateCaseException when this plane and the line are parallel
	 */
	public Vector3 intersect(Line3 line) {
		double denom=line.getDir().dot(getNormal());
		if (Math.abs(denom)<EPS) throw new DegenerateCaseException("Line and plane are parallel");
		double t=getPoint().minus(line.getPoint()).dot(getNormal())/denom;
		return line.getPointAt(t);
	}
	
	/**
	 * Returns the intersection of this plane and the argument.
	 * @param plane the plane to intersect with
	 * @return the intersection line
	 * @throws DegenerateCaseException when the planes are parallel
	 */
	public Line3 intersect(Plane3 plane) {
		Vector3 normalCross=getUnitNormal().cross(plane.getUnitNormal());
		if (normalCross.norm()<EPS) throw new DegenerateCaseException("Planes are parallel");
		double normalDot=getUnitNormal().dot(plane.getUnitNormal());
		double h1=getUnitNormal().dot(getPoint());
		double h2=plane.getUnitNormal().dot(plane.getPoint());
		
		double denom=1-normalDot*normalDot;
		double c1=(h1-h2*normalDot)/denom;
		double c2=(h2-h1*normalDot)/denom;
		return Line3.createFromDir(getUnitNormal().times(c1).plus(plane.getUnitNormal().times(c2)), normalCross);
	}
}

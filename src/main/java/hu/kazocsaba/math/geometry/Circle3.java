package hu.kazocsaba.math.geometry;

import hu.kazocsaba.math.matrix.MatrixFactory;
import hu.kazocsaba.math.matrix.Vector3;
import hu.kazocsaba.math.matrix.immutable.ImmutableVector3;

/**
 * A circle in 3D space. It is specified by the center C, the radius r, and the unit normal N of the plane containing
 * the circle.
 * @author Kazó Csaba
 */
public class Circle3 {
	private static final double EPS=1e-8;
	
	private final Line3 centerLine;
	private final double radius;

	Circle3(Vector3 center, Vector3 normal, double radius) {
		if (radius<=0) throw new IllegalArgumentException("Radius must be positive");
		double normalLength=normal.norm();
		if (normalLength<EPS) throw new IllegalArgumentException("Badly specified circle (normal vector too small)");
		
		centerLine=Line3.createFromDir(center, normal);
		this.radius = radius;
	}
	
	/**
	 * Creates a new circle.
	 * @param center the center
	 * @param normal the normal vector of the plane containing the circle; need not be unit length
	 * @param radius the radius of the circle
	 * @return the circle
	 * @throws IllegalArgumentException if the normal vector is too small or the radius is not positive
	 */
	public static Circle3 create(Vector3 center, Vector3 normal, double radius) {
		return new Circle3(center, normal, radius);
	}

	/**
	 * Returns the center of the circle.
	 * @return {@code C}
	 */
	public ImmutableVector3 getCenter() {
		return centerLine.getPoint();
	}

	/**
	 * Returns the radius of the circle.
	 * @return {@code r}
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * Returns the unit-length normal vector of the plane containing the circle.
	 * @return {@code N}
	 */
	public ImmutableVector3 getNormal() {
		return centerLine.getUnitDir();
	}
	
	
	/**
	 * Returns the point on the circumference of this circle described by an angle. The point corresponding to the
	 * angle zero is unspecified but fixed for a given circle.
	 * @param radians the angle specifying the point
	 * @return the point on the circle
	 */
	public Vector3 getPointAt(double radians) {
		Vector3 radialVector;
		
		// Rotate the normal vector by 90 degrees
		
		double xabs=Math.abs(getNormal().getX());
		double yabs=Math.abs(getNormal().getY());
		double zabs=Math.abs(getNormal().getZ());
		
		if (xabs>=yabs && xabs>=zabs) {
			// divide by the x coordinate
			radialVector=MatrixFactory.createVector(-(getNormal().getY()+getNormal().getZ())/getNormal().getX(), 1, 1);
		} else if (yabs>=zabs) {
			// divide by the y coordinate
			radialVector=MatrixFactory.createVector(1, -(getNormal().getX()+getNormal().getZ())/getNormal().getY(), 1);
		} else {
			// divide by the z coordinate
			radialVector=MatrixFactory.createVector(1, 1, -(getNormal().getX()+getNormal().getY())/getNormal().getZ());
		}
		radialVector.scale(radius/radialVector.norm());
		
		// Rotate the radialVector around the centerLine
		
		Vector3 p=centerLine.getRotation(radians).mul(radialVector);
		p.add(centerLine.getPoint());
		return p;
	}
	
	/**
	 * Returns the plane containing the circle.
	 * @return the plane of the circle
	 */
	public Plane3 getPlane() {
		return Plane3.create(centerLine.getPoint(), centerLine.getDir());
	}

	/**
	 * Returns a human-readable string representation of this circle.
	 * @return a string describing this circle
	 */
	@Override
	public String toString() {
		return String.format("Circle3[center=%s; radius=%s; normal=%s]", getCenter(), getRadius(), getNormal());
	}
	
}

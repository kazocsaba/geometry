package hu.kazocsaba.math.geometry;

import hu.kazocsaba.math.matrix.Matrix3;
import hu.kazocsaba.math.matrix.MatrixFactory;
import hu.kazocsaba.math.matrix.Vector3;
import hu.kazocsaba.math.matrix.immutable.ImmutableVector3;

/**
 * A line in 3D space. It is specified by a point P and a direction D through the formula
 * {@code P + t*D} where the {@code t} parameter is used to specify the points of the line.
 * D is not necessarily unit length.
 * @author Kazó Csaba
 */
public class Line3 extends Line {
	
	Line3(Vector3 point, Vector3 dir) {
		super(point, dir);
	}

	@Override
	public ImmutableVector3 getDir() {
		return (ImmutableVector3) super.getDir();
	}

	@Override
	public ImmutableVector3 getUnitDir() {
		return (ImmutableVector3) super.getUnitDir();
	}

	@Override
	public ImmutableVector3 getPoint() {
		return (ImmutableVector3) super.getPoint();
	}

	@Override
	public Vector3 getPointAt(double t) {
		return (Vector3) super.getPointAt(t);
	}

	@Override
	public Vector3 getPointWhereCoord(int coord, double value) {
		return (Vector3) super.getPointWhereCoord(coord, value);
	}
	
	/**
	 * Creates a new line from a point and a direction.
	 * @param point a point on the line
	 * @param dir the direction of the line
	 * @return the new line
	 * @throws IllegalArgumentException if the length of the direction vector is too small
	 */
	public static Line3 createFromDir(Vector3 point, Vector3 dir) {
		return new Line3(point, dir);
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
	public static Line3 createFromTwoPoints(Vector3 p1, Vector3 p2) {
		return new Line3(p1, p2.minus(p1));
	}
	
	/**
	 * Returns the matrix which rotates around the direction of this line with the
	 * specified angle. See this <a href="http://en.wikipedia.org/wiki/Rotation_matrix#Rotation_matrix_from_axis_and_angle">
	 * Wikipedia link</a>.
	 * <p>
	 * For example, if {@code getDir()=(0,0,1)} and {@code R=getRotation(Math.PI/2)}, then {@code R*(1,1,0)=(-1,1,0)}.
	 * @param angle the angle in radians
	 * @return the rotation matrix
	 */
	public Matrix3 getRotation(double angle) {
		double c=Math.cos(angle);
		double mc=1-c;
		double s=Math.sin(angle);
		Vector3 u=getDir().times(1/getDir().norm());
		Matrix3 r=MatrixFactory.createMatrix3();
		double x=u.getX(), y=u.getY(), z=u.getZ();
		
		r.set(0, 0, c+x*x*mc);
		r.set(0, 1, x*y*mc-z*s);
		r.set(0, 2, x*z*mc+y*s);
		r.set(1, 0, y*x*mc+z*s);
		r.set(1, 1, c+y*y*mc);
		r.set(1, 2, y*z*mc-x*s);
		r.set(2, 0, z*x*mc-y*s);
		r.set(2, 1, z*y*mc+x*s);
		r.set(2, 2, c+z*z*mc);
		return r;
	}
}

package hu.kazocsaba.math.geometry;

import hu.kazocsaba.math.matrix.MatrixFactory;
import hu.kazocsaba.math.matrix.Vector;
import hu.kazocsaba.math.matrix.immutable.ImmutableMatrixFactory;
import hu.kazocsaba.math.matrix.immutable.ImmutableVector;

/**
 * An infinite line. It is specified by a point P and a direction D through the formula
 * {@code P + t*D} where the {@code t} parameter is used to specify the points of the line.
 * D is not necessarily unit length.
 * @author Kazó Csaba
 */
public class Line {
	protected static final double EPS=1e-8;
	
	private final ImmutableVector point;
	private final ImmutableVector dir;
	private final ImmutableVector unitDir;
	
	Line(Vector point, Vector dir) {
		this.point=ImmutableMatrixFactory.copy(point);
		this.dir=ImmutableMatrixFactory.copy(dir);
		double dirNorm=this.dir.norm();
		if (dirNorm<EPS) throw new IllegalArgumentException("Badly specified line (direction vector too small)");
		unitDir = ImmutableMatrixFactory.copy(this.dir.times(1/dirNorm));
	}
	
	/**
	 * Returns the point {@code P} specifying the line.
	 * @return {@code P}
	 */
	public ImmutableVector getPoint() {return point;}
	/**
	 * Returns the direction {@code D} of the line. The length of the direction vector is not necessarily one.
	 * @return {@code D}
	 */
	public ImmutableVector getDir() {return dir;}
	
	/**
	 * Returns the unit vector specifying the direction of the line. The returned vector is identical to
	 * <blockquote>{@code getDir().normalized()}.</blockquote>
	 * @return the direction of the line as a unit vector
	 */
	public ImmutableVector getUnitDir() {return unitDir;}
	/**
	 * Returns the point on this line corresponding to the parameter value. The parameter {@code t} is linear,
	 * its value is 0 at {@code point} and 1 at {@code point+dir}.
	 * @param t the parameter value
	 * @return {@code P + t*D}
	 */
	public Vector getPointAt(double t) {
		return getPoint().plus(getDir().times(t));
	}
	/**
	 * Returns the point of the line for which the specified coordinate has a given value.
	 * @param coord the index of the coordinate
	 * @param value the value of the coordinate
	 * @return the requested point of this line
	 * @throws IllegalArgumentException if the index is invalid
	 * @throws DegenerateCaseException when this line is parallel with the
	 * {@code <coord> = value} plane
	 */
	public Vector getPointWhereCoord(int coord, double value) {
		if (coord<0 || coord>=getPoint().getDimension()) throw new IllegalArgumentException();
		Vector v=MatrixFactory.createLike(getPoint());
		
		if (Math.abs(getDir().getCoord(coord))<EPS)
			throw new DegenerateCaseException("No such point");
		
		double t=(value-getPoint().getCoord(coord))/getDir().getCoord(coord);
		for (int i=0; i<v.getDimension(); i++)
			v.setCoord(i, getPoint().getCoord(i)+t*getDir().getCoord(i));
		
		return v;
	}
	/**
	 * Returns the parameter value specifying the point of the line which is closest to the argument. It is given by
	 * the expression
	 * <blockquote>{@code p.minus(getPoint()).dot(getUnitDir())/getDir().norm()}.</blockquote>
	 * @param p an arbitrary point
	 * @return the parameter value {@code t} for which {@link getPointAt(t)} is the point of this line closest to {@code p}.
	 */
	public double getClosestT(Vector p) {
		return p.minus(getPoint()).dot(getUnitDir())/getDir().norm();
	}
	/**
	 * Returns the distance between this line and a point.
	 * @param p a point
	 * @return the distance of {@code p} from this line
	 * @throws IllegalArgumentException if the point is not in the same space as this line (e.g. one is 2D and the other is 3D)
	 */
	public double distance(Vector p) {
		Vector closest=getPointAt(getClosestT(p));
		return closest.error(p);
	}
	/**
	 * Returns the distance between this line and the argument.
	 * @param line the other line
	 * @throws IllegalArgumentException if the two lines are not in the same space (e.g. one is 2D and the other is 3D)
	 */
	public double distance(Line line) {
		if (line.getPoint().getDimension()!=getPoint().getDimension())
			throw new IllegalArgumentException("Dimension mismatch: "+getPoint().getDimension()+" != "+line.getPoint().getDimension());
		
		Vector dp=getPoint().minus(line.getPoint()); // p1-p2
		
		Vector d1=getDir();
		Vector d2=line.getDir();
		double d1d2=d1.dot(d2);
		double d1d1=d1.dot(d1);
		double d2d2=d2.dot(d2);
		
		double denom=(d1d1-d1d2*d1d2/d2d2);
		double t1;
		if (Math.abs(denom)<EPS)
			// the two lines are parallel
			t1=0;
		else
			t1=d2.times(d1d2/d2d2).minus(d1).dot(dp)/denom;
		double t2=(d2.dot(dp)+t1*d1d2)/d2d2;
		
		return dp.plus(d1.times(t1)).minus(d2.times(t2)).norm();
	}
}

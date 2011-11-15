package hu.kazocsaba.math.geometry;

/**
 * Thrown when no solution could be returned due to special circumstances. There may be no
 * solutions or infinite solutions.
 * For example, {@link Plane3.intersect(Line3)} throws this exception when
 * the line is parallel with the plane.
 * @author Kazó Csaba
 */
public class DegenerateCaseException extends RuntimeException {

	/**
	 * Creates a new instance of <code>DegenerateCaseException</code> without detail message.
	 */
	public DegenerateCaseException() {
	}

	/**
	 * Constructs an instance of <code>DegenerateCaseException</code> with the specified detail message.
	 * @param msg the detail message.
	 */
	public DegenerateCaseException(String msg) {
		super(msg);
	}
}

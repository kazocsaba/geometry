package hu.kazocsaba.math.geometry;

import hu.kazocsaba.math.matrix.Vector2;
import hu.kazocsaba.math.matrix.immutable.ImmutableMatrixFactory;
import hu.kazocsaba.math.matrix.immutable.ImmutableVector2;

/**
 * An axis-aligned 2D rectangle. It is specified by the position of its bottom left corner and its width and height.
 * @author Kazó Csaba
 */
public final class Box2 {
	private static final double EPS=1e-8;
	
	private final ImmutableVector2 point;
	private final double width, height;

	/**
	 * Creates a new box.
	 * @param point the bottom left corner
	 * @param width the width
	 * @param height the height
	 * @throws IllegalArgumentException if either {@code width} or {@code height} is negative or too small
	 */
	public Box2(Vector2 point, double width, double height) {
		if (width<EPS || height<EPS) throw new IllegalArgumentException("Box must have positive size");
		this.point = ImmutableMatrixFactory.copy(point);
		this.width = width;
		this.height = height;
	}

	/**
	 * Returns the bottom left corner of this box.
	 * @return the bottom left corner
	 */
	public ImmutableVector2 getPoint() {
		return point;
	}
	
	/**
	 * Returns the top left corner of this box. It is the point inside the box with the minimal x and maximal y coordinates.
	 * @return the top left corner
	 */
	public ImmutableVector2 getTopLeft() {
		return ImmutableMatrixFactory.createVector(point.getX(), point.getY()+height);
	}
	/**
	 * Returns the top right corner of this box. It is the point inside the box with the maximal x and y coordinates.
	 * @return the top right corner
	 */
	public ImmutableVector2 getTopRight() {
		return ImmutableMatrixFactory.createVector(point.getX()+width, point.getY()+height);
	}
	/**
	 * Returns the bottom right corner of this box. It is the point inside the box with the maximal x and minimal y coordinates.
	 * @return the bottom right corner
	 */
	public ImmutableVector2 getBottomRight() {
		return ImmutableMatrixFactory.createVector(point.getX()+width, point.getY());
	}
	/**
	 * Returns the bottom left corner of this box. It is the point inside the box with the minimal x and y coordinates.
	 * @return the bottom left corner
	 */
	public ImmutableVector2 getBottomLeft() {
		return point;
	}

	/**
	 * Returns the width of this box.
	 * @return the width
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * Returns the height of this box.
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}
	
	/**
	 * Returns the intersection of a line and this box, or {@code null} if they do not intersect.
	 * @param line the line
	 * @return the intersection as a line segment
	 */
	public Segment2 intersect(Line2 line) {
		Vector2 lineDir=line.getDir();
		
		try {
			if (Math.abs(lineDir.getY())>Math.abs(lineDir.getX())) {
				// vertical
				double xtop=line.getXforY(getPoint().getY());
				double xbot=line.getXforY(getPoint().getY()+getHeight());

				int xpostop=0, xposbot=0;
				if (xtop>getPoint().getX()+getWidth())
					xpostop=1;
				else if (xtop<getPoint().getX())
					xpostop=-1;
				if (xbot>getPoint().getX()+getWidth())
					xposbot=1;
				else if (xbot<getPoint().getX())
					xposbot=-1;

				if (xpostop*xposbot==1)
					return null;
				if (xpostop*xposbot==-1)
					return new Segment2(line.getPointWhereCoord(0, getPoint().getX()), line.getPointWhereCoord(0, getPoint().getX()+getWidth()));

				if (xpostop==-1) {
					return new Segment2(line.getPointWhereCoord(0, getPoint().getX()), ImmutableMatrixFactory.createVector(xbot, getPoint().getY()+getHeight()));
				} else if (xpostop==1) {
					return new Segment2(line.getPointWhereCoord(0, getPoint().getX()+getWidth()), ImmutableMatrixFactory.createVector(xbot, getPoint().getY()+getHeight()));
				} else if (xposbot==-1) {
					return new Segment2(ImmutableMatrixFactory.createVector(xtop, getPoint().getY()), line.getPointWhereCoord(0, getPoint().getX()));
				} else if (xposbot==1) {
					return new Segment2(ImmutableMatrixFactory.createVector(xtop, getPoint().getY()), line.getPointWhereCoord(0, getPoint().getX()+getWidth()));
				} else {
					return new Segment2(ImmutableMatrixFactory.createVector(xtop, getPoint().getY()), ImmutableMatrixFactory.createVector(xbot, getPoint().getY()+getHeight()));
				}
			} else {
				// horizontal
				double ytop=line.getYforX(getPoint().getX());
				double ybot=line.getYforX(getPoint().getX()+getWidth());

				int ypostop=0, yposbot=0;
				if (ytop>getPoint().getY()+getHeight())
					ypostop=1;
				else if (ytop<getPoint().getY())
					ypostop=-1;
				if (ybot>getPoint().getY()+getHeight())
					yposbot=1;
				else if (ybot<getPoint().getY())
					yposbot=-1;

				if (ypostop*yposbot==1)
					return null;
				if (ypostop*yposbot==-1)
					return new Segment2(line.getPointWhereCoord(1, getPoint().getY()), line.getPointWhereCoord(1, getPoint().getY()+getHeight()));

				if (ypostop==-1) {
					return new Segment2(ImmutableMatrixFactory.createVector(getPoint().getX()+getWidth(), ybot), line.getPointWhereCoord(1, getPoint().getY()));
				} else if (ypostop==1) {
					return new Segment2(ImmutableMatrixFactory.createVector(getPoint().getX()+getWidth(), ybot), line.getPointWhereCoord(1, getPoint().getY()+getHeight()));
				} else if (yposbot==-1) {
					return new Segment2(ImmutableMatrixFactory.createVector(getPoint().getX(), ytop), line.getPointWhereCoord(1, getPoint().getY()));
				} else if (yposbot==1) {
					return new Segment2(ImmutableMatrixFactory.createVector(getPoint().getX(), ytop), line.getPointWhereCoord(1, getPoint().getY()+getHeight()));
				} else {
					return new Segment2(ImmutableMatrixFactory.createVector(getPoint().getX(), ytop), ImmutableMatrixFactory.createVector(getPoint().getX()+getWidth(), ybot));
				}
			}
		} catch (IllegalArgumentException e) {
			// couldn't construct a Segment2; this means that the intersection is a single point
			return null;
		}
	}
	
}

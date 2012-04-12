package oop.battle.brain;

public class Point {
	private double x, y;

	public Point(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getAngleFrom(Point point) {
		return point.getAngleTo(this);
	}

	public double getAngleTo(Point point) {
		return Math.atan2(this.getY() - point.getY(),
				this.getX() - point.getX())
				+ Math.PI;
	}

	public double getDistanceTo(Point point) {
		return Math.sqrt((point.getX() - this.getX())
				* (point.getX() - this.getX()) + (point.getY() - this.getY())
				* (point.getY() - this.getY()));
	}

}

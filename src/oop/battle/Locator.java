package oop.battle;

import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import oop.battle.brain.ILocator;
import oop.battle.brain.Point;

public class Locator extends RobotPart implements ILocator {

	private double range, length;
	private double angle, newAngle;
	private List<Point> data = new ArrayList<Point>();

	public Locator(BattleField battleField, double range, double length) {
		super(battleField);
		this.range = range;
		this.length = length;
		this.angle = this.newAngle = Math.random() * Math.PI * 2;
	}

	@Override
	public void setLocatorAngle(double angle) {
		this.newAngle = angle;
	}

	@Override
	public double getLocatorRange() {
		return range;
	}

	@Override
	public double getLocatorLength() {
		return length;
	}

	@Override
	public List<Point> getLocatorData() {
		return data;
	}

	void refreshLocatorData() {
		List<Point> result = new ArrayList<Point>();
		final Point me = new Point(robot.getRobotX(), robot.getRobotY());
		result.add(me);

		Polygon polygon = new Polygon();
		polygon.addPoint((int) me.getX(), (int) me.getY());
		polygon.addPoint((int) (me.getX() + Math.cos(angle - range / 2)
				* length), (int) (me.getY() + Math.sin(angle - range / 2)
				* length));
		polygon.addPoint((int) (me.getX() + Math.cos(angle) * length),
				(int) (me.getY() + Math.sin(angle / 2) * length));
		polygon.addPoint((int) (me.getX() + Math.cos(angle + range / 2)
				* length), (int) (me.getY() + Math.sin(angle + range / 2)
				* length));

		for (Robot r : battleField.getRobots()) {
			if (r == robot) {
				continue;
			}
			if (polygon.contains(new java.awt.Point((int) r.getRobotX(),
					(int) r.getRobotY()))) {
				result.add(new Point(r.getRobotX(), r.getRobotY()));
			}
		}

		Collections.sort(result, new Comparator<Point>() {

			@Override
			public int compare(Point o1, Point o2) {
				double d1 = me.getDistanceTo(o1);
				double d2 = me.getDistanceTo(o2);
				return Double.compare(d1, d2);
			}
		});

		this.data = result;
	}

	@Override
	public double getBattleFieldWidth() {
		return battleField.getWidth();
	}

	@Override
	public double getBattleFieldHeight() {
		return battleField.getHeight();
	}

	@Override
	public double getPaintableAngle() {
		return angle;
	}

	@Override
	public String getPaintableView() {
		return "locator";
	}

	@Override
	public void battleStep() {
		angle = newAngle;
		refreshLocatorData();
	}

}

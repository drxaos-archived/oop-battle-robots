package oop.battle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ui.Viewable;

public class BattleField {

	public static final String NOISE = "NOISE - NOISE - NOISE - NOISE - NOISE";

	private double width, height;

	private List<Robot> robots;
	private List<Bullet> bullets;
	private Map<Double, String> air;

	private Viewable scene;

	public BattleField(Viewable viewable, double width, double height) {
		this.scene = viewable;
		this.width = width;
		this.height = height;
		robots = new ArrayList<Robot>();
		bullets = new ArrayList<Bullet>();
		air = new HashMap<Double, String>();
	}

	void clearAir() {
		air.clear();
	}

	void publishMessage(double freq, String msg) {
		if (!air.containsKey(freq))
			air.put(freq, msg);
		else
			air.put(freq, NOISE);
	}

	String listenMessage(double freq) {
		return air.get(freq);
	}

	public void addRobot(Robot robot) {
		robots.add(robot);
		scene.addPaintable(robot);
	}

	public void removeRobot(Robot robot) {
		robots.remove(robot);
		scene.removePaintable(robot);
	}

	Robot[] getRobots() {
		return robots.toArray(new Robot[0]);
	}

	Bullet[] getBullets() {
		return bullets.toArray(new Bullet[0]);
	}

	public void addBullet(Bullet bullet) {
		bullets.add(bullet);
		scene.addPaintable(bullet);
	}

	public void removeBullet(Bullet bullet) {
		bullets.remove(bullet);
		scene.removePaintable(bullet);
	}

	double getHeight() {
		return height;
	}

	double getWidth() {
		return width;
	}

	void endStep() {
		scene.requestRepaint();
	}
}

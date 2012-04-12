package oop.battle;

import java.util.List;

import ui.Paintable;

public class Bullet implements Paintable {

	private Robot robot;
	private double x, y, angle, speed, life, damage;

	public Bullet(Robot robot, double x, double y, double angle, double speed,
			double life, double damage) {
		this.robot = robot;
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.speed = speed;
		this.life = life;
		this.damage = damage;
	}

	Robot getRobot() {
		return robot;
	}

	double getBulletX() {
		return x;
	}

	double getBulletY() {
		return y;
	}

	double getBulletAngle() {
		return angle;
	}

	double getBulletSpeed() {
		return speed;
	}

	double getBulletLife() {
		return life;
	}

	void setBulletLife(double life) {
		this.life = life;
	}

	double getBulletDamage() {
		return damage;
	}

	void moveBullet(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public double getPaintableX() {
		return x;
	}

	@Override
	public double getPaintableY() {
		return y;
	}

	@Override
	public double getPaintableAngle() {
		return angle;
	}

	@Override
	public String getPaintableView() {
		return "bullet";
	}

	@Override
	public List<Paintable> getPaintableChildren() {
		return null;
	}

}

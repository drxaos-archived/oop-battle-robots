package oop.battle;

import oop.battle.brain.IChassis;

public class Chassis extends RobotPart implements IChassis {

	private double maxSpeed;
	private double speed, angle;

	public Chassis(BattleField battleField, double maxSpeed) {
		super(battleField);
		this.maxSpeed = maxSpeed;
		this.speed = 0;
		this.angle = Math.random() * Math.PI * 2;
	}

	double getChassisSpeed() {
		return speed;
	}

	double getChassisAngle() {
		return angle;
	}

	public void setChassisSpeed(double speed) {
		if (speed < 0)
			speed = 0;
		if (speed > maxSpeed)
			speed = maxSpeed;
		this.speed = speed;
	}

	public void setChassisAngle(double angle) {
		this.angle = angle;
	}

	public double getChassisMaxSpeed() {
		return maxSpeed;
	}

	@Override
	public void battleStep() {
	}

}

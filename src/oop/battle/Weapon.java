package oop.battle;

import oop.battle.brain.IWeapon;

public class Weapon extends RobotPart implements IWeapon {

	private double damage, speed, length, charge;
	private double angle, charged;
	private boolean fire;

	public Weapon(BattleField battleField, double damage, double speed,
			double lenght, double charge) {
		super(battleField);
		this.damage = damage;
		this.speed = speed;
		this.length = lenght;
		this.charge = charge;
		this.charged = 0;
	}

	double getWeaponAngle() {
		return angle;
	}

	@Override
	public void setWeaponAngle(double angle) {
		this.angle = angle;
	}

	@Override
	public double getWeaponDamage() {
		return damage;
	}

	@Override
	public double getWeaponSpeed() {
		return speed;
	}

	@Override
	public double getWeaponCharge() {
		return charge;
	}

	@Override
	public double getWeaponCharged() {
		return charged;
	}

	@Override
	public double getWeaponMaxLength() {
		return length;
	}

	@Override
	public void doWeaponFire() {
		fire = true;
	}

	@Override
	public void battleStep() {
		if (charged < charge) {
			charged++;
		}
		if (fire && charged >= charge) {
			Bullet bullet = new Bullet(robot, robot.getRobotX(),
					robot.getRobotY(), angle, speed, length / speed, damage);
			battleField.addBullet(bullet);
			fire = false;
			charged = 0;
		}
	}

}

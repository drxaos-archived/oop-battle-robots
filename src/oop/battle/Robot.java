package oop.battle;

import java.util.ArrayList;
import java.util.List;

import oop.battle.brain.IBrain;
import oop.battle.brain.IRobot;
import oop.battle.brain.Point;
import oop.battle.concurrent.Thinker;
import ui.Paintable;

public class Robot implements IRobot, Paintable {

	private Chassis chassis;
	private Locator locator;
	private Weapon weapon;
	private IBrain brain;
	private double x, y, radius;
	private List<Paintable> paintableChildren;
	private double armor;
	private Thinker thinker;

	public Robot(Chassis chassis, Locator locator, Weapon weapon, IBrain brain,
			double x, double y, double radius, double armor) {
		this.chassis = chassis;
		chassis.attachTo(this);
		this.locator = locator;
		locator.attachTo(this);
		this.weapon = weapon;
		weapon.attachTo(this);
		this.brain = brain;
		brain.attachToRobot(this);
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.armor = armor;

		thinker = new Thinker(this);
		thinker.start();

		paintableChildren = new ArrayList<Paintable>();
		paintableChildren.add(chassis);
		paintableChildren.add(locator);
		paintableChildren.add(weapon);
	}

	Chassis getChassis() {
		return chassis;
	}

	Locator getLocator() {
		return locator;
	}

	Weapon getWeapon() {
		return weapon;
	}

	IBrain getBrain() {
		return brain;
	}

	public Thinker getThinker() {
		return thinker;
	}

	public void think() {
		brain.think();
	}

	public void setChassisSpeed(double speed) {
		chassis.setChassisSpeed(speed);
	}

	public void setChassisAngle(double angle) {
		chassis.setChassisAngle(angle);
	}

	public double getChassisMaxSpeed() {
		return chassis.getChassisMaxSpeed();
	}

	public void setLocatorAngle(double angle) {
		locator.setLocatorAngle(angle);
	}

	public double getLocatorRange() {
		return locator.getLocatorRange();
	}

	public double getLocatorLength() {
		return locator.getLocatorLength();
	}

	public List<Point> getLocatorData() {
		return locator.getLocatorData();
	}

	public void setWeaponAngle(double angle) {
		weapon.setWeaponAngle(angle);
	}

	public double getWeaponDamage() {
		return weapon.getWeaponDamage();
	}

	public double getWeaponCharge() {
		return weapon.getWeaponCharge();
	}

	@Override
	public double getWeaponCharged() {
		return weapon.getWeaponCharged();
	}

	public double getWeaponSpeed() {
		return weapon.getWeaponSpeed();
	}

	public double getWeaponMaxLength() {
		return weapon.getWeaponMaxLength();
	}

	public void doWeaponFire() {
		weapon.doWeaponFire();
	}

	@Override
	public double getBattleFieldWidth() {
		return locator.getBattleFieldWidth();
	}

	@Override
	public double getBattleFieldHeight() {
		return locator.getBattleFieldHeight();
	}

	double getRobotX() {
		return x;
	}

	double getRobotY() {
		return y;
	}

	double getRobotRadius() {
		return radius;
	}

	void moveRobot(double x, double y) {
		this.x = x;
		this.y = y;
	}

	double getRobotArmor() {
		return armor;
	}

	void setRobotArmor(double armor) {
		this.armor = armor;
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
		return chassis.getChassisAngle();
	}

	@Override
	public String getPaintableView() {
		return "robot";
	}

	@Override
	public List<Paintable> getPaintableChildren() {
		return paintableChildren;
	}

	void battleStep() {
		chassis.battleStep();
		locator.battleStep();
		weapon.battleStep();
	}

}

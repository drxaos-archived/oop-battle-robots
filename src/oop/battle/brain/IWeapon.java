package oop.battle.brain;

public interface IWeapon {

	void setWeaponAngle(double angle);

	double getWeaponDamage();

	double getWeaponCharge();

	double getWeaponCharged();

	double getWeaponSpeed();

	double getWeaponMaxLength();

	void doWeaponFire();
}

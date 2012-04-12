package oop.battle.brain.test;

import oop.battle.brain.Point;
import oop.battle.brain.common.DefaultBrain;

public class RandomBrain extends DefaultBrain {

	protected double locatorAngle;

	@Override
	public void think() {
		super.think();
		if (targets.size() > 0) {
			double x = targets.get(0).getX();
			double y = targets.get(0).getY();
			double a = loc.getAngleTo(targets.get(0));
			double d = targets.get(0).getDistanceTo(loc);
			if (d > robot.getWeaponMaxLength() * 0.5) {
				moveTarget = new Point(x - Math.cos(a)
						* robot.getWeaponMaxLength() * 0.3, y - Math.sin(a)
						* robot.getWeaponMaxLength() * 0.3);
			}
			robot.setLocatorAngle(locatorAngle = a);
			if (d <= robot.getWeaponMaxLength()
					&& robot.getWeaponCharged() >= robot.getWeaponCharge()) {
				robot.setWeaponAngle(locatorAngle);
				robot.doWeaponFire();
			}
		} else if (moveTarget == null) {
			moveTarget = new Point(Math.random() * robot.getBattleFieldWidth(),
					Math.random() * robot.getBattleFieldHeight());
		} else {
			robot.setLocatorAngle(locatorAngle += robot.getLocatorRange());
		}
	}
}

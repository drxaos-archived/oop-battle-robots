package oop.battle.brain.common;

import java.util.List;

import oop.battle.brain.IBrain;
import oop.battle.brain.IRobot;
import oop.battle.brain.Point;

public class DefaultBrain implements IBrain {

	protected IRobot robot;

	@Override
	public void attachToRobot(IRobot robot) {
		this.robot = robot;
	}

	protected Point loc;
	protected List<Point> targets;
	protected Point moveTarget;

	protected void defaultLocate() {
		List<Point> data = robot.getLocatorData();
		if (data.size() == 0) {
			loc = new Point(-1, -1);
		} else {
			loc = data.remove(0);
		}
		targets = data;
	}

	protected void defaultMove() {
		if (moveTarget != null) {
			double a = loc.getAngleTo(moveTarget);
			double d = loc.getDistanceTo(moveTarget);
			if (d > 3) {
				robot.setChassisAngle(a);
				robot.setChassisSpeed(d);
			} else {
				robot.setChassisSpeed(0);
				moveTarget = null;
			}
		}
	}

	@Override
	public void think() {
		defaultLocate();
		defaultMove();
	}

}

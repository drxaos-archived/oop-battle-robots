package oop.battle;

import java.util.List;

import ui.Paintable;

abstract public class RobotPart implements Paintable {

	protected BattleField battleField;
	protected Robot robot;

	public RobotPart(BattleField battleField) {
		this.battleField = battleField;
	}

	public void attachTo(Robot robot) {
		this.robot = robot;
	}

	public double getPaintableX() {
		return robot.getPaintableX();
	}

	public double getPaintableY() {
		return robot.getPaintableY();
	}

	public double getPaintableAngle() {
		return robot.getPaintableAngle();
	}

	public String getPaintableView() {
		return null;
	}

	public List<Paintable> getPaintableChildren() {
		return null;
	}

	abstract public void battleStep();
}

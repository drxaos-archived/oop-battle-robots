package oop;

import oop.battle.BattleController;
import oop.battle.BattleField;
import oop.battle.Chassis;
import oop.battle.Locator;
import oop.battle.Robot;
import oop.battle.Weapon;
import oop.battle.brain.test.RandomBrain;
import ui.Scene;
import ui.Viewable;

public class BattleMain {
	public static void main(String[] args) {
		double W = 500;
		double H = 500;
		Viewable scene = new Scene((int) W, (int) H, "img", "bg");
		BattleField battleField = new BattleField(scene, W, H);
		BattleController controller = new BattleController(battleField);

		for (int i = 0; i < 10; i++) {
			Robot robot = new Robot(new Chassis(battleField, 10), new Locator(
					battleField, 60 * Math.PI / 180, 150), new Weapon(
					battleField, 10, 25, 150, 20), new RandomBrain(),
					Math.random() * W, Math.random() * W, 10, 100);
			battleField.addRobot(robot);
		}
		controller.startBattle();
	}
}

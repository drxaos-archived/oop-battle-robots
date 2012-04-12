package oop.battle;

import oop.battle.brain.Point;

public class BattleController {

	private BattleField battleField;

	public BattleController(BattleField battleField) {
		this.battleField = battleField;
	}

	public void startBattle() {

		int step = 0, ACCURACY = 3;
		while (true) {
			long ts = System.currentTimeMillis();
			step = (step + 1) % ACCURACY;

			// Get objects
			Robot[] robots = battleField.getRobots();
			Bullet[] bullets = battleField.getBullets();

			if (step == 0) {
				// Think
				for (Robot robot : robots) {
					if (!robot.getThinker().isRunning()) {
						robot.getThinker().restart();
					}
				}
			}
			if (step == ACCURACY - 1) {
				// Step
				battleField.clearAir();
				for (Robot robot : robots) {
					if (!robot.getThinker().isRunning()) {
						robot.battleStep();
					}
				}
			}

			// Robots
			for (Robot robot : robots) {
				// Move
				double speed = robot.getChassis().getChassisSpeed()
						/ (double) ACCURACY;
				double angle = robot.getChassis().getChassisAngle();
				double x = robot.getRobotX();
				double y = robot.getRobotY();
				x = x + Math.cos(angle) * speed;
				y = y + Math.sin(angle) * speed;
				if (x < 0)
					x = 0;
				if (y < 0)
					y = 0;
				if (x > battleField.getWidth())
					x = battleField.getWidth();
				if (y > battleField.getHeight())
					y = battleField.getHeight();
				robot.moveRobot(x, y);
			}

			// Bullets
			for (Bullet bullet : bullets) {
				// Move
				double speed = bullet.getBulletSpeed() / (double) ACCURACY;
				double angle = bullet.getBulletAngle();
				double x = bullet.getBulletX();
				double y = bullet.getBulletY();
				x = x + Math.cos(angle) * speed;
				y = y + Math.sin(angle) * speed;
				if (x < 0)
					x = 0;
				if (y < 0)
					y = 0;
				if (x > battleField.getWidth())
					x = battleField.getWidth();
				if (y > battleField.getHeight())
					y = battleField.getHeight();
				bullet.moveBullet(x, y);

				// Collisions
				Point bulletPoint = new Point(x, y);
				for (Robot robot : robots) {
					if (bullet.getRobot() == robot) {
						continue;
					}
					if (robot.getRobotRadius() >= bulletPoint
							.getDistanceTo(new Point(robot.getRobotX(), robot
									.getRobotY()))) {
						double armor = robot.getRobotArmor()
								- bullet.getBulletDamage();
						battleField.removeBullet(bullet);
						robot.setRobotArmor(armor);
						if (armor < 0) {
							battleField.removeRobot(robot);
						}
					}
				}

				// Bullet distance
				double life = bullet.getBulletLife() - 1 / (double) ACCURACY;
				bullet.setBulletLife(life);
				if (life <= 0) {
					battleField.removeBullet(bullet);
				}
			}

			// Repaint
			battleField.endStep();

			// Sleep
			synchronized (this) {
				try {
					long to = ts - System.currentTimeMillis() + 50;
					if (to > 0)
						this.wait(to);
				} catch (InterruptedException e) {
				}
			}
		}
	}
}

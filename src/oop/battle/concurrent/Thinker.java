package oop.battle.concurrent;

import oop.battle.Robot;

public class Thinker extends Thread {
	public class Lock {

		private boolean isLocked = false;

		public synchronized void lock() {
			while (isLocked) {
				try {
					wait();
				} catch (InterruptedException e) {
				}
			}
			isLocked = true;
		}

		public synchronized void unlock() {
			isLocked = false;
			notify();
		}

		public synchronized boolean isLocked() {
			return isLocked;
		}
	}

	private Lock lock = new Lock();
	private Robot robot;

	public Thinker(Robot robot) {
		super();
		this.robot = robot;
	}

	public boolean isRunning() {
		return !lock.isLocked();
	}

	public void restart() {
		lock.unlock();
	}

	@Override
	public void run() {
		while (true) {
			lock.lock();
			robot.think();
		}
	}
}

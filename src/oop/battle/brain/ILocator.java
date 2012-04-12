package oop.battle.brain;

import java.util.List;

public interface ILocator {

	void setLocatorAngle(double angle);

	double getLocatorRange();

	double getLocatorLength();

	List<Point> getLocatorData();

	double getBattleFieldWidth();

	double getBattleFieldHeight();

}

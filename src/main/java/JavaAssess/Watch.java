package JavaAssess;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Watch {
	private List<Location> listOfInterestingLocationsGround = new ArrayList<>();
	private List<Location> listOfMonsters = new ArrayList<>();

	private double objPosX = 0;
	private double objPosY = 0;
	// private double objPosZ = 0;
	private double objPosGold = 0;
	private int currentObject = 0;

	Watch() {
		createNextObject(0, 0);
	}

	private void createNextObject(double playerXPos, double playerYPos) {
		double isMinusX;
		double isMinusY;
		Location locationRefVar = new Location();
		listOfInterestingLocationsGround.add(locationRefVar);
		isMinusX = Math.round(Math.random());
		isMinusY = Math.round(Math.random());
		locationRefVar.xPosOfLocation = (Math.random() * 4 + 1d) * Math.pow((-1d), isMinusX) + playerXPos;
		locationRefVar.yPosOfLocation = (Math.random() * 4 + 1d) * Math.pow((-1d), isMinusY) + playerYPos;
		// locationRefVar.zPosOfLocation = Math.random() * 4 + 1d;
		locationRefVar.gold = Math.round(Math.random() * 50);

	}

	public double getDistance(double playerXPos, double playerYPos) {
		objPosX = listOfInterestingLocationsGround.get(currentObject).xPosOfLocation;
		objPosY = listOfInterestingLocationsGround.get(currentObject).yPosOfLocation;
		objPosGold = 0;
		double xDistanceTo = (objPosX - playerXPos);
		double yDistanceTo = (objPosY - playerYPos);
		double totalDistance = Math.sqrt((xDistanceTo * xDistanceTo + yDistanceTo * yDistanceTo));
		System.out.println("Watch Reads: " + new DecimalFormat("0.##").format(totalDistance));
		if (totalDistance < 1) {
			System.out.println("You have arrived at an Interesting Location!");
			objPosGold = listOfInterestingLocationsGround.get(currentObject).gold;
			System.out.println("Gold recieved is: " + objPosGold);
			createNextObject(playerXPos, playerYPos);
			currentObject++;
			objPosX = listOfInterestingLocationsGround.get(currentObject).xPosOfLocation;
			objPosY = listOfInterestingLocationsGround.get(currentObject).yPosOfLocation;
			xDistanceTo = (objPosX - playerXPos);
			yDistanceTo = (objPosY - playerYPos);
			totalDistance = Math.sqrt((xDistanceTo * xDistanceTo + yDistanceTo * yDistanceTo));
			System.out.println("Number of Locations Visited: " + currentObject);
			System.out.println(
					"Distance to Next Interesting Location is: " + new DecimalFormat("0.##").format(totalDistance));
		}

		return objPosGold;
	}
}

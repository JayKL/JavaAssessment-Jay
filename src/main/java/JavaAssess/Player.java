package JavaAssess;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Player {
	private double playerXPos = 0;
	private double playerYPos = 0;
	private double playerGold = 20;
	private double stepsize = 0.8d;
	private List<Items> listOfItems = new ArrayList<>();

	public void updateGold(double amountSpent) {
		playerGold = playerGold - amountSpent;
		System.out.println("Gold Left: " + playerGold);
	}

	public static void main(String xyz[]) {
		String directionPlayerSelected;
		String stepSizeSelection;
		Items itemNameSelection;
		int itemSelection;
		boolean continuteOrExit = true;
		Scanner scannerRefVar = new Scanner(System.in);
		Watch watchRefVar = new Watch();
		Player playerRefVar = new Player();
		Shop shopRefVar = new Shop(playerRefVar);

		System.out.println("You wake up in a grey and dreay swamp, earn enough gold to buy the wings and escape!");
		while (continuteOrExit == true) {

			playerRefVar.playerGold += watchRefVar.getDistance(playerRefVar.playerXPos, playerRefVar.playerYPos);
			System.out.println("select next action 'north' 'south' 'east' 'west' 'stats' 'inventory' 'shop' 'give up'");
			directionPlayerSelected = scannerRefVar.nextLine();
			directionPlayerSelected.toLowerCase();
			if (directionPlayerSelected.equals("north")) {
				playerRefVar.playerYPos += playerRefVar.stepsize;
			} else if (directionPlayerSelected.equals("south")) {
				playerRefVar.playerYPos -= playerRefVar.stepsize;
			} else if (directionPlayerSelected.equals("east")) {
				playerRefVar.playerXPos += playerRefVar.stepsize;
			} else if (directionPlayerSelected.equals("west")) {
				playerRefVar.playerXPos -= playerRefVar.stepsize;
			} else if (directionPlayerSelected.equals("stats")) {
				System.out.println("Gold is: " + playerRefVar.playerGold);
				System.out.println(
						"Distance from start west is : " + new DecimalFormat("0.##").format(playerRefVar.playerXPos));
				System.out.println(
						"Distance from start north is : " + new DecimalFormat("0.##").format(playerRefVar.playerYPos));
				System.out.println("--------------------");

				System.out.println("Set stepsize 'small','medium' or 'large'");
				stepSizeSelection = scannerRefVar.nextLine();
				stepSizeSelection.toLowerCase();
				if (stepSizeSelection.equals("small")) {
					System.out.println("Step size set to small");
					playerRefVar.stepsize = 0.2d;
				} else if (stepSizeSelection.equals("medium")) {
					System.out.println("Step size set to medium");
					playerRefVar.stepsize = 0.8d;
				} else if (stepSizeSelection.equals("large")) {
					System.out.println("Step size set to large");
					playerRefVar.stepsize = 1.5d;
				} else {
					System.out.println("input not regonized stepsize set to medium");
					playerRefVar.stepsize = 0.8d;
				}
			} else if (directionPlayerSelected.equals("inventory")) {
				System.out.println("List of items: ");
				IntStream.range(0, playerRefVar.listOfItems.size())
						.forEach(x -> System.out.println("Item " + (x + 1) + ": " + playerRefVar.listOfItems.get(x)));
				System.out.println("PLEASE SELECT ITEM TO USE, INTEGERS ONLY");
				try {
					String itemSelectionString = scannerRefVar.nextLine();
					itemSelection = Integer.parseInt(itemSelectionString);
					itemNameSelection = playerRefVar.listOfItems.get(itemSelection - 1);
					if (itemNameSelection == Items.WINGS) {
						System.out.println("You fly away from the swamp! you win!");
						continuteOrExit = false;
					} else if (itemNameSelection == Items.OWL) {
						System.out.println("You now have a cute pet owl following you! uwu");
					} else if (itemNameSelection == Items.JAVA_PROGRAMMING_BOOK) {
						System.out.println("you sit and read the book, you are now an excellent JAVA programmer");
					} else {
						System.out.println("not a valid option");
					}
				} catch (Exception E) {
					System.out.println("not a valid selection, item selection not detected");
				}
				// Items.valueOf(itemSelection);
			} else if (directionPlayerSelected.equals("shop")) {
				playerRefVar.listOfItems
						.addAll((Collection<? extends Items>) shopRefVar.goToShop(playerRefVar.playerGold));
			} else if (directionPlayerSelected.equals("give up")) {
				System.out.println(
						"You lie down in the swamp, you feel your energy drain from you as the water consumes you. You see total darkness.");
				System.out.println("You die.");
				continuteOrExit = false;
			} else {
				System.out.println("That is not a valid command");
			}

			if (continuteOrExit == true) {
				System.out.println("------------New Action------------");
			}
		}

	}
}
package JavaAssess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Shop {
	private List<Items> listOfBroughtItems = new ArrayList<>();
	private List<Item> listOfBuyableItems = new ArrayList<>();
	private Items listOfAllItems[] = Items.values();
	private int itemNumberSelected = 0;
	Player playerRefVarShop;

	public List<Items> goToShop(double playergold) {
		listOfBroughtItems.clear();
		Scanner scannerRefVarShop = new Scanner(System.in);
		System.out.println("HELLO MY FRIEND MY NAME IS SHAFEEQ WELCOME TO MY SHOP");
		IntStream.range(0, listOfBuyableItems.size())
				.forEach(x -> System.out.println("Item " + (listOfBuyableItems.get(x).shopIndex) + ": "
						+ listOfBuyableItems.get(x).name + "   Gold: " + (listOfBuyableItems.get(x).goldValue)));
		System.out.println("PLEASE SELECT YOUR ITEM TO ADD, INTEGERS ONLY");
		try {
			String itemNumberSelectedString = scannerRefVarShop.nextLine();
			itemNumberSelected = Integer.parseInt(itemNumberSelectedString);
			if (listOfBuyableItems.get(itemNumberSelected - 1).goldValue < playergold) {
				System.out.println("ITEM PURCHASED: " + listOfBuyableItems.get(itemNumberSelected - 1).name);
				playerRefVarShop.updateGold(listOfBuyableItems.get(itemNumberSelected - 1).goldValue);
				listOfBroughtItems.add(listOfBuyableItems.get(itemNumberSelected - 1).name);
			} else {
				System.out.println("NOT ENOUGH GOLD");
			}
			System.out.println("GOODBYE I HATE YOU");
		} catch (Exception E) {
			System.out.println("selection not detected");
		}
		return listOfBroughtItems;
	}

	public Shop(Player playerRefVar) {
		playerRefVarShop = playerRefVar;
		for (int i = 0; i < Items.values().length; i++) {
			Item itemRefVar = new Item();
			listOfBuyableItems.add(itemRefVar);
			itemRefVar.name = listOfAllItems[i];
			itemRefVar.goldValue = 100d - (double) i * 10d;
			itemRefVar.shopIndex = i + 1;
		}
	}
}

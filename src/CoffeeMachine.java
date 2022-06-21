import java.util.Objects;
import java.util.Scanner;

public class CoffeeMachine {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		CoffeeMachineInventory coffeeMakerResources = new CoffeeMachineInventory(400, 540, 120, 9, 550);

		String action;
		do {
			System.out.println("Write action (buy, fill, take, remaining, exit)");
			action = scanner.next();

			switch (action) {
				case "remaining" -> System.out.println(coffeeMakerResources);
				case "buy" -> {
					System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
					String coffeeType = scanner.next();
					switch (coffeeType) {
						case "1" -> {
							coffeeMakerResources.espresso();
							System.out.println(coffeeMakerResources.getStatePrettyPrint());
						}
						case "2" -> {
							coffeeMakerResources.latte();
							System.out.println(coffeeMakerResources.getStatePrettyPrint());
						}
						case "3" -> {
							coffeeMakerResources.cappuccino();
							System.out.println(coffeeMakerResources.getStatePrettyPrint());
						}
					}
				}
				case "fill" -> {
					System.out.println("Write how many ml of water you want to add:");
					coffeeMakerResources.addWater(scanner.nextInt());
					System.out.println("Write how many ml of milk you want to add:");
					coffeeMakerResources.addMilk(scanner.nextInt());
					System.out.println("Write how many grams of coffee beans you want to add:");
					coffeeMakerResources.addBeans(scanner.nextInt());
					System.out.println("Write how many disposable cups of coffee you want to add:");
					coffeeMakerResources.addCups(scanner.nextInt());
				}
				case "take" -> {
					System.out.println("I gave you $" + coffeeMakerResources.getMoney());
					coffeeMakerResources.removeMoney();
				}
			}
		} while (!Objects.equals(action, "exit"));
	}
}

class CoffeeMachineInventory {
	private int water;
	private int milk;
	private int beans;
	private int cups;
	private int money;

	private InventoryEnum state;

	public CoffeeMachineInventory(int water, int milk, int beans, int cups, int money) {
		this.water = water;
		this.milk = milk;
		this.beans = beans;
		this.cups = cups;
		this.money = money;
		this.state = InventoryEnum.OK;
	}

	public void latte() {
		if (water < 350) {
			setState(InventoryEnum.water);
			return;
		} else if (milk < 75) {
			setState(InventoryEnum.milk);
			return;
		} else if (beans < 20) {
			setState(InventoryEnum.beans);
			return;
		} else if (cups < 1) {
			setState(InventoryEnum.cups);
			return;
		}
		water = water - 350;
		milk = milk - 75;
		beans = beans - 20;
		cups = cups - 1;
		money = money + 7;
		setState(InventoryEnum.OK);
	}

	public void espresso() {
		if (water < 250) {
			setState(InventoryEnum.water);
			return;
		} else if (beans < 16) {
			setState(InventoryEnum.beans);
			return;
		} else if (cups < 1) {
			setState(InventoryEnum.cups);
			return;
		}
		water = water - 250;
		beans = beans - 16;
		cups = cups - 1;
		money = money + 4;
		setState(InventoryEnum.OK);
	}

	public void cappuccino() {
		if (water < 200) {
			setState(InventoryEnum.water);
			return;
		} else if (milk < 100) {
			setState(InventoryEnum.milk);
			return;
		} else if (beans < 12) {
			setState(InventoryEnum.beans);
			return;
		} else if (cups < 1) {
			setState(InventoryEnum.cups);
			return;
		}
		water = water - 200;
		milk = milk - 100;
		beans = beans - 12;
		cups = cups - 1;
		money = money + 6;
		setState(InventoryEnum.OK);
	}

	@Override
	public String toString() {
		return "\nThe coffee machine has: \n" +
				water + " ml of water\n" +
				milk + " ml of milk\n" +
				beans + " g of coffee beans\n" +
				cups + " disposable cups\n" +
				"$" + money + " of money\n";
	}

	enum InventoryEnum {
		OK, water, milk, beans, cups
	}

	public void setState(InventoryEnum state) {
		this.state = state;
	}

	public InventoryEnum getState() {
		return state;
	}

	public int getMoney() {
		return money;
	}

	public void removeMoney() {
		this.money = 0;
	}

	public String getStatePrettyPrint() {
		if (getState().equals(InventoryEnum.water)) {
			return "Sorry, not enough " + InventoryEnum.water + " !";
		} else if (getState().equals(InventoryEnum.milk)) {
			return "Sorry, not enough " + InventoryEnum.milk + " !";
		} else if (getState().equals(InventoryEnum.beans)) {
			return "Sorry, not enough " + InventoryEnum.beans + " !";
		} else if (getState().equals(InventoryEnum.cups)) {
			return "Sorry, not enough " + InventoryEnum.cups + " !";
		}
		return "I have enough resources, making you a coffee!";
	}

	public void addWater(int amount) {
		this.water = water + amount;
	}

	public void addMilk(int amount) {
		this.milk = milk + amount;
	}

	public void addBeans(int amount) {
		this.beans = beans + amount;
	}

	public void addCups(int amount) {
		this.cups = cups + amount;
	}
}

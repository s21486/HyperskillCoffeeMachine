package machine;
import java.util.Scanner;

enum CoffeeMachineState {
    STANDBY,
    BUYING,
    FILLING,
    EXITING
}

enum CoffeeIngredients {
    WATER,
    MILK,
    COFFEE_BEANS,
    CUPS
}



enum CoffeeRecipes {
    ESPRESSO(250, 0, 16, 1, 4),
    LATTE(350, 75, 20, 1, 7),
    CAPPUCCINO(200, 100, 12, 1, 6);

    private final int water;
    private final int milk;
    private final int coffeeBeans;
    private final int cups;
    private final int price;

    CoffeeRecipes(int water, int milk, int coffeeBeans, int cups, int price) {
        this.water = water;
        this.milk = milk;
        this. coffeeBeans = coffeeBeans;
        this.cups = cups;
        this.price = price;
    }

    public int getWaterAmount() {
        return water;
    }

    public int getMilkAmount() {
        return milk;
    }

    public int getCoffeeBeansAmount() {
        return coffeeBeans;
    }

    public int getCupsAmount() {
        return cups;
    }

    public int getPrice() {
        return price;
    }
}

class CM {

    private int water;
    private int milk;
    private int coffeeBeans;
    private int cups;
    private int money;
    private CoffeeMachineState status;
    private CoffeeRecipes espresso = CoffeeRecipes.ESPRESSO;
    private CoffeeRecipes latte = CoffeeRecipes.LATTE;
    private CoffeeRecipes cappuccino = CoffeeRecipes.CAPPUCCINO;
    private CoffeeIngredients currentIngredient;

    CM() {
        water = 400;
        milk = 540;
        coffeeBeans = 120;
        cups = 9;
        money = 550;
        status = CoffeeMachineState.STANDBY;
    }

    public void getInput(String input) {
        switch (status) {
            case STANDBY:
                if (input.equals("buy")) {
                    status = CoffeeMachineState.BUYING;
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino,"
                            + " back - to main menu:");
                    break;
                } else if (input.equals("fill")) {
                    status = CoffeeMachineState.FILLING;
                    currentIngredient = CoffeeIngredients.WATER;
                    System.out.println("Write how many ml of water do you want to add:");
                    break;
                } else if (input.equals("take")) {
                    System.out.println("I gave you $" + money);
                    money = 0;
                    break;
                } else if (input.equals("remaining")) {
                    printRemaining(water, milk, coffeeBeans, cups, money);
                    break;
                } else if (input.equals("exit")) {
                    status = CoffeeMachineState.EXITING;
                    break;
                } else {
                    System.out.println("Wrong input");
                    break;
                }
            case BUYING:
                if (input.equals("back")) {
                    status = CoffeeMachineState.STANDBY;
                    break;
                }
                int choice = Integer.parseInt(input);
                if (choice == espresso.ordinal()+1) {
                    if (water >= espresso.getWaterAmount() && milk >= espresso.getMilkAmount() &&
                            coffeeBeans >= espresso.getCoffeeBeansAmount() && cups >= espresso.getCupsAmount()) {
                        water -= espresso.getWaterAmount();
                        milk -= espresso.getMilkAmount();
                        coffeeBeans -= espresso.getCoffeeBeansAmount();
                        cups -= espresso.getCupsAmount();
                        money += espresso.getPrice();
                        System.out.println("I have enough resources, making you coffee!");
                        status = CoffeeMachineState.STANDBY;
                        break;
                    }
                    if (water < espresso.getWaterAmount()) {
                        System.out.println("Sorry, not enough water!");
                    }
                    if (milk < espresso.getMilkAmount()) {
                        System.out.println("Sorry, not enough milk!");
                    }
                    if (coffeeBeans < espresso.getCoffeeBeansAmount()) {
                        System.out.println("Sorry, not enough coffee beans!");
                    }
                    if (cups < espresso.getCupsAmount()) {
                        System.out.println("Sorry, not enough disposable cups!");
                    }
                    status = CoffeeMachineState.STANDBY;
                    break;
                } else if (choice == latte.ordinal()+1) {
                    if (water >= latte.getWaterAmount() && milk >= latte.getMilkAmount() && coffeeBeans
                            >= latte.getCoffeeBeansAmount() && cups >= latte.getCupsAmount()) {
                        water -= latte.getWaterAmount();
                        milk -= latte.getMilkAmount();
                        coffeeBeans -= latte.getCoffeeBeansAmount();
                        cups -= latte.getCupsAmount();
                        money += latte.getPrice();
                        System.out.println("I have enough resources, making you coffee!");
                        status = CoffeeMachineState.STANDBY;
                        break;
                    }
                    if (water < latte.getWaterAmount()) {
                        System.out.println("Sorry, not enough water!");
                    }
                    if (milk < latte.getMilkAmount()) {
                        System.out.println("Sorry, not enough milk!");
                    }
                    if (coffeeBeans < latte.getCoffeeBeansAmount()) {
                        System.out.println("Sorry, not enough coffee beans!");
                    }
                    if (cups < latte.getCupsAmount()) {
                        System.out.println("Sorry, not enough disposable cups!");
                    }
                    status = CoffeeMachineState.STANDBY;
                    break;
                }
                if (choice == cappuccino.ordinal()+1) {
                    if (water >= cappuccino.getWaterAmount() && milk >= cappuccino.getMilkAmount() &&
                            coffeeBeans >= cappuccino.getCoffeeBeansAmount() && cups >= cappuccino.getCupsAmount()) {
                        water -= cappuccino.getWaterAmount();
                        milk -= cappuccino.getMilkAmount();
                        coffeeBeans -= cappuccino.getCoffeeBeansAmount();
                        cups -= cappuccino.getCupsAmount();
                        money += cappuccino.getPrice();
                        System.out.println("I have enough resources, making you coffee!");
                        status = CoffeeMachineState.STANDBY;
                        break;
                    }
                    if (water < cappuccino.getWaterAmount()) {
                        System.out.println("Sorry, not enough water!");
                    }
                    if (milk < cappuccino.getMilkAmount()) {
                        System.out.println("Sorry, not enough milk!");
                    }
                    if (coffeeBeans < cappuccino.getCoffeeBeansAmount()) {
                        System.out.println("Sorry, not enough coffee beans!");
                    }
                    if (cups < cappuccino.getCupsAmount()) {
                        System.out.println("Sorry, not enough disposable cups!");
                    }
                    status = CoffeeMachineState.STANDBY;
                    break;
                }
            case FILLING:
                if (currentIngredient == CoffeeIngredients.WATER) {
                    water += Integer.parseInt(input);
                    currentIngredient = CoffeeIngredients.MILK;
                    System.out.println("Write how many ml of milk do you want to add:");
                    break;
                } else if (currentIngredient == CoffeeIngredients.MILK) {
                    milk += Integer.parseInt(input);
                    currentIngredient = CoffeeIngredients.COFFEE_BEANS;
                    System.out.println("Write how many grams of coffee beans do you want to add:");
                    break;
                } else if (currentIngredient == CoffeeIngredients.COFFEE_BEANS) {
                    coffeeBeans += Integer.parseInt(input);
                    currentIngredient = CoffeeIngredients.CUPS;
                    System.out.println("Write how many disposable cups do you want to add:");
                    break;
                } else {
                    cups += Integer.parseInt(input);
                    currentIngredient = CoffeeIngredients.WATER;
                    status = CoffeeMachineState.STANDBY;
                    break;
                }
        }
    }

    private static void printRemaining(int water, int milk, int coffeeBeans, int cups, int money) {
        System.out.println(water + " of water");
        System.out.println(milk + " of milk");
        System.out.println(coffeeBeans + " of coffee beans");
        System.out.println(cups + " of disposable cups");
        System.out.println(money + " of money");
    }
    public CoffeeMachineState getStatus() {
        return status;
    }
}

public class CoffeeMachine {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CM cm = new CM();
        do {
            if (cm.getStatus() == CoffeeMachineState.STANDBY) {
                System.out.println("Write action (buy, fill, take, remaining, exit):");
            }
            cm.getInput(sc.next());
        } while(cm.getStatus() != CoffeeMachineState.EXITING);

    }
}

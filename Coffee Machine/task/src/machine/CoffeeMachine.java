package machine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;


enum States {
    CHOICE_AN_ACTION("Write action (buy, fill, take, remaining, exit):"),
    BUY("What do you want to buy? " +
            "1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: "),
    FILL(""),
    FILLING_WATER("Write how many ml of water do you want to add:"),
    FILLING_MILK("Write how many ml of milk do you want to add:"),
    FILLING_BEANS("Write how many grams of coffee beans do you want to add:"),
    TAKE(""),
    REMAINING("");

    String message;

    States(String message) {
        this.message = message;

    }

    States state;

}


public class CoffeeMachine {

    static boolean workProcess = true;

    public static void main(String[] args) throws IOException {
        Stack<States> stack = new Stack<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        do {

            choice(reader.readLine(), stack);

        } while (workProcess);
    }


    public static void choice(String action, Stack<States> stack) {

        int waterToAdd, milkToAdd, beansToAdd, cupsToAdd;
        int flag = 0;

        switch (action) {
            case "buy":
                stack.push(States.BUY);
                System.out.println(States.BUY.message);
                break;
            case "fill":
                stack.push(States.FILL);
                System.out.println("Write how many ml of water do you want to add:");
                break;
            case "take":
                stack.push(States.TAKE);
                System.out.println("I gave you $" + money);
                System.out.println();
                money = 0;
                stack.push(States.CHOICE_AN_ACTION);
                System.out.println("Write action (buy, fill, take, remaining, exit):");
                break;
            case "remaining":
                stack.push(States.REMAINING);
                state();
                stack.push(States.CHOICE_AN_ACTION);
                System.out.println("Write action (buy, fill, take, remaining, exit):");
                break;
            case "exit":
                workProcess = false;
//                stack.push(States.CHOICE_AN_ACTION);
//                System.out.println("Write action (buy, fill, take, remaining, exit):");
                break;
            default:
                flag = 1;

        }
        if (flag == 0) {
            return;
        }

        if (stack.empty()) {
            stack.push(States.CHOICE_AN_ACTION);
        }
        if (stack.peek().equals(States.BUY)) {
            switch (action) {
                case "1":
                    if (water >= 250 && beans >= 16) {
                        System.out.println("I have enough resources, making you a coffee!");
                        water -= 250;
                        beans -= 16;
                        cups -= 1;
                        money += 4;
                        stack.push(States.CHOICE_AN_ACTION);
                        break;
                    }
                    if (water < 250)
                        System.out.println("Please, add more water");
                    if (beans < 16)
                        System.out.println("Please add more beans");
                    if (cups < 1) System.out.println("Please put more cups");
                    stack.push(States.CHOICE_AN_ACTION);
                    break;
                case "2":
                    if (water >= 350 && beans >= 20 && milk >= 75) {
                        System.out.println("I have enough resources, making you a coffee!");
                        water -= 350;
                        milk -= 75;
                        beans -= 20;
                        cups -= 1;
                        money += 7;
                        stack.push(States.CHOICE_AN_ACTION);
                        break;
                    }
                    if (water < 350)
                        System.out.println("Please, add more water");
                    if (milk < 75)
                        System.out.println("Please, add more milk");
                    if (beans < 20) System.out.println("Please add more beans");
                    if (cups < 1) System.out.println("Please put more cups");
                    stack.push(States.CHOICE_AN_ACTION);
                    break;
                case "3":
                    if (water >= 200 && beans >= 12 && milk >= 100) {
                        water -= 200;
                        milk -= 100;
                        beans -= 12;
                        cups -= 1;
                        money += 6;
                        stack.push(States.CHOICE_AN_ACTION);
                        break;
                    }
                    if (water < 200)
                        System.out.println("Please, add more water");
                    if (milk < 100)
                        System.out.println("Please, add more milk");
                    if (beans < 12) System.out.println("Please add more beans");
                    if (cups < 1) System.out.println("Please put more cups");
                    stack.push(States.CHOICE_AN_ACTION);
                    break;
                case "back":
                    stack.push(States.CHOICE_AN_ACTION);
                    break;

            }
        }

        switch (stack.peek()) {
            case FILL:
                waterToAdd = Integer.parseInt(action);
                water += waterToAdd;
                stack.push(States.FILLING_WATER);
                System.out.println("Write how many ml of milk do you want to add:");
                break;
            case FILLING_WATER:
                milkToAdd = Integer.parseInt(action);
                milk += milkToAdd;
                stack.push(States.FILLING_MILK);
                System.out.println("Write how many grams of coffee beans do you want to add:");
                break;
            case FILLING_MILK:
                beansToAdd = Integer.parseInt(action);
                beans += beansToAdd;
                stack.push(States.FILLING_BEANS);
                System.out.println("Write how many disposable cups of coffee do you want to add:");
                break;
            case FILLING_BEANS:
                cupsToAdd = Integer.parseInt(action);
                cups += cupsToAdd;
//                stack.push(States.FILLING_CUPS);
                stack.push(States.CHOICE_AN_ACTION);
                System.out.println("Write action (buy, fill, take, remaining, exit):");
                break;
            case CHOICE_AN_ACTION:
                System.out.println("Write action (buy, fill, take, remaining, exit):");
                break;
        }
    }

    static int water = 400;
    static int milk = 540;
    static int beans = 120;
    static int cups = 9;
    static int money = 550;

    static void state() {
        System.out.println("The coffee machine has:");
        System.out.println(water + " of water");
        System.out.println(milk + " of milk");
        System.out.println(beans + " of coffee beans");
        System.out.println(cups + " of disposable cups");
        System.out.println(money + " of money");


    }
}

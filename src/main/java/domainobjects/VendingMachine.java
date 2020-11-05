package domainobjects;

import java.util.HashMap;
import java.util.Map;

public class VendingMachine {
    private int openingCredit;
    private int[] denomination = {1, 5, 10, 25};
    private Map<Integer, String> drinksChoice = new HashMap<Integer, String>();
    private int priceOfCoke = 25;
    private int priceOfPepsi = 35;
    private int priceOfSoda = 45;

    public VendingMachine(int credits) {
        this.openingCredit = credits;
    }

    public int getOpeningCredit() {
        return openingCredit;
    }

    public boolean acceptDenomination(int coin) {
        return checkAndAcceptedCurrency(coin);
    }

    private boolean checkAndAcceptedCurrency(int coin) {
        boolean jackpot = false;

        for (int c : denomination) {
            if (c == coin) {
                jackpot = true;
                break;
            }
        }
        if (jackpot) {
            System.out.println(coin + " coin accepted\n\nPlease make a drink selection!");
        } else {
            System.out.println(coin + " coin not accepted");
        }

        return jackpot;
    }

    public String beverageSelection(int selection) {

        String drink;
        drinksChoice.put(25, "Coke");
        drinksChoice.put(35, "Pepsi");
        drinksChoice.put(45, "Soda");

        drink = drinksChoice.get(selection);
        return drink;


    }

    public int cancelOrderAndIssueRefund(String refund, int coins) {
        boolean cancel = false;
        int money = 0;
        if (refund.contains("Cancel")) cancel = true;
        if (cancel) {
            money = refundCalculation(coins);
        }
        return money;
    }

    private int refundCalculation(int coins) {
        return coins;
    }

    public int giveDrinkAndChange(int selection, int coins) {
        int change = 0;

        if (selection == 25 && coins >= 25) {
            change = calculateRefund(priceOfCoke, coins);
        } else if (selection == 35 && coins >= 35) {
            change = calculateRefund(priceOfPepsi, coins);
            System.out.print("Your drink choice is Pepsi. Please take your change of " + change);
        } else if (selection == 45 && coins >= 45) {
            change = calculateRefund(priceOfSoda, coins);
            System.out.println("Your drink choice is Soda. Please take your change of " + change);
        } else {
            System.out.println("===You've made an incorrect transaction===");
        }

        return change;
    }

    private int calculateRefund(int drinkPrice, int amount) {
        return amount - drinkPrice;
    }
}

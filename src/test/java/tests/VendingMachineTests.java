package tests;

import domainobjects.VendingMachine;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class VendingMachineTests {
    private VendingMachine vendingMachine;

    @Test
    public void testVendingMachineOpeningCredit() {
        int credit = 0;
        vendingMachine = new VendingMachine(0);
        assertEquals("Vending machine has credit", credit, vendingMachine.getOpeningCredit());
    }

    @Test
    public void testAcceptedCoins() {
        vendingMachine = new VendingMachine(0);
        assertTrue(vendingMachine.acceptDenomination(10));
    }

    @Test
    public void testDrinksOrder() {
        String myDrink;
        vendingMachine = new VendingMachine(0);
        myDrink = vendingMachine.beverageSelection(35);
        assertEquals("Not my drink", "Pepsi", myDrink);
        System.out.println("Your drink selection is " + myDrink);
    }

    @Test
    public void testOrderCancellation() {
        vendingMachine = new VendingMachine(0);
        int refund = vendingMachine.cancelOrderAndIssueRefund("Cancel", 20);
        assertThat("Money not returned", refund, is(equalTo(20)));
        System.out.println("Please take your change of " + refund);
    }

    @Test
    public void testDrinkPlusRefund() {
        vendingMachine = new VendingMachine(0);
        int change = vendingMachine.giveDrinkAndChange(45, 100);
        assertThat("Incorrect change returned", change, is(equalTo(55)));
    }

}

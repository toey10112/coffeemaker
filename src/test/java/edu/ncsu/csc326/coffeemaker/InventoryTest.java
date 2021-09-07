package edu.ncsu.csc326.coffeemaker;
import static org.junit.Assert.*;



import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;

public class InventoryTest {
    private Inventory inventory;

    @Before
    public void setUp()  {
        inventory = new Inventory();


    }

    /**
     * Given inventory
     * When we want to add chocolate, if the value of chocolate is not a positive integer
     * @throws InventoryException if there was an error parsing the quantity to a positive integer.
     */
    @Test (expected = InventoryException.class)
    public void TestAddChocolateWithNonInteger() throws InventoryException{
        inventory.addChocolate("a");
    }

    /**
     * Given inventory
     * When we want to add chocolate, if the value of chocolate is not a positive integer
     * @throws InventoryException if there was an error parsing the quantity to a positive integer.
     */
    @Test (expected = InventoryException.class)
    public void TestAddChocolateWithNegativeInteger() throws InventoryException{
        inventory.addChocolate("-2");
    }

    /**
     * Given inventory
     * When we want to add coffee, if the value of coffee is not a positive integer
     * @throws InventoryException if there was an error parsing the quantity to a positive integer.
     */
    @Test (expected = InventoryException.class)
    public void TestAddCoffeeWithNonInteger() throws InventoryException{
        inventory.addCoffee("a");
    }

    /**
     * Given inventory
     * When we want to add coffee, if the value of coffee is not a positive integer
     * @throws InventoryException if there was an error parsing the quantity to a positive integer.
     */
    @Test (expected = InventoryException.class)
    public void TestAddCoffeeWithNegativeInteger() throws InventoryException{
        inventory.addCoffee("-2");
    }

    /**
     * Given inventory
     * When we want to add milk, if the value of milk is not a positive integer
     * @throws InventoryException if there was an error parsing the quantity to a positive integer.
     */
    @Test (expected = InventoryException.class)
    public void TestAddMilkWithNonInteger() throws InventoryException{
        inventory.addMilk("a");
    }

    /**
     * Given inventory
     * When we want to add milk, if the value of milk is not a positive integer
     * @throws InventoryException if there was an error parsing the quantity to a positive integer.
     */
    @Test (expected = InventoryException.class)
    public void TestAddMilkWithNegativeInteger() throws InventoryException{
        inventory.addMilk("-2");
    }

    /**
     * Given inventory
     * When we want to add sugar, if the value of sugar is not a positive integer
     * @throws InventoryException if there was an error parsing the quantity to a positive integer.
     */
    @Test (expected = InventoryException.class)
    public void TestAddSugarWithNonInteger() throws InventoryException{
        inventory.addSugar("a");
    }

    /**
     * Given inventory
     * When we want to add sugar, if the value of sugar is not a positive integer
     * @throws InventoryException if there was an error parsing the quantity to a positive integer.
     */
    @Test (expected = InventoryException.class)
    public void TestAddSugarWithNegativeInteger() throws InventoryException{
        inventory.addSugar("-5");
    }

    /**
     * Given inventory
     * When we want to add sugar, if the value of sugar is a positive integer
     * Inventory should add sugar
     * @throws InventoryException if there was an error parsing the quantity to a positive integer.
     */
    @Test
    public void TestAddSugarWithPositiveInteger() throws InventoryException {
        inventory.addSugar("10");
        assertEquals(10,inventory.getSugar());
    }

    /**
     * Given inventory
     * When we check if there is enough ingredients
     * It returns true if it has enough otherwise false.
     * @throws RecipeException if there was an error parsing the quantity to a positive integer.
     *
     */
    @Test
    public void testEnoughIngredients() throws RecipeException {
        Recipe recipe1 = new Recipe();
        assertTrue(inventory.enoughIngredients(recipe1)); // All ingredients should be 0
        recipe1.setAmtCoffee("100");
        assertFalse(inventory.enoughIngredients(recipe1));
        recipe1.setAmtCoffee("0"); // Reset for test another ingredients
        recipe1.setAmtChocolate("100");
        assertFalse(inventory.enoughIngredients(recipe1));
        recipe1.setAmtChocolate("0"); // Reset for test another ingredients
        recipe1.setAmtMilk("100");
        assertFalse(inventory.enoughIngredients(recipe1));
        recipe1.setAmtMilk("0"); // Reset for test another ingredients
        recipe1.setAmtSugar("100");
        assertFalse(inventory.enoughIngredients(recipe1));

    }

    /**
     * Given inventory
     * When we want to add chocolate, if the amount is more than 0
     * Then it will add chocolate to inventory otherwise no change
     */
    @Test
    public void testSetChocolate(){
        inventory.setChocolate(-1);
        assertEquals(0,inventory.getChocolate());
        inventory.setChocolate(1);
        assertEquals(1,inventory.getChocolate());


    }

    /**
     * Given inventory
     * When we want to add milk, if the amount is more than 0
     * Then it will add milk to inventory otherwise no change
     */
    @Test
    public void testSetMilk(){
        inventory.setMilk(-3);
        assertEquals(0,inventory.getMilk());
        inventory.setMilk(1);
        assertEquals(1,inventory.getMilk());


    }

    /**
     * Given inventory
     * When we want to add coffee, if the amount is more than 0
     * Then it will add coffee to inventory otherwise no change
     */
    @Test
    public void testSetCoffee(){
        inventory.setCoffee(-3);
        assertEquals(0,inventory.getCoffee());
        inventory.setCoffee(1);
        assertEquals(1,inventory.getCoffee());


    }

    /**
     * Given inventory
     * When we want to add coffee, if the amount is more than 0
     * Then it will add coffee to inventory otherwise no change
     */
    @Test
    public void testSetSugar(){
        inventory.setSugar(-3);
        assertEquals(0,inventory.getSugar());
        inventory.setSugar(1);
        assertEquals(1,inventory.getSugar());


    }

}

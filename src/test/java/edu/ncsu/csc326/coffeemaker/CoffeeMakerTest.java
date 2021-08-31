/*
 * Copyright (c) 2009,  Sarah Heckman, Laurie Williams, Dright Ho
 * All Rights Reserved.
 * 
 * Permission has been explicitly granted to the University of Minnesota 
 * Software Engineering Center to use and distribute this source for 
 * educational purposes, including delivering online education through
 * Coursera or other entities.  
 * 
 * No warranty is given regarding this software, including warranties as
 * to the correctness or completeness of this software, including 
 * fitness for purpose.
 * 
 * 
 * Modifications 
 * 20171114 - Ian De Silva - Updated to comply with JUnit 4 and to adhere to 
 * 							 coding standards.  Added test documentation.
 */
package edu.ncsu.csc326.coffeemaker;
import static org.junit.Assert.*;



import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;

/**
 * Unit tests for CoffeeMaker class.
 * 
 * @author Suchon Prasert
 */
public class CoffeeMakerTest {
	
	/**
	 * The object under test.
	 */
	private CoffeeMaker coffeeMaker;
	
	// Sample recipes to use in testing.
	private Recipe recipe1;
	private Recipe recipe2;
	private Recipe recipe3;
	private Recipe recipe4;

	/**
	 * Initializes some recipes to test with and the {@link CoffeeMaker} 
	 * object we wish to test.
	 * 
	 * @throws RecipeException  if there was an error parsing the ingredient 
	 * 		amount when setting up the recipe.
	 */
	@Before
	public void setUp() throws RecipeException {
		coffeeMaker = new CoffeeMaker();
		
		//Set up for r1
		recipe1 = new Recipe();
		recipe1.setName("Coffee");
		recipe1.setAmtChocolate("0");
		recipe1.setAmtCoffee("3");
		recipe1.setAmtMilk("1");
		recipe1.setAmtSugar("1");
		recipe1.setPrice("50");
		
		//Set up for r2
		recipe2 = new Recipe();
		recipe2.setName("Mocha");
		recipe2.setAmtChocolate("20");
		recipe2.setAmtCoffee("3");
		recipe2.setAmtMilk("1");
		recipe2.setAmtSugar("1");
		recipe2.setPrice("75");
		
		//Set up for r3
		recipe3 = new Recipe();
		recipe3.setName("Latte");
		recipe3.setAmtChocolate("0");
		recipe3.setAmtCoffee("3");
		recipe3.setAmtMilk("3");
		recipe3.setAmtSugar("1");
		recipe3.setPrice("100");
		
		//Set up for r4
		recipe4 = new Recipe();
		recipe4.setName("Hot Chocolate");
		recipe4.setAmtChocolate("4");
		recipe4.setAmtCoffee("0");
		recipe4.setAmtMilk("1");
		recipe4.setAmtSugar("1");
		recipe4.setPrice("65");
	}
	
	
	/**
	 * Given a coffee maker with the default inventory
	 * When we add inventory with well-formed quantities
	 * Then we do not get an exception trying to read the inventory quantities.
	 * 
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test
	public void testAddInventory() throws InventoryException {
		coffeeMaker.addInventory("4","7","0","9");

	}
	
	/**
	 * Given a coffee maker with the default inventory
	 * When we add inventory with malformed quantities (i.e., a negative 
	 * quantity and a non-numeric string)
	 * Then we get an inventory exception
	 * 
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test(expected = InventoryException.class)
	public void testAddInventoryException() throws InventoryException {
		coffeeMaker.addInventory("4", "-1", "asdf", "3");
	}

	/**
	 * Given a coffee maker with the recipe book
	 * When we add a recipe, Only 3 recipes can be added to the coffee maker
	 * If we add more than that the program should return False.
	 */
	@Test
	public void testNoMoreThan3RecipesAdd(){
		assertTrue(coffeeMaker.addRecipe(recipe1));
		assertTrue(coffeeMaker.addRecipe(recipe2));
		assertTrue(coffeeMaker.addRecipe(recipe3));
		assertFalse(coffeeMaker.addRecipe(recipe4));
	}

	/**
	 * Given a coffee maker with the recipe book
	 * When we add a recipe, every recipe must have their name
	 * If we add the recipe with duplicate name the program should return False.
	 */
	@Test
	public void testAddDuplicateRecipe(){
		coffeeMaker.addRecipe(recipe1);
		assertFalse(coffeeMaker.addRecipe(recipe1));
	}

	/**
	 * Given a coffee maker with the recipe book
	 * When we try to delete recipes that we don't have in the recipe book
	 * it will return Null.
	 */
	@Test
	public void testDeleteANonexistentRecipe(){
		assertNull(coffeeMaker.deleteRecipe(1));
	}

	/**
	 * Given a coffee maker with the recipe book
	 * When we try to delete recipes that we have in the recipe book
	 * it will return the name of the recipe that got deleted.
	 */
	@Test
	public void testDeleteRecipeThatExistent(){
		coffeeMaker.addRecipe(recipe1);
		assertEquals(recipe1.getName(),coffeeMaker.deleteRecipe(0));
		assertNull(coffeeMaker.deleteRecipe(0)); //Check that their no recipe in the recipe book.
	}

	/**
	 * Given a coffee maker with the recipe book
	 * When we try to edit recipes that we don't have in the recipe book
	 * it will return Null
	 */
	@Test
	public void testEditANonexistentRecipe(){
		assertNull(coffeeMaker.editRecipe(0,recipe1));
	}

	/**
	 * Given a coffee maker with the recipe book
	 * When we try to edit recipes that we have in the recipe book
	 * it will return the name of the recipe that got edited.
	 */
	@Test
	public void testEditRecipeThatExistent(){
		coffeeMaker.addRecipe(recipe1);
		assertEquals(recipe1.getName(),coffeeMaker.editRecipe(0,recipe2));
	}

	/**
	 * Given a coffee maker with the recipe book
	 * When we buy beverage,the ingredients should be reduced according to the beverage recipe.
	 */
	@Test
	public void testRemoveIngredientsFromPurchaseBeverage(){
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.makeCoffee(0,50);
		assertEquals("Coffee: 12\n" +
				"Milk: 14\n" +
				"Sugar: 14\n" +
				"Chocolate: 15\n",coffeeMaker.checkInventory());
	}

	/**
	 * Given a coffee maker with the recipe book
	 * When we check inventory
	 * we should see the units of each item in the inventory.
	 *
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 	  		to a positive integer.
	 *
	 */
	@Test
	public void testCheckInventory() throws InventoryException{
		assertEquals("Coffee: 15\n" + "Milk: 15\n" + "Sugar: 15\n" + "Chocolate: 15\n",coffeeMaker.checkInventory());
	}

	/**
	 * Given a coffee maker with the recipe book
	 * When we pay less than the cost of the coffee
	 * we can't buy coffee and get the money back.
	 */
	@Test
	public void testPurchaseBeverageWithoutEnoughMoney(){
		coffeeMaker.addRecipe(recipe3); // price : 100
		assertEquals(74,coffeeMaker.makeCoffee(0,74));
	}

	/**
	 * Given a coffee maker with the recipe book
	 * When we pay with enough money then the money that we get back should be 0
	 * But if we pay with more money than the price, we should get a change return.
	 */
	@Test
	public void testPurchaseBeverageWithEnoughMoneyAndMore(){
		coffeeMaker.addRecipe(recipe3); // price : 100
		assertEquals(0,coffeeMaker.makeCoffee(0,100));
		assertEquals(1,coffeeMaker.makeCoffee(0,101));
	}

	/**
	 * Given a coffee maker with the recipe book
	 * When we buy beverage if it doesn't have enough ingredients in the inventory
	 * it will return the money back.
	 */
	@Test
	public void testPurchaseBeverageWithoutEnoughIngredients(){
		coffeeMaker.addRecipe(recipe2); // Chocolate need : 20 but we only have 15
		assertEquals(75,coffeeMaker.makeCoffee(0,75));

	}




}

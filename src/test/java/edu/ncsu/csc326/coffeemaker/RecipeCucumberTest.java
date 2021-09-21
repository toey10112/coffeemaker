package edu.ncsu.csc326.coffeemaker;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;
import io.cucumber.java.en.*;
import static org.junit.Assert.*;

public class RecipeCucumberTest {
    private CoffeeMaker coffeeMaker;
    private Recipe mocha;

    private Recipe createRecipe(String name, String chocolate, String coffee, String milk, String sugar, String price) throws RecipeException, RecipeException {
        Recipe r = new Recipe();
        r.setName(name);
        r.setAmtChocolate(chocolate);
        r.setAmtCoffee(coffee);
        r.setAmtMilk(milk);
        r.setAmtSugar(sugar);
        r.setPrice(price);
        return r;
    }



    @Given("coffeemaker is set")
    public void coffeemakerIsSet() throws RecipeException {
        coffeeMaker = new CoffeeMaker();
        Recipe recipe1 = createRecipe("blackcoffee", "1","1","1","1","50");
        coffeeMaker.addRecipe(recipe1);

    }

    @When("The owner add mocha recipe to coffeemaker")
    public void theOwnerAddMochaRecipeToCoffeemaker() throws RecipeException {
        //Mocha
        mocha = createRecipe("mocha", "10","10","10","10","500");
    }

    @Then("coffeemaker return true")
    public void coffeemakerReturnTrue() {
        assertTrue(coffeeMaker.addRecipe(mocha));
    }

    @When("The owner edit all ingredients and price from recipe {int} to {word}")
    public void theOwnerEditAllIngredientsAndPriceFromRecipeTo(int recipe, String num ) throws RecipeException {
        Recipe temp = createRecipe("temp",num,num,num,num,num);
        coffeeMaker.editRecipe(recipe-1,temp);
    }

    @Then("recipe {int} will return coffee {int}, milk {int}, chocolate {int}, sugar {int} and price {int}")
    public void recipeWillReturnCoffeeMilkChocolateSugarAndPrice(int recipe,int coffee, int milk, int chocolate, int sugar, int price) {
        assertEquals(coffee, coffeeMaker.getRecipes()[recipe-1].getAmtCoffee());
        assertEquals(milk, coffeeMaker.getRecipes()[recipe-1].getAmtMilk());
        assertEquals(chocolate, coffeeMaker.getRecipes()[recipe-1].getAmtChocolate());
        assertEquals(sugar, coffeeMaker.getRecipes()[recipe-1].getAmtSugar());
        assertEquals(price, coffeeMaker.getRecipes()[recipe-1].getPrice());
    }
    @When("The owner delete recipe {int} in coffeemaker")
    public void theOwnerDeleteRecipeInCoffeemaker(int recipe) {

    }
    @Then("coffeemaker return name of recipe {int}")
    public void coffeemakerReturnNameOfRecipe(int arg0) {
        assertEquals("blackcoffee",coffeeMaker.deleteRecipe(0));
    }
}

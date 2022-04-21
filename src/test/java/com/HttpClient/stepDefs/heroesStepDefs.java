package com.HttpClient.stepDefs;

import com.HttpClient.pages.HeroesPage;
import com.HttpClient.utilities.BrowserUtils;
import com.HttpClient.utilities.ConfigurationReader;
import com.HttpClient.utilities.Driver;
import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.*;


public class heroesStepDefs {


    HeroesPage heroesPage = new HeroesPage();
    String heroName = new Faker().name().firstName();
    WebDriver driver = Driver.get();

    @Given("User is on the Heroes Page")
    public void user_is_on_the_Heroes_Page() {
        String url = ConfigurationReader.get("url");
        driver.get(url);      //due to singleton design pattern
    }

    @Given("Heroes check box should be selected")
    public void heroes_check_box_should_be_selected() {
        if (!heroesPage.heroesCheckBox.isSelected()) {
            heroesPage.heroesCheckBox.click();
        }
    }

    @When("The user adds new hero")
    public void the_user_adds_new_hero() {
        heroesPage.addHero(heroName);
    }

    @Then("verify new hero is added")
    public void verify_new_hero_is_added() {
        String actualResult = heroesPage.getHeroName(heroName);
        System.out.println(actualResult);

        String expectedResult = heroName;
        System.out.println(expectedResult);
        BrowserUtils.sleep(2);

        assertEquals(expectedResult, actualResult);
    }

    @Given("The user should search hero name {string}")
    public void the_user_should_search_hero_name(String heroes) {
        heroesPage.inputBox.sendKeys(heroes);
        heroesPage.searchButton.click();
    }

    @Then("verify the hero is found by named {string}")
    public void verify_the_hero_is_found_by_named(String heroes) {
        assertEquals(heroesPage.getHeroName(heroes), heroes);
        System.out.println(heroesPage.getHeroName(heroes) + "=" + heroes);
    }

    @Then("The user deletes new hero")
    public void the_user_deletes_new_hero(){
        driver.findElement(By.xpath("//span[text()='" + heroName + "']/../following-sibling::button")).click();
        BrowserUtils.sleep(2);
    }

    @Then("verify new hero is deleted")
    public void verify_new_hero_is_deleted() {
        boolean exist = driver
                .findElements(By.xpath("//span[text()='" + heroName + "']/../following-sibling::button")).size() == 0;
        System.out.println("The hero is deleted :" + exist);
    }

    @When("The user adds new hero name with {string}")
    public void the_user_adds_new_hero_name_with(String string) {
        heroesPage.addHero(string);
    }

   @Then("verify no new hero is added with {string} name")
    public void verify_no_new_hero_is_added_with_name(String string) {
        assertTrue(driver.findElements(By.xpath("//span[text()='"+string+"']/../following-sibling::button")).isEmpty());
    }


}

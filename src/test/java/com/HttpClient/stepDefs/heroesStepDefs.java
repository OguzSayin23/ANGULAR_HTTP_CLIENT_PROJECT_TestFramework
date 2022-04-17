package com.HttpClient.stepDefs;

import com.HttpClient.pages.HeroesPage;
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
    public void verify_new_hero_is_added() throws InterruptedException {
        String actualResult = heroesPage.getHeroName(heroName);
        System.out.println(actualResult);

        String expectedResult = heroName;
        System.out.println(expectedResult);
        Thread.sleep(2000);

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
    public void the_user_deletes_new_hero() throws InterruptedException {
        driver.findElement(By.xpath("//span[text()='" + heroName + "']/../following-sibling::button")).click();
        Thread.sleep(2000);
    }

    @Then("verify new hero is deleted")
    public void verify_new_hero_is_deleted() {
        //assertTrue(driver.findElements(By.xpath("//span[text()='"+heroName+"']/../following-sibling::button")).isEmpty());
        boolean exist = driver
                .findElements(By.xpath("//span[text()='" + heroName + "']/../following-sibling::button")).size() == 0;
        System.out.println("The hero is deleted :" + exist);
    }


}

package com.HttpClient.pages;

import com.HttpClient.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeroesPage {
    public HeroesPage(){
        PageFactory.initElements(Driver.get(),this);
    }
    // locators
    // useful methods

    @FindBy(id = "hero-name")
    public WebElement inputBox;

    @FindBy(xpath = "//button[text()=' Add hero ']")
    public WebElement addHeroButton;

    @FindBy(xpath = "//button[text()=' Search ']")
    public WebElement searchButton;

    @FindBy(xpath = "//button[@title='delete hero']")
    public WebElement deleteHero;

    @FindBy(xpath = "//input[@id='heroes']")
    public WebElement heroesCheckBox;



    public void addHero(String hero){
        inputBox.sendKeys(hero);
        addHeroButton.click();
    }

    public String getHeroName(String hero){
        String heroName = Driver.get().findElement(By.xpath("//span[text()='"+hero+"']")).getText();
        return heroName;
    }
}

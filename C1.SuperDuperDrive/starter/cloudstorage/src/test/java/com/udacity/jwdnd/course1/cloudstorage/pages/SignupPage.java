package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

    @FindBy(id = "inputFirstName")
    private WebElement firstNameField;

    @FindBy(id = "inputLastName")
    private WebElement lastNameField;

    @FindBy(id = "inputUsername")
    private WebElement usernameField;

    @FindBy(id = "inputPassword")
    private WebElement passwordField;

    @FindBy(id = "buttonSignUp")
    private WebElement signupButton;

    @FindBy(id = "success-msg")
    private WebElement successMsg;

    public SignupPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void SignUp(String firstName, String lastName, String username, String password){
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);

        signupButton.click();
    }

    public Boolean isSuccessfulSignup(){
        return successMsg.getText().contains("You successfully signed up");
    }
}

package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.pages.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.pages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.SignupPage;
import org.openqa.selenium.WebDriver;

public class Utils {
    public static void userLogin(WebDriver driver, String baseURL, String username, String password){
        LoginPage loginPage = new LoginPage(driver);
        driver.get(baseURL + "/login");
        loginPage.login(username, password);
    }
    public static void userSignUp(WebDriver driver, String baseURL, String firstName, String lastName, String username, String password){
        SignupPage signUpPage = new SignupPage(driver);
        driver.get(baseURL + "/signup");
        signUpPage.SignUp(firstName, lastName, username, password);
    }
    public static void logout(WebDriver driver){
        HomePage homePage = new HomePage(driver);
        homePage.logout();
    }
}

package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.pages.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static com.udacity.jwdnd.course1.cloudstorage.Utils.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CredentialTest {
    @LocalServerPort
    private int port;
    @Autowired
    private CredentialService credentialService;
    private static WebDriver driver;
    private String baseURL;
    boolean signedUp = false;

    @BeforeAll
    public static void beforeAll(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    @AfterAll
    public static void afterAll(){
        driver.quit();
        driver = null;
    }
    @BeforeEach
    public void beforeEach() throws InterruptedException {
        baseURL = "http://localhost:" + port;
        if (!signedUp){
            userSignUp(driver, baseURL,"John", "Doe","JohnDoe","123456789");
            signedUp = true;
        }
        userLogin(driver, baseURL, "JohnDoe", "123456789");
        HomePage homePage = new HomePage(driver);
        homePage.createNewCredential("www.superduperdrive.com", "testCredential0","credPassword0");
        driver.get(baseURL + "/home");
        homePage.openCredentialsTab();
    }
    @AfterEach
    public void afterEach(){
        logout(driver);
    }

    @Test
    public void TestAddCredential() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        WebElement actualCredentialUrl = homePage.searchCredentialsByUrl("www.superduperdrive.com");
        WebElement actualUsername = homePage.searchCredentialsByUsername("testCredential0");
        assertNotNull(actualCredentialUrl);
        assertNotNull(actualUsername);

    }

    @Test
    public void testEditCredential() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.editCredential("edited credential url", "edited credential username", "edited credential password");
        driver.get(baseURL + "/home");
        homePage.openCredentialsTab();
        WebElement actualCredentialUrl = homePage.searchCredentialsByUrl("edited credential url");
        WebElement actualCredentialUsername = homePage.searchCredentialsByUsername("edited credential username");
        assertEquals("edited credential url", actualCredentialUrl.getText());
        assertEquals("edited credential username", actualCredentialUsername.getText());
    }

//    @Test
//    void testDeleteCredential() throws InterruptedException {
//        HomePage homePage = new HomePage(driver);
//        homePage.deleteCredential();
//        driver.get(baseURL + "/home");
//        homePage.openCredentialsTab();
//        Thread.sleep(2000);
//        WebElement actualCredentialUrl = homePage.searchCredentialsByUrl("www.superduperdrive.com");
//        WebElement actualCredentialUsername = homePage.searchCredentialsByUsername("testCredential0");
//        WebElement actualCredentialPassword = homePage.searchCredentialsByPassword("credPassword0");
//        assertNull(actualCredentialUrl);
//        assertNull(actualCredentialUsername);
//        assertNull(actualCredentialPassword);
//    }

}

package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomePage {

    private WebDriverWait wait;

    @FindBy(id = "logout-button")
    private WebElement logoutButton;
    @FindBy(id = "nav-notes-tab")
    private WebElement navNotesTab;
    @FindBy(id = "add-new-note")
    private WebElement addNewNote;
    @FindBy(id = "note-title")
    private WebElement modalNoteTitle;
    @FindBy(id = "note-description")
    private WebElement modalNoteDescription;
    @FindBy(id = "submit-note")
    private WebElement submitNoteButton;
    @FindBy(className = "note-titles")
    private List<WebElement> noteTitles;
    @FindBy(className = "note-descriptions")
    private List<WebElement> noteDescriptions;
    @FindBy(className = "note-edit-buttons")
    private List<WebElement> noteEditButtons;
    @FindBy(className = "delete-note-buttons")
    private List<WebElement> deleteEditButtons;

    @FindBy(id = "nav-credentials-tab")
    private WebElement navCredentialsTab;
    @FindBy(id = "add-new-credentials")
    private WebElement addNewCredentials;
    @FindBy(id = "credential-url")
    private WebElement modalCredentialUrl;
    @FindBy(id = "credential-username")
    private WebElement modalCredentialUsername;
    @FindBy(id = "credential-password")
    private WebElement modalCredentialPassword;
    @FindBy(id = "submit-credential")
    private WebElement submitCredentialButton;
    @FindBy(className = "credential-urls")
    private List<WebElement> credentialUrls;
    @FindBy(className = "credential-usernames")
    private List<WebElement> credentialUsernames;
    @FindBy(className = "credential-password")
    private List<WebElement> credentialPassword;
    @FindBy(className = "credential-edit-buttons")
    private List<WebElement> credentialEditButtons;
    @FindBy(className = "delete-credential-buttons")
    private List<WebElement> deleteCredentialButtons;

    public List<WebElement> getNoteTitles() {
        return noteTitles;
    }

    // Notes
    public HomePage(WebDriver driver){
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 5);
    }
    public void openNotesTab() throws InterruptedException {
        Thread.sleep(500);
        this.navNotesTab.click();
    }
    public void openNewNoteModal() throws InterruptedException {
        Thread.sleep(500);
        this.addNewNote.click();
    }
    public void createNewNote(String noteTitle, String noteDescription) throws InterruptedException {
        openNotesTab();
        openNewNoteModal();
        Thread.sleep(500);
        modalNoteTitle.sendKeys(noteTitle);
        modalNoteDescription.sendKeys(noteDescription);
        submitNoteButton.click();
    }
    public WebElement searchNotesByTitle(String title) throws InterruptedException {
        Thread.sleep(500);
        for (WebElement element : noteTitles){
            if(element.getText().equals(title)){
                return element;
            }
        }
        return null;
    }
    public WebElement searchNotesByDescription(String description) throws InterruptedException {
        Thread.sleep(500);
        for (WebElement element : noteDescriptions){
            if(element.getText().equals(description)){
                return element;
            }
        }
        return null;
    }
    public void editNote(String title, String description) throws InterruptedException {
        Thread.sleep(500);
        noteEditButtons.get(0).click();
        Thread.sleep(500);
        modalNoteTitle.clear();
        modalNoteTitle.sendKeys(title);
        modalNoteDescription.clear();
        modalNoteDescription.sendKeys(description);
        submitNoteButton.click();
    }
    public void deleteNote() throws InterruptedException {
        Thread.sleep(500);
        for (WebElement delButton: deleteEditButtons){
            try {
                delButton.click();
            } catch (Exception e) {

            }
        }
    }

    // Credentials
    public void openCredentialsTab() throws InterruptedException {
        Thread.sleep(500);
        this.navCredentialsTab.click();
    }
    public void createNewCredential(String url, String username, String password) throws InterruptedException {
        openCredentialsTab();
        Thread.sleep(500);
        this.addNewCredentials.click();
        Thread.sleep(500);
        modalCredentialUrl.sendKeys(url);
        modalCredentialUsername.sendKeys(username);
        modalCredentialPassword.sendKeys(password);
        submitCredentialButton.click();
    }
    public WebElement searchCredentialsByUrl(String url) throws InterruptedException {
        Thread.sleep(500);
        for (WebElement element : credentialUrls){
            if(element.getText().equals(url)){
                return element;
            }
        }
        return null;
    }
    public WebElement searchCredentialsByUsername(String username) throws InterruptedException {
        Thread.sleep(500);
        for (WebElement element : credentialUsernames){
            if(element.getText().equals(username)){
                return element;
            }
        }
        return null;
    }
    public WebElement searchCredentialsByPassword(String password) throws InterruptedException {
        Thread.sleep(500);
        for (WebElement element : credentialPassword){
            if(element.getText().equals(password)){
                return element;
            }
        }
        return null;
    }
    public void editCredential(String url, String username, String password) throws InterruptedException{
        Thread.sleep(500);
        credentialEditButtons.get(0).click();
        Thread.sleep(500);
        modalCredentialUrl.clear();
        modalCredentialUrl.sendKeys(url);
        modalCredentialUsername.clear();
        modalCredentialUsername.sendKeys(username);
        modalCredentialPassword.clear();
        modalCredentialPassword.sendKeys(password);
        submitCredentialButton.click();
    }
    public void deleteCredential() throws InterruptedException{
        Thread.sleep(500);
        for (WebElement delCredButton : deleteCredentialButtons){
            try {
                delCredButton.click();
            } catch (Exception e) {
            }
        }
    }

    // logout
    public void logout(){
        this.logoutButton.click();
    }
}

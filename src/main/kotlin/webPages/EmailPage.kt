package webPages

import entities.Email
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory


public class EmailPage(private val driver: WebDriver) {

    @FindBy(id = "username")
    var emailElement: WebElement? = null

    @FindBy(xpath = "//*[@id=\"userInfo\"]/div/h2/span")
    var userEmailRetrievedFromItineraryPage: WebElement? = null

    @FindBy(id = "LoginContinueBtn_1")
    var continueBtnAfterEmailAddress: WebElement? = null

    @FindBy(id = "AdultOne")
    var travellerNameLabel: WebElement? = null

    init {
        PageFactory.initElements(driver, this)
    }

    fun enterEmailAddress(email: Email): TravellerDetailsPage {

        BasePage(driver).sendKeys(emailElement, email.email)
        continueBtnAfterEmailAddress?.click()
        BasePage(driver).waitForElementsToLoad(travellerNameLabel, "Traveller name")
        return TravellerDetailsPage(driver)
    }

    fun isEmailEnteredCorrectly(email: Email): Boolean {
        Thread.sleep(2000)
        var getUserEmailFromItineraryPage = userEmailRetrievedFromItineraryPage?.text
        return (getUserEmailFromItineraryPage.equals(email.email))
    }
}
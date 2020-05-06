package webPages


import entities.Traveller
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.Select

class TravellerDetailsPage (private val driver: WebDriver) : BasePage(driver) {

    @FindBy(xpath ="(//*[@id=\"AdultFname1\"])[1]")
    var firstNameElement:WebElement?= null

    @FindBy(xpath ="(//*[@id=\"AdultLname1\"])[1]")
    var lastNameElement:WebElement?= null

    @FindBy(xpath ="(//*[@id=\"mobileNumber\"])[1]")
    var mobileElement:WebElement?= null

    @FindBy(xpath ="(//*[@id=\"AdultTitle1\"])[1]")
    var titleOfTravellerElement:WebElement?= null

    @FindBy(xpath="//*[@id=\"travellerSum\"]/ul/li/span")
    var travellerInfoElement:WebElement?= null

    @FindBy(id="travellerBtn")
    var continueBtnToNavigateToPayment:WebElement?= null

    @FindBy(xpath="//*[@id=\"CCTab\"]/dl[1]/dt[1]/label")
    var creditCardLabelElement:WebElement?= null

    init {
        PageFactory.initElements(driver, this)
    }

    public fun enterTravellerDetails(travellerInfo: Traveller){

        Select(titleOfTravellerElement).selectByValue(travellerInfo.title)
        BasePage(driver).sendKeys(firstNameElement,travellerInfo.firstName)
        BasePage(driver).sendKeys(lastNameElement,travellerInfo.lastName)
        BasePage(driver).sendKeys(mobileElement,travellerInfo.mobile)
        continueBtnToNavigateToPayment?.click()
        BasePage(driver).waitForElementsToLoad(creditCardLabelElement, "Credit card no.")
    }

    public fun isTravellerInfoCorrect(travellerInfo: Traveller):Boolean {

        Thread.sleep(2000)
        return ((travellerInfoElement?.text?.contains(travellerInfo.title)!!) &&
                (travellerInfoElement?.text?.contains(travellerInfo.firstName)!!) &&
                (travellerInfoElement?.text?.contains(travellerInfo.lastName)!!))
    }
}
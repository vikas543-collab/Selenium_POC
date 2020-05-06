package webPage

import entities.TravelDetails
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import webPages.BasePage

public class TicketsBookingPage(private val driver: WebDriver) : BasePage(driver) {

    @FindBy(id = "FromTag")
    var originFlightElement: WebElement? = null

    @FindBy(xpath = "((//*[@tabindex=\"-1\"])[4])")
    var originFlightSelectHighlightedElement: WebElement? = null

    @FindBy(id = "ToTag")
    var destinationFlightElement: WebElement? = null

    @FindBy(xpath = "((//*[@tabindex=\"-1\"])[5])")
    var destinationFlightSelectHighlightedElement: WebElement? = null

    @FindBy(id = "DepartDate")
    var dateOfTravelElement: WebElement? = null

    @FindBy(id = "SearchBtn")
    var searchFlightElement: WebElement? = null

    @FindBy(xpath = "(.//*[contains(text(),'Bangalore')])[1]")
    var pageFullyLoadCheckElement: WebElement? = null

    init {
        PageFactory.initElements(driver, this)
    }

    public fun searchFlight(travel: TravelDetails): SelectFlightPage {
        enterOriginCity(travel.sourceCity)
        enterDestinationCity(travel.destCity)
        enterDate(travel.travelDate)
        click(searchFlightElement)
        Thread.sleep(10000)
        return SelectFlightPage(driver)
    }

    private fun enterDate(travelDate: String) {
        dateOfTravelElement?.sendKeys(Keys.BACK_SPACE)
        sendKeys(dateOfTravelElement, travelDate)
        Thread.sleep(5000)
    }

    private fun enterOriginCity(sourceCity: String) {
        sendKeys(originFlightElement, sourceCity)
        click(originFlightSelectHighlightedElement)
    }

    private fun enterDestinationCity(destinationCity: String) {
        sendKeys(destinationFlightElement, destinationCity)
        click(destinationFlightSelectHighlightedElement)
    }
}
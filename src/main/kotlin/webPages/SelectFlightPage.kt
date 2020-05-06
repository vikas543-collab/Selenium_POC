package webPage

import com.google.common.base.CharMatcher
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory


public class SelectFlightPage(private val driver: WebDriver) {

    @FindBy(xpath = ".//*[@data-sort='airline']")
    var airlineTag: By? = null

    @FindBy(xpath = "(//*[@id=\"BaggageBundlingTemplate\"])[1]")
    var firstTicketPriceElement: WebElement? = null

    @FindBy(xpath = "(//*[@class='price   actionPrice   '])[1]")
    var bookFlightElement: WebElement? = null

    init {
        PageFactory.initElements(driver, this)
    }
    fun isTheTopMostPriceLowest(): Boolean {
        println("Lowest price as per Cleartrip " + firstTicketPriceElement?.text)
        var firstTicketPrice = CharMatcher.inRange('0', '9').retainFrom(firstTicketPriceElement?.text)
        var ticketPriceList = driver.findElements(By.xpath("(//*[@id=\"BaggageBundlingTemplate\"])"))
        var numOfTickets = (ticketPriceList.size)
        val listOfTickets: MutableList<String> = mutableListOf()

        println("-----------------------------------")
        println("Here goes all ticket prices")
        println("-----------------------------------")

        var lowestTicketPrice = CharMatcher.inRange('0', '9').retainFrom(firstTicketPriceElement?.text)
        print("lowestAsPerClearTrip "+lowestTicketPrice)
        for (i in 0 until numOfTickets) {
            println()
            println(ticketPriceList.get(i).text)
            var extractedValue =CharMatcher.inRange('0', '9').retainFrom(ticketPriceList.get(i).text)
            if(extractedValue < lowestTicketPrice){
                var lowestTicketPrice = extractedValue}
        }
        print("Lowest ticket price "+lowestTicketPrice)
        return firstTicketPrice == lowestTicketPrice
    }

    fun navigateToItineraryPageByChoosingFirstFlight(): ItineraryPage {
        Thread.sleep(5000)
        bookFlightElement?.click()
        return ItineraryPage(driver)
    }
}
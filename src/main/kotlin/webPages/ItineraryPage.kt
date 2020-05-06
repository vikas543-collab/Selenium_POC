package webPage

import entities.TravelDetails
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import webPages.EmailPage
import java.text.DateFormatSymbols


public class ItineraryPage(private val driver: WebDriver) {

    @FindBy(xpath = "//*[@class=\"itinerary clearFix onwBlock\"]")
    var originToSourceFlightDetailElement: WebElement? = null

    @FindBy(xpath = "//*[@class=\"itinerary clearFix onwBlock\"]/h1/small[2]")
    var flightDateOnItineraryPage: WebElement? = null

    @FindBy(xpath ="//*[@id=\"coupon\"]")
    var coupon: WebElement? = null

    @FindBy(xpath ="//*[@id=\"itineraryBtn\"]")
    var continueBookingBtnOnItineraryPage: WebElement? = null

    init {
        PageFactory.initElements(driver, this)
    }

    fun getOriginToSourceFlightInfo(): String? {
        var originToSourceFlightInfo = originToSourceFlightDetailElement?.text
        return originToSourceFlightInfo
    }

    fun getFlightDateFromItineraryPage(): String? {
        return (flightDateOnItineraryPage?.text)
    }

    fun isItineraryDetailCorrect(travel: TravelDetails): Boolean {
        val actual = getOriginToSourceFlightInfo()
        return (actual?.contains(travel.sourceCity)!!) && (actual.contains(travel.destCity))
    }

    fun isTravelDateCorrect(travelDate: String): Boolean {
        val actualDate = getFlightDateFromItineraryPage()
        var parsingMonthNumberToName = (((travelDate.split("-"))[1]).toIntOrNull())?.minus(1)
        val parsedMonthName =(DateFormatSymbols.getInstance().months[parsingMonthNumberToName!!])
        var isDateMatching = ((travelDate.split("-"))[0]).equals(((actualDate?.split(" "))?.get(1)))
        var isMonthMatching = parsedMonthName.contains(((actualDate?.split(" "))?.get(2).toString()))
        var isYearMatching = ((travelDate.split("-"))[2]).equals(((actualDate?.split(" "))?.get(3)))

        return(isDateMatching && isMonthMatching && isYearMatching)
    }

    fun clickContinueBookingBtn(): EmailPage {

        val winHandleBefore = driver.windowHandle
        for (winHandle in driver.windowHandles) {
            driver.switchTo().window(winHandle)
        }
        Thread.sleep(7000)
        val jse = driver as JavascriptExecutor
        jse.executeScript("scroll(0, 250);");
        Thread.sleep(1000)
        continueBookingBtnOnItineraryPage?.click()
        return EmailPage(driver)
    }
}
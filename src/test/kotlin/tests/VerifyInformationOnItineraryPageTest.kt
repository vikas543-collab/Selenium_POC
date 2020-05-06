package tests

import entities.TravelDetails
import org.testng.Assert
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import webPage.ItineraryPage
import webPage.SelectFlightPage
import webPage.TicketsBookingPage
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class VerifyInformationOnItineraryPageTest : TestBase() {

    private lateinit var ticketsBookingPage: TicketsBookingPage
    private lateinit var selectFlightPage: SelectFlightPage
    private lateinit var itineraryPage: ItineraryPage

    @BeforeMethod
    fun testSetup() {
        val driver = TestBase().testSetUp()
        ticketsBookingPage = TicketsBookingPage(driver!!)
        selectFlightPage = SelectFlightPage(driver!!)
        itineraryPage = ItineraryPage(driver!!)
    }

    @Test(description = "Verify if Itinerary details : Source, destination flight and date of travel is displayed correctly on Itinerary page",enabled = true)
    fun verifyItineraryPageDetails() {

        val tomorrowsDate = LocalDate.now().plusMonths(2).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString()
        val travel = TravelDetails(sourceCity = "Bangalore", destCity = "Lucknow", travelDate = tomorrowsDate)

        ticketsBookingPage.searchFlight(travel)
        .navigateToItineraryPageByChoosingFirstFlight()
        var getItineraryDetailCheck = itineraryPage.isItineraryDetailCorrect(travel)

        Assert.assertEquals(getItineraryDetailCheck, true, "Flight info is not displayed correctly on itinerary page")

        var getDateMonthYearCheckFromItineraryPage = itineraryPage.isTravelDateCorrect(travel.travelDate)

        Assert.assertEquals(
            getDateMonthYearCheckFromItineraryPage, true,
            "Date is not displayed correctly on itinerary page"
        )
    }
}
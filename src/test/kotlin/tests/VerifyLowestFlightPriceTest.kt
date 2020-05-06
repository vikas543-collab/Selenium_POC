package tests

import entities.TravelDetails
import org.testng.Assert
import org.testng.annotations.Test
import webPage.SelectFlightPage
import webPage.TicketsBookingPage
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class VerifyLowestFlightPriceTest : TestBase() {

    private lateinit var ticketsBookingPage: TicketsBookingPage
    private lateinit var selectFlightPage: SelectFlightPage

    fun testSetup() {
        val driver = TestBase().testSetUp()
        ticketsBookingPage = TicketsBookingPage(driver!!)
        selectFlightPage = SelectFlightPage(driver!!)
    }


    @Test(description = "Verify if lowest flight price is on top",enabled = true, priority = 2)
    fun verifyLowestFlightPriceIsOnTheTop() {
        testSetup()
        val tomorrowsDate = LocalDate.now().plusMonths(2).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString()
        val travel = TravelDetails("Bangalore", "Lucknow", travelDate = tomorrowsDate)
        ticketsBookingPage.searchFlight(travel)

        val isLowestPrice = selectFlightPage.isTheTopMostPriceLowest()
        Assert.assertEquals(isLowestPrice, true, "Lowest price is not displayed correctly")
    }
}
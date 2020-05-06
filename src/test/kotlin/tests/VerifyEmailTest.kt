package tests

import entities.Email
import entities.TravelDetails
import org.testng.Assert
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import webPage.ItineraryPage
import webPage.SelectFlightPage
import webPage.TicketsBookingPage
import webPages.EmailPage
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class VerifyEmailTest : TestBase() {

    private lateinit var emailPage: EmailPage
    private lateinit var ticketsBookingPage: TicketsBookingPage
    private lateinit var selectFlightPage: SelectFlightPage
    private lateinit var itineraryPage: ItineraryPage

    @BeforeMethod
    fun setUp() {
        val driver = TestBase().testSetUp()
        emailPage = EmailPage(driver!!)
        ticketsBookingPage = TicketsBookingPage(driver!!)
        selectFlightPage = SelectFlightPage(driver!!)
        itineraryPage = ItineraryPage(driver!!)
    }

    @Test(description = "Verify if email is correct", enabled = true)
    fun verifyEmailTest() {

        val tomorrowsDate = LocalDate.now().plusMonths(2).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString()
        val travel = TravelDetails("Bangalore", "Lucknow", travelDate = tomorrowsDate)
        val email = Email("vikas543@gmail.com")

        ticketsBookingPage.searchFlight(travel)
            .navigateToItineraryPageByChoosingFirstFlight()
            .clickContinueBookingBtn()
            .enterEmailAddress(email)
        var getEmailCheckFromItineraryPage = emailPage.isEmailEnteredCorrectly(email)

        Assert.assertEquals(getEmailCheckFromItineraryPage, true, "Email is not saved correctly")
    }
}
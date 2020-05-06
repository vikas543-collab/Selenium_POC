package tests
import org.testng.Assert
import org.testng.annotations.Test
import webPages.LaunchHomePage

class VerifyTitleOfHomePage : TestBase() {

    @Test(description = "Verify if title of Home page is correct",enabled = true, priority = 1)
    fun verifyTitleOfHomePageTest() {

        val driver = TestBase().testSetUp()
        var expectedTitleOfHomePage = "#1 Site for Booking Flights, Hotels, Packages, Trains & Local activities."
        var actualTitleOfHomePage = LaunchHomePage(driver).getTitleOfHomePage()

        Assert.assertEquals(
            actualTitleOfHomePage,
            expectedTitleOfHomePage,
            "Title is not as expected"
        )
    }
}
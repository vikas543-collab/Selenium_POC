package tests

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.testng.annotations.AfterMethod
import org.testng.annotations.Test
import java.io.FileInputStream
import java.util.*
import java.util.concurrent.TimeUnit


open class TestBase {

    var driver: WebDriver? = null
    @Test
    open fun testSetUp(): ChromeDriver {
        val options = ChromeOptions()
        val fis = FileInputStream("src/main/resources/config.properties")
        val prop = Properties()
        prop.load(fis)
        var baseURL = prop.getProperty("pageURL")

        WebDriverManager.chromedriver().setup()
        val driver = ChromeDriver(getChromeOption())

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)
        driver.get(baseURL)
        return driver
    }

    @AfterMethod
    fun driverClose() {
        driver?.quit()
    }

    private fun getChromeOption(): ChromeOptions {
        val options = ChromeOptions()
        options!!.addArguments("--disable-notifications")
        options.setCapability(ChromeOptions.CAPABILITY, options);
        return options
    }
}
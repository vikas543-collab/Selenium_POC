package webPages
import org.openqa.selenium.WebDriver

public class LaunchHomePage(private val driver: WebDriver){

    fun getTitleOfHomePage(): String? {
        return (driver.title)
    }
}




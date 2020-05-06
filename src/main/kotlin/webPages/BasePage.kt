package webPages

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

open class BasePage(private val driver: WebDriver) {

    fun sendKeys(Element: WebElement?, Text: String) {
        WebDriverWait(driver, 10)
            .until(ExpectedConditions.elementToBeClickable(Element))
        Element?.click()
        Element?.sendKeys(Text)
    }

    fun click(Element: WebElement?){
        WebDriverWait(driver, 10)
            .until(ExpectedConditions.elementToBeClickable(Element))
        Element?.click()
    }

    fun waitForElementsToLoad(Element: WebElement?, waitForTextOnPage: String?) {
        WebDriverWait(driver, 20)
            .until(ExpectedConditions.textToBePresentInElement(Element, waitForTextOnPage))
    }
}
## Automation POC with Kotlin

## Problem Statement

Automate a ticket booking application(www.cleartrip.com) and verify following :

* **VerifyEmailTest** : Verify if title of home page is correct
* **VerifyInformationOnItineraryPageTest** : Verify if Itinerary details : Source, destination flight and date of travel is displayed correctly on Itineray page
* **VerifyLowestFlightPriceTest** : Verify if lowest price ticket is displayed at the top by default
* **VerifyTitleOfHomePage** : Verify if title of Home page is correct
* **VerifyTravellerDetailsTest** : Verify if Itineray details : Source, destination flight and date of travel is displayed correctly on Itineray page

## Demo of the framework 

https://drive.google.com/file/d/1RzLgN4wl7ifXtNIO8lYaOBMwvvY1X4Bg/view?usp=sharing

## Project details

* Language used : Kotlin
* Automation Test framework : Selenium
* Build tool : Maven
* Unit test framework : TestNg

## Project structure

* src/main/kotlin/entities - Entity details to hold travel information

* src/main/kotlin/webPages - Placeholder for Webpages and elements associated with different screens

* src/test/kotlin/tests - Placeholder for tests to verify functionality 

* src/main/resources/ - Stores config file to store common test variables 

### Steps to execute the test -

`mvn clean test`

package com.veeam;

import com.veeam.configuration.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class seleniumTest {

    @ParameterizedTest(name = "Veeam vacancies (country = {0}, amount of jobs = {2})")
    @CsvFileSource(resources = Constants.VEEAM_JOBS_IN_COUNTRIES_CSV_PATH, numLinesToSkip = 1)
    void veeamSeleniumTest(String country, String languageBtn, String jobsFound) throws InterruptedException {
        System.setProperty(Constants.WEB_DRIVER_CHROME_DRIVER, Constants.CHROME_DRIVER_PATH);

        ChromeDriver driver = new ChromeDriver();

        driver.get(Constants.URL_PATH);

        driver.manage().window().maximize();

        WebElement listAllCountries = driver.findElementByCssSelector("[id=\"country-element\"]");
        listAllCountries.click();

        WebElement buttonCountry = driver.findElementByCssSelector("[data-value=" + country + "]");
        buttonCountry.click();

        WebElement listAllLanguages = driver.findElementByCssSelector("[id=\"language\"]");
        listAllLanguages.click();

        WebElement buttonLanguage = driver.findElementByCssSelector("[for=" + languageBtn + "]");
        buttonLanguage.click();
        Thread.sleep(1000L);

        WebElement labelAmountOfFoundJobs =
                driver.findElementByCssSelector("[class=\"pb15-sm-down mb30-md-up text-center-md-down\"]");

        Assertions.assertEquals(jobsFound, labelAmountOfFoundJobs.getText());
    }
}
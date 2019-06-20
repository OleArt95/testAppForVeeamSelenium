package com.veeam;

import com.veeam.configuration.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class seleniumTest {

    @ParameterizedTest(name = "Veeam vacancies (country = {0}, amount of jobs = {2})")
    @CsvFileSource(resources = Constants.VEEAM_JOBS_IN_COUNTRIES_CSV_PATH, numLinesToSkip = 1)
    void veeamSeleniumTest(String country, String languageBtn, String jobsFound) throws InterruptedException {
        System.setProperty(Constants.WEB_DRIVER_CHROME_DRIVER, Constants.CHROME_DRIVER_PATH);

        //Объявление переменной для веб-драйвера
        WebDriver driver = new ChromeDriver();

        //метод для перехода на сайт https://careers.veeam.com/
        driver.get(Constants.URL_PATH);

        //метод для перехода в полноэкранный режим браузера
        driver.manage().window().maximize();

        clickOnAllCountriesList(driver);
        clickOnCountryButton(driver, country);
        clickOnAllLanguagesList(driver);
        clickOnLanguageButton(driver, languageBtn);
        checkAmountOfFoundJobs(driver, jobsFound);
    }

    /**
     * метод для нажатия на выпадающий список стран
     */
    private void clickOnAllCountriesList(WebDriver driver) {
        WebElement listAllCountries = driver.findElement(By.cssSelector("[id=\"country-element\"]"));
        listAllCountries.click();
    }

    /**
     * метод для выбора определенной страны
     */
    private void clickOnCountryButton(WebDriver driver, String country) {
        WebElement buttonCountry = driver.findElement(By.cssSelector("[data-value=" + country + "]"));
        buttonCountry.click();
    }

    /**
     * метод для нажатия на выпадающий список доступных языков
     */
    private void clickOnAllLanguagesList(WebDriver driver) {
        WebElement listAllLanguages = driver.findElement(By.cssSelector("[id=\"language\"]"));
        listAllLanguages.click();
    }

    /**
     * метод для выбора языка
     */
    private void clickOnLanguageButton(WebDriver driver, String languageBtn) throws InterruptedException {
        WebElement buttonLanguage = driver.findElement(By.cssSelector("[for=" + languageBtn + "]"));
        buttonLanguage.click();
        Thread.sleep(1000L);
    }

    /**
     * метод для проверки количества доступных вакансий
     */
    private void checkAmountOfFoundJobs(WebDriver driver, String jobsFound) {
        WebElement labelAmountOfFoundJobs =
                driver.findElement(By.cssSelector("[class=\"pb15-sm-down mb30-md-up text-center-md-down\"]"));

        Assertions.assertEquals(jobsFound, labelAmountOfFoundJobs.getText());
    }
}
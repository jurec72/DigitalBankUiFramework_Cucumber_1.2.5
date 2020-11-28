package steps.amazon;

import Pages.amazon.AmazonSearchPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.SeleniumUtils;

public class AmazonSearchSteps {

    private static final Logger LOG = LogManager.getLogger(AmazonSearchSteps.class.getName());

    AmazonSearchPage amazonSearchPage = new AmazonSearchPage();
    WebDriver driver = Driver.getDriver();

    @Given("^go to amazon\\.com$")
    public void go_to_amazon_com() throws Throwable {
        SeleniumUtils.goTo(ConfigReader.getProperty("amazon_url"));
    }

    @When("^enter search term \"([^\"]*)\"$")
    public void enter_search_term(String searchText) throws Throwable {
        SeleniumUtils.sendKeysWithClear(amazonSearchPage.searchInput, searchText);
    }

    @When("^click Search button$")
    public void click_Search_button() throws Throwable {
        SeleniumUtils.click(amazonSearchPage.searchButton);
    }

    @Then("^verify that result with \"([^\"]*)\" is displayed anywhere in the results$")
    public void verify_that_result_with_is_displayed_anywhere_in_the_results(String expectedText) throws Throwable {
//        String element = "//span[@class='a-size-medium a-color-base a-text-normal' and contains(text(),'"+expectedText+"')]";
//        WebElement elText = driver.findElement(By.xpath(element));
//        Assert.assertTrue(elText.isDisplayed());
        for (WebElement results : amazonSearchPage.searchResults) {
            if (results.getText().contains(expectedText)) {
                Assert.assertTrue(SeleniumUtils.getText(results).contains(expectedText));
                break;
            }
        }
    }

    @Then("^verify that a result with \"([^\"]*)\" is not displayed anywhere in the results$")
    public void verify_that_a_result_with_is_not_displayed_anywhere_in_the_results(String expectedText) throws Throwable {
        for (WebElement results : amazonSearchPage.searchResults) {
                Assert.assertFalse(SeleniumUtils.getText(results).contains(expectedText));
        }
    }




}

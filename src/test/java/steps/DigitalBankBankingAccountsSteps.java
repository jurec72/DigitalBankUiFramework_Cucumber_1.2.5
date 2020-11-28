package steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Driver;

import java.util.List;

public class DigitalBankBankingAccountsSteps {
    WebDriver driver = Driver.getDriver();

    @Then("^Verify that \"([^\"]*)\" (label|header|text) is displayed$")
    public void verify_that_text_is_displayed(String arg1) throws Throwable {
        System.out.println(driver.findElement(By.xpath(String.format("//*[contains(text(),'%s')]", arg1))).getText());

        driver.findElement(By.xpath(String.format("//*[text()='%s']", arg1))); //div[text()='Welcome John']
        driver.findElement(By.xpath("//*[text()='" + arg1 + "']"));// //div[text()='Welcome John']

    }

    @Then("^Verify that panel with account information is displayed$")
    public void verify_that_panel_with_account_information_is_displayed() throws Throwable {

    }

    @Then("^Verify that under \"([^\"]*)\" we have options$")
    public void verify_that_under_we_have_options(String mainButton, List<String> buttonOptions) throws Throwable {
        WebElement headButton = driver.findElement(By.xpath("//a[text()='" + mainButton + "']"));

//        driver.findElement(By.xpath("//a[text()='Savings']"));
        headButton.click();

        List<WebElement> listOptionButtons = driver.findElements
                (By.xpath("//a[text()='" + mainButton + "']/following-sibling::" +
                        "ul[@class='sub-menu children dropdown-menu show']//a"));

        for (int i = 0; i < listOptionButtons.size(); i++) {
            Assert.assertTrue(buttonOptions.contains(listOptionButtons.get(i).getText()));
        }
    }

    @Then("^User clicks on Checking button$")
    public void user_clicks_on_Checking_button() throws Throwable {

    }

    @Then("^User clicks on New Checking button$")
    public void user_clicks_on_New_Checking_button() throws Throwable {

    }

    @Then("^Verify that radio buttons are unchecked with following text$")
    public void verify_that_radio_buttons_are_unchecked_with_following_text(DataTable arg1) throws Throwable {

    }

    @Then("^Verify that input field accepts alphanumeric and special characters$")
    public void verify_that_input_field_accepts_alphanumeric_and_special_characters() throws Throwable {

    }

    @Then("^Verify that input field accepts accepts numeric whole or decimal values$")
    public void verify_that_input_field_accepts_accepts_numeric_whole_or_decimal_values() throws Throwable {

    }

    @Then("^Verify that \"([^\"]*)\" button is displayed$")
    public void verify_that_button_is_displayed(String arg1) throws Throwable {

    }

    @Then("^User should get error message \"([^\"]*)\"$")
    public void user_should_get_error_message(String arg1) throws Throwable {

    }

    @Then("^User clicks on \"([^\"]*)\" radio buttons$")
    public void user_clicks_on_radio_buttons(String arg1) throws Throwable {

    }

    @And("^User enter name \"([^\"]*)\" into Account Name$")
    public void userEnterNameIntoAccountName(String arg0) throws Throwable {

    }

    @And("^Verify that Submit button is displayed$")
    public void verifyThatSubmitButtonIsDisplayed() {


    }

    @And("^Verify that Reset button is displayed$")
    public void verifyThatResetButtonIsDisplayed() {

    }

    @And("^User clicks on Submit button$")
    public void userClicksOnSubmitButton() {
    }
}

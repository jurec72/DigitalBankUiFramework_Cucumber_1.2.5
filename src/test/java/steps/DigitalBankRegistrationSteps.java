package steps;


import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Driver;

import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 * TODO:
 * Add implementations of steps from feature file
 */
public class DigitalBankRegistrationSteps {



    WebDriver driver = Driver.getDriver();
    private final String LOGIN_PAGE_URL = "http://dbankdemo.com/login";
    private final String HOME_PAGE_URL = "http://dbankdemo.com/home";

    @Given("^User navigates to Digital Bank login page$")
    public void user_navigates_to_digital_bank_login_page() {
        driver.get(LOGIN_PAGE_URL);
        Assertions.assertThat(driver.getCurrentUrl()).isEqualTo(LOGIN_PAGE_URL);
    }

    @Given("^Verify that web title is \"([^\"]*)\"$")
    public void verify_that_web_title_is(String title) throws Throwable {
        Assertions.assertThat(driver.getTitle()).isEqualTo(title);
    }

    @When("^User logs in with following credentials$")
    public void user_logs_in_with_following_credentials(DataTable dataTable) throws Throwable {
        List<Map<String, String>> credentials = dataTable.asMaps(String.class, String.class);
        driver.findElement(By.id("username")).sendKeys(credentials.get(0).get("username"));
//        driver.findElement(By.xpath("//input[@id='username']")).sendKeys(credentials.get("username"));
        driver.findElement(By.id("password")).sendKeys(credentials.get(0).get("password"));
        driver.findElement(By.id("submit")).click();
    }

    @Then("^User successfully logged in to home page$")
    public void user_successfully_logged_in_to_home_page() throws Throwable {
        Assertions.assertThat(driver.getCurrentUrl()).isEqualTo(HOME_PAGE_URL);
        WebElement welcomeLink = driver.findElement(By.xpath("//li[contains(text(),'Welcome')]"));
        Assertions.assertThat(welcomeLink.getText()).contains("Welcome");
    }

    @Then("^User should be displayed with the error message \"([^\"]*)\"$")
    public void user_should_be_displayed_with_the_error_message(String expectedErrorMessage) throws Throwable {
        WebElement error = driver.findElement(
                By.xpath("//div[@class='sufee-alert alert " +
                        "with-close alert-danger alert-dismissible fade show']"));
        Assertions.assertThat(error.getText()).contains("Error Invalid credentials or access not granted.");
    }

    @When("^User logs in with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void userLogsInWithAnd(String username, String password) throws Throwable {
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("submit")).click();
    }

    @Given("^User navigates to Digital Bank signup page$")
    public void userNavigatesToDigitalBankSignupPage() {
    }

    @When("^User creates account with following fields$")
    public void user_creates_account_with_following_fields(DataTable dataTable) throws Throwable {

    }

    @Then("^User should be displayed with the message \"([^\"]*)\"$")
    public void userShouldBeDisplayedWithTheMessage(String expectedMessage) throws Throwable {

    }

}


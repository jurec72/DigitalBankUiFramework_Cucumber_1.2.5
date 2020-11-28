package steps;


import com.github.javafaker.Faker;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domains.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utilities.Driver;

import java.util.List;
import java.util.Map;


/**
 * TODO:
 * Add implementations of steps from feature file
 */
public class DigitalBankRegistrationSteps {

    WebDriver driver = Driver.getDriver();
    private final String LOGIN_PAGE_URL = "http://dbankdemo.com/login";
    private final String HOME_PAGE_URL = "http://dbankdemo.com/home";
    private String username;
    private String password;
    private String title;


    private static final Logger LOG = LogManager.getLogger(DigitalBankRegistrationSteps.class.getName());

    @Given("^User navigates to Digital Bank login page$")
    public void user_navigates_to_digital_bank_login_page() {
        LOG.debug("Use url {}", LOGIN_PAGE_URL);
        driver.get(LOGIN_PAGE_URL);
        LOG.info("Login page is opened!");
        try {
            LOG.debug("Assert that actual url is as expected");
            Assertions.assertThat(driver.getCurrentUrl()).isEqualTo(LOGIN_PAGE_URL);
            LOG.info("Assertion for URL is passed.");
        }catch(Exception e){
            LOG.error("error is here");
            throw e;
        }


        String title = driver.getTitle();
        if(title.equals("Home Page")){
            LOG.error("");
        }

    }

    @Given("^Verify that web title is \"([^\"]*)\"$")
    public void verify_that_web_title_is(String title) throws Throwable {
        Assertions.assertThat(driver.getTitle()).isEqualTo(title);
        this.title = title;
        LOG.info("Title - {} - is correct.", title);
    }

    @When("^User logs in with following credentials$")
    public void user_logs_in_with_following_credentials(DataTable dataTable) throws Throwable {
        List<Map<String, String>> credentials = dataTable.asMaps(String.class, String.class);
        driver.findElement(By.id("username")).sendKeys(credentials.get(0).get("username"));
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
        WebElement signUpButton = driver.findElement(By.xpath("//a[contains(text() , 'Sign Up Here')]"));
        signUpButton.click();
        WebElement titleWord = driver.findElement(By.xpath("//strong[text()='Title']"));
        Assert.assertTrue(titleWord.isDisplayed());
    }

    @When("^User creates account with following fields$")
    public void user_creates_account_with_following_fields(DataTable dataTable) throws Throwable {
        //generate random data
        Faker fake = new Faker();

        String email = fake.internet().emailAddress();
        String ssn = fake.regexify("[0-8]\\d{2}-\\d{2}-\\d{4}");
        String homePhone = fake.regexify("\\([0-8]\\d{2}\\)\\d{3}-\\d{4}");
        String mobilePhone = fake.regexify("\\([0-8]\\d{2}\\)\\d{3}-\\d{4}");
        String workPhone = fake.regexify("\\([0-8]\\d{2}\\)\\d{3}-\\d{4}");

        //save datatable from feature file
        List<User> userList = dataTable.asList(User.class);

        //save data on class level to use in the next step
        username = email;
        password = userList.get(0).getPassword();

        //select title Mr.
        WebElement selectTitle = driver.findElement(By.id("title"));

        Select select = new Select(selectTitle);
        select.selectByValue(userList.get(0).getTitle());

        //enter firstname
        LOG.info("Enter firstname");
        WebElement firstName = driver.findElement(By.id("firstName"));
        firstName.sendKeys(userList.get(0).getFirstName());
        //enter Lastname
        WebElement lastName = driver.findElement(By.id("lastName"));
        lastName.sendKeys(userList.get(0).getLastName());

         //select gender dynamicly
        List<WebElement> gender = driver.findElements(By.id("gender"));

        for (WebElement element : gender) {
            if (element.getAttribute("value").equalsIgnoreCase(userList.get(0).getGender())) {
                element.click();
            }
        }
        //dateOfBirth
        WebElement dob = driver.findElement(By.id("dob"));
        dob.sendKeys(userList.get(0).getDob());
        //ssn
        WebElement ssnLocated = driver.findElement(By.id("ssn"));
        ssnLocated.sendKeys(ssn);
        //email
        WebElement emailLocated = driver.findElement(By.id("emailAddress"));
        emailLocated.sendKeys(email);
        //password
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys(userList.get(0).getPassword());
        //confirm password
        WebElement confirmPass = driver.findElement(By.id("confirmPassword"));
        confirmPass.sendKeys(userList.get(0).getPassword());
        WebElement nextButton = driver.findElement(By.xpath("//button[text()='Next']"));
        nextButton.click();
        Thread.sleep(3000);
        //verify address page
        WebElement addressWord = driver.findElement(By.xpath("//strong[text()='Address']"));
        Assert.assertTrue(addressWord.isDisplayed());
        //next Page address
        WebElement address = driver.findElement(By.id("address"));
        address.sendKeys(userList.get(0).getAddress());
        //locality
        WebElement locality = driver.findElement(By.id("locality"));
        locality.sendKeys(userList.get(0).getLocality());
        //region
        WebElement region = driver.findElement(By.id("region"));
        region.sendKeys(userList.get(0).getLocality());
        //postal code
        WebElement postalCode = driver.findElement(By.id("postalCode"));
        postalCode.sendKeys(userList.get(0).getPostalCode());
        //country
        WebElement country = driver.findElement(By.id("country"));
        country.sendKeys(userList.get(0).getCountry());
        //home phone
        WebElement homePhoneLocated = driver.findElement(By.id("homePhone"));
        homePhoneLocated.sendKeys(homePhone);
        //mobile phone
        WebElement mobilePhoneLocated = driver.findElement(By.id("mobilePhone"));
        mobilePhoneLocated.sendKeys(mobilePhone);
        //work phone
        WebElement workPhoneLocated = driver.findElement(By.id("workPhone"));
        workPhoneLocated.sendKeys(workPhone);
        //agree terms
        driver.findElement(By.id("agree-terms")).click();
        //submit button
        driver.findElement(By.xpath("//button[@type='submit']")).click();


    }

    @Then("^User should be displayed with the message \"([^\"]*)\"$")
    public void userShouldBeDisplayedWithTheMessage(String expectedMessage) throws Throwable {
        WebElement registrationMessage = driver.findElement(By.xpath("//span[text()='Registration Successful. Please Login.']"));
        Assert.assertTrue(registrationMessage.getText().contains(expectedMessage));
    }

    @And("^User can login to the new account$")
    public void userCanLoginToTheNewAccount() {
        WebElement userNameElement = driver.findElement(By.id("username"));
        userNameElement.clear();
        userNameElement.sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("submit")).click();
        Assertions.assertThat(driver.getTitle()).isEqualTo(title);
    }
}




package steps;


import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utilities.Driver;

public class Hooks {

    private static Logger logger;

    @Before
    public void start() {
        logger = LogManager.getLogger();
        logger.traceEntry("Enter the Application");
    }

    @After
    public void tearDown() {
        Driver.quitDriver();
        logger.traceExit("Exit the Application");
    }

}
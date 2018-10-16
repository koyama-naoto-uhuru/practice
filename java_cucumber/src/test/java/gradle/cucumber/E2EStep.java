package gradle.cucumber;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.testng.Assert.assertEquals;

public class E2EStep {

    private ChromeDriver driver = new WebDriverWrapper().getDriver();

    @Given("I visit top page")
    public void i_visit_top_page() {
        driver.get("file:///Users/d.kanai/Desktop/hello.html");
    }

    @Then("^I should see \"([^\"]*)\"$")
    public void iShouldSee(String id) {
        assertEquals("hello", driver.findElementById(id).getText());
        driver.close();
    }
}

package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {

    @Step("Open Home page")
    public void openHomePage() {
        open("https://github.com/");
    }

    @Step("Search for repository {repo}")
    public void searchForRepository(String repo) {
        $(".header-search-button").click();
        $("#query-builder-test").setValue(repo).pressEnter();
    }

    @Step("Click on repository link {repo}")
    public void clickOnRepositoryLink(String repo) {
        $(linkText(repo)).click();
    }

    @Step ("Open Issues tab ")
    public void openIssuesTab() {
        $("span[data-content='Issues']").click();
    }

    @Step("Check text on empty issues page with {text}")
    public void shouldSeeEmptyPageText(String text) {
        $(".container-md").shouldHave(text(text));
    }

    @Step("Check issue with number {issue}")
    public void shouldSeeIssueWithNumber (int issue) {
        $(withText("#" + issue)).should(Condition.exist);
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

}

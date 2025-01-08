package guru.qa;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class StepsTests {

    private static String REPOSITORY = "AnastasiiaBlon/qa_guru_files_tests";
    private static String TEXT = "Welcome to issues!";

    @Test
    public void testLambdaStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Open Home page", () -> {
            open("https://github.com/");
        });

        step("Search for repository " + REPOSITORY, () -> {
            $(".header-search-button").click();
            $("#query-builder-test").setValue(REPOSITORY).pressEnter();
        });

        step("Click on repository link " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });

        step("Open Issues tab ", () -> {
            $("span[data-content='Issues']").click();
        });

        step("Check text on empty issues page " + TEXT, () -> {
            $(".container-md").shouldHave(text(TEXT));
        });
    }

    @Test
    public void testAnnotatedStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.openHomePage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssuesTab();
        steps.shouldSeeEmptyPageText(TEXT);
    }
}
